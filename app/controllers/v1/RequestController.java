package controllers.v1;

import com.google.inject.Inject;
import daos.AcceptedRequestDao;
import daos.CommentDao;
import daos.NotificationDao;
import daos.RejectedRequestDao;
import daos.RequestDao;
import daos.RequestDestinationSubjectDao;
import daos.SubjectDao;
import daos.UserDao;
import daos.UserHasRoleDao;
import daos.UserRecognizeSubjectDao;
import http.ErrorResponse;
import http.SuccessReponse;
import http.actions.OAuth2Action;
import http.actions.RecognizerAction;
import models.Comment;
import models.Notification;
import models.Request;
import models.RequestDestinationSubject;
import models.Role;
import models.Subject;
import models.User;
import play.mvc.Result;
import play.mvc.With;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@With(OAuth2Action.class)
public class RequestController extends BaseController {

    @Inject
    private RequestDao requestDao;
    @Inject
    private UserDao userDao;
    @Inject
    private CommentDao commentDao;
    @Inject
    private SubjectDao subjectDao;
    @Inject
    private RequestDestinationSubjectDao requestDestinationDao;
    @Inject
    private UserRecognizeSubjectDao userRecognizeSubjectDao;
    @Inject
    private NotificationDao notificationDao;
    @Inject
    private AcceptedRequestDao acceptedRequestDao;
    @Inject
    private RejectedRequestDao rejectedRequestDao;
    @Inject
    private UserHasRoleDao userHasRoleDao;

    public Result show(Long requestId) {
        Long userId = (Long) ctx().args.get("user_id");

        Integer userRole = userHasRoleDao.userRole(userId).intValue();

        boolean canSee = true;
        switch (userRole) {
            case Role.STUDENT:
                canSee = requestDao.studentCanSeeRequest(requestId, userId);
                break;
            case Role.RECOG:
                canSee = requestDao.recognizerCanSeeRequest(requestId, userId);
                break;
        }

        if (!canSee) {
            return ok(gson.toJson(
                    new ErrorResponse("Not authorized")
            ));
        }

        Map<String, Object> r = requestDao.find(requestId);
        List<Map<String, Object>> rds = requestDestinationDao.requestDestinations(requestId);
        r.put("destination_subjects", rds);

        return ok(gson.toJson(r));
    }

    public Result showUserRequests(Long userId) {
        Integer roleId = userHasRoleDao.userRole(userId).intValue();

        List<Map<String, Object>>  rs = new ArrayList<>();
        switch (roleId) {
            case Role.ADMIN:
                rs = requestDao.index();
                break;
            case Role.COORD:
                rs = requestDao.coordinatorRequests(userId);
                break;
            case Role.RECOG:
                rs = requestDao.recognizerRequests(userId);
                break;
            case Role.STUDENT:
                rs = requestDao.studentRequests(userId);
                break;
        }

        Map<Object, List<Map<String, Object>>> groupByCategory =
                rs.stream().collect(
                        Collectors.groupingBy(row -> row.get("category"))
                );

        return ok(gson.toJson(groupByCategory));
    }

    public Result comment(Long requestId) {
        String[] requiredParams = {"content"};
        List<String> missingFields = checkRequiredParams(requiredParams);

        if (missingFields.size() > 0) {
            return ok(gson.toJson(
                    new ErrorResponse("Missing required fields", missingFields)
            ));
        }

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        Comment comment = new Comment();
        comment.setRequestId(requestId);
        comment.setContent(params.get("content")[0]);
        comment.setUserId((Long) ctx().args.get("user_id"));

        if (!comment.isValid()) {
            return ok(gson.toJson(
                    new ErrorResponse("Comment not valid", comment.getErrors())
            ));
        }

        Object commentId = commentDao.create(comment);

        if (commentId == null) {
            return ok(gson.toJson(
                    new ErrorResponse("Oops something went wrong")
            ));
        }

        Map<String, Object> subjectRequest = requestDao.find(requestId);
        // If another user comment my request, notify me
        Long commentUserId = comment.getUserId();
        Integer requestStudentId = (Integer) subjectRequest.get("student_id");
        if (!commentUserId.equals(requestStudentId.longValue())) {
            Notification notification = new Notification();
            notification.setUserId(requestStudentId.longValue());
            User commenterUser = userDao.find(commentUserId);
            notification.setContent("Usuario " + commenterUser.getName() + " " + commenterUser.getSurname() +
                    " ha comentado tu petición.");

            notificationDao.create(notification);
        }

        return ok(gson.toJson(
                new SuccessReponse("Success", commentId)
        ));
    }

    public Result comments(Long requestId) {
        List<Map<String, Object>> cs = commentDao.requestComments(requestId);

        return ok(gson.toJson(cs));
    }

