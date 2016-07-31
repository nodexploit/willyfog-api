package controllers.v1;

import com.google.inject.Inject;
import daos.UserDao;
import models.User;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

public class UserController extends BaseController {

    @Inject
    private UserDao userDao;

    public Result show(Integer id) {
        User u = userDao.find(id);

        return ok(gson.toJson(u));
    }

    public Result userByToken(String accessToken) {
        User u = userDao.find(accessToken);

        return ok(gson.toJson(u));
    }

    public Result userInfo(Integer id) {
        List<Map<String, Object>> result = userDao.getUserInfo(id);

        return ok(gson.toJson(result));
    }
}
