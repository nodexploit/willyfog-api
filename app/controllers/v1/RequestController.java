package controllers.v1;

import com.google.inject.Inject;
import daos.CommentDao;
import daos.RequestDao;
import daos.RequestDestinationSubjectDao;
import http.ErrorResponse;
import http.SuccessReponse;
import models.Comment;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

public class RequestController extends BaseController {

    @Inject
    private RequestDao requestDao;
    @Inject
    private CommentDao commentDao;
    @Inject
    private RequestDestinationSubjectDao requestDestinationDao;

    public Result show(Integer id) {
        Map<String, Object> r = requestDao.find(id);
        List<Map<String, Object>> rds = requestDestinationDao.requestDestinations(id);
        r.put("destination_subjects", rds);

        return ok(gson.toJson(r));
    }

    public Result showUserRequests(Integer userId) {
        List<Map<String, Object>>  rs = requestDao.userRequests(userId);

        return ok(gson.toJson(rs));
    }

    public Result comment(Integer requestId) {
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
        comment.setUserId(1);

        if (!comment.isValid()) {
            return ok(gson.toJson(
                    new ErrorResponse("Comment not valid", comment.getErrors())
            ));
        }

        Object commentId = commentDao.create(comment);

        if (commentId == null) {
            return ok(gson.toJson(
                    new ErrorResponse("Ups something went wrong")
            ));
        }

        return ok(gson.toJson(
                new SuccessReponse("Success", commentId)
        ));
    }

    public Result comments(Integer requestId) {
        List<Map<String, Object>> cs = commentDao.requestComments(requestId);

        return ok(gson.toJson(cs));
    }
}
