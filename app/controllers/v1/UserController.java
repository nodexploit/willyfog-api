package controllers.v1;

import com.google.inject.Inject;
import daos.NotificationDao;
import daos.SubjectDao;
import daos.UserDao;
import daos.UserHasRoleDao;
import http.ErrorResponse;
import models.Notification;
import models.Role;
import models.Subject;
import models.User;
import play.mvc.Result;
import play.mvc.Results;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
}