    @With(RecognizerAction.class)
    public Result accept(Long requestId) {
        Long recognizerId = (Long) ctx().args.get("user_id");

        if (!requestDao.userRecognizesSubject(requestId, recognizerId)) {
            return ok(
                    gson.toJson(new ErrorResponse("Trying to recognize an incorrect subject."))
            );
        }

        Long acceptedId = acceptedRequestDao.accept(requestId, recognizerId);

        if (acceptedId != null) {
            notifyRequestUpdate(requestId, "accepted");

            return ok(
                    gson.toJson(new SuccessReponse("Success", acceptedId))
            );
        } else {
            return ok(
                    gson.toJson(new ErrorResponse("Oops, something went wrong."))
            );
        }
    }

    @With(RecognizerAction.class)
    public Result reject(Long requestId) {
        Long recognizerId = (Long) ctx().args.get("user_id");

        if (!requestDao.userRecognizesSubject(requestId, recognizerId)) {
            return ok(
                    gson.toJson(new ErrorResponse("Trying to recognize an incorrect subject."))
            );
        }

        Long rejectedId = rejectedRequestDao.reject(requestId, recognizerId);

        if (rejectedId != null) {
            notifyRequestUpdate(requestId, "rejected");

            return ok(
                    gson.toJson(new SuccessReponse("Success", rejectedId))
            );
        } else {
            return ok(
                    gson.toJson(new ErrorResponse("Oops, something went wrong."))
            );
        }
    }

    private void notifyRequestUpdate(Long requestId, String state) {
        Map<String, Object> request = requestDao.find(requestId);
        Notification notification = new Notification();
        notification.setUserId(((Integer)request.get("student_id")).longValue());
        notification.setContent("Tu petición para " + request.get("subject_name") + " ha sido " +
                (state.equals("rejected") ? "rechazada" : "aprobada") + ".");
        notificationDao.create(notification);
    }

    public Result create() {
        String[] requiredParams = {"request_content"};
        List<String> missingFields = checkRequiredParams(requiredParams);

        if (missingFields.size() > 0) {
            return ok(gson.toJson(
                    new ErrorResponse("Missing required fields", missingFields)
            ));
        }

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        String requestContentJson = params.get("request_content")[0];
        RequestContent requestContent = gson.fromJson(requestContentJson, RequestContent.class);

        Request req = new Request();
        req.setMobilityTypeId(requestContent.mobility_type_id);
        req.setOriginSubjectId(requestContent.origin_subject_id);
        req.setStudentId((Long) ctx().args.get("user_id"));

        Long reqId = requestDao.create(req);

        List<RequestDestinationSubject> destinations = destinationToModel(requestContent.destinations, reqId);

        requestDestinationDao.create(destinations);

        // Notify recognizers
        notifyRecognizers(req.getOriginSubjectId());

        return ok(
                gson.toJson(new SuccessReponse("Success", reqId))
        );
    }

    private List<RequestDestinationSubject> destinationToModel(List<Destination> destinations, Long requestId) {
        return destinations.stream()
                .map(d -> {
                    RequestDestinationSubject rds = new RequestDestinationSubject();
                    rds.setRequestId(requestId);
                    rds.setSubjectId(d.destination_subject_id);
                    rds.setSubjectName(d.destination_subject);
                    rds.setSubjectCredits(d.destination_subject_credits);
                    rds.setSubjectCode(d.destination_subject_code);
                    rds.setCountryName(d.destination_country);
                    rds.setCentreName(d.destination_centre);
                    rds.setCityName(d.destination_city);
                    rds.setUniversityName(d.destination_university);
                    rds.setDegreeName(d.destination_degree);
                    rds.setUri(d.destination_url);

                    return rds;
                }).collect(Collectors.toList());
    }

    private void notifyRecognizers(Long subjectId) {
        List<Long> recognizerIds = userRecognizeSubjectDao.subjectRecognizers(subjectId);

        for (Long recognizerId: recognizerIds) {
            Notification notification = new Notification();
            notification.setUserId(recognizerId);
            Subject subject = subjectDao.find(subjectId);
            notification.setContent("La asignatura " + subject.getName() + " tiene una nueva petición.");

            notificationDao.create(notification);
        }
    }

    private class RequestContent {

        private Long origin_subject_id;
        private Long mobility_type_id;
        private List<Destination> destinations;
    }

    private class Destination {

        private Long destination_subject_id;
        private String destination_url;
        private String destination_country;
        private String destination_city;
        private String destination_university;
        private String destination_centre;
        private String destination_degree;
        private String destination_subject_code;
        private String destination_subject;
        private Integer destination_subject_credits;
    }
}
