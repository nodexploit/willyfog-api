package controllers.v1;

import com.google.inject.Inject;
import daos.UserDao;
import models.User;
import play.mvc.Result;

public class UserController extends BaseController {

    @Inject
    private UserDao userDao;

    public Result show(Integer id) {
        User u = userDao.find(id);

        return ok(gson.toJson(u));
    }
}
