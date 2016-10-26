package controllers.v1;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import daos.NotificationDao;
import daos.SubjectDao;
import daos.UserDao;
import daos.UserHasRoleDao;
import daos.UserRecognizeSubjectDao;
import http.ErrorResponse;
import http.SuccessReponse;
import http.actions.CoordinatorAction;
import models.Notification;
import models.Role;
import models.Subject;
import models.User;
import play.mvc.Result;
import play.mvc.With;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController extends BaseController {

    @Inject
    private UserDao userDao;
    @Inject
    private UserHasRoleDao userHasRoleDao;
    @Inject
    private NotificationDao notificationDao;
    @Inject
    private SubjectDao subjectDao;
    @Inject
    private UserRecognizeSubjectDao userRecognizeSubjectDao;

    public Result show(Long id) {
        User u = userDao.find(id);

        return ok(gson.toJson(u));
    }

    public Result userByToken(String accessToken) {
        User u = userDao.find(accessToken);

        return ok(gson.toJson(u));
    }

    public Result userInfo(Long userId) {
        Integer roleId = userHasRoleDao.userRole(userId).intValue();

        Map<String, Object>  userInfo = new HashMap<>();
        switch (roleId) {
            case Role.ADMIN:
                userInfo = userDao.adminInfo(userId);
                break;
            case Role.COORD:
                userInfo = userDao.coordinatorInfo(userId);
                break;
            case Role.RECOG:
                userInfo = userDao.recognizerInfo(userId);
                break;
            case Role.STUDENT:
                userInfo = userDao.studentInfo(userId);
                break;
        }

        User u = userDao.find(userId);
        userInfo.put("gravatar", u.gravatar());

        return ok(gson.toJson(userInfo));
    }

    public Result notifications(Long userId) {
        List<Notification> ns = notificationDao.userNotifications(userId);
        notificationDao.setRead(userId);

        return ok(gson.toJson(ns));
    }

    public Result recognizerSubjects(Long userId) {
        Long userRole = userHasRoleDao.userRole(userId);

        if (Role.RECOG != userRole) {
            return ok(gson.toJson(
                    new ErrorResponse("Given user is not a recognizer"))
            );
        }

        List<Subject> subjects = subjectDao.recognizerSubjects(userId);

        return ok(gson.toJson(subjects));
    }

    @With(CoordinatorAction.class)
    public Result recognizerSubject(Long userId, Long subjectId) {
        userRecognizeSubjectDao.deleteSubject(userId, subjectId);

        return ok(gson.toJson(new SuccessReponse("true")));
    }

    @With(CoordinatorAction.class)
    public Result addSubjects(Long recognizerId) {
        List<Integer> subjectIds = gson.fromJson(
                request().body().asFormUrlEncoded()
                        .get("subject_ids")[0],
                new TypeToken<List<Integer>>() {
                }.getType()
        );

        userRecognizeSubjectDao.addSubjects(recognizerId, subjectIds);

        return ok(gson.toJson(new SuccessReponse("true")));
    }
}
