package controllers.v1;

import com.google.inject.Inject;
import daos.*;
import http.ErrorResponse;
import http.SuccessReponse;
import models.Notification;
import models.Role;
import models.Subject;
import models.User;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

public class UserController extends BaseController {

    @Inject
    private UserDao userDao;
    @Inject
    private UserHasRoleDao userHasRoleDao;
    @Inject
    private UserEnrolledDegreeDao userEnrolledDegreeDao;
    @Inject
    private NotificationDao notificationDao;
    @Inject
    private SubjectDao subjectDao;

    public Result show(Long id) {
        User u = userDao.find(id);

        return ok(gson.toJson(u));
    }

    public Result userByToken(String accessToken) {
        User u = userDao.find(accessToken);

        return ok(gson.toJson(u));
    }

    public Result userInfo(Long id) {
        User u = userDao.find(id);
        Map<String, Object> result = userDao.getUserInfo(id);

        result.put("gravatar", u.gravatar());

        return ok(gson.toJson(result));
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
}
