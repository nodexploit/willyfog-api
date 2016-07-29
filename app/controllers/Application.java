package controllers;

import com.google.gson.Gson;
import com.google.inject.Inject;
import daos.UserDao;
import models.User;
import play.*;
import play.mvc.*;

public class Application extends Controller {

    @Inject
    private UserDao userDao;
    @Inject
    private Gson gson;

    public Result greet() {
        User u = userDao.find(1);

        return ok(gson.toJson(u));
    }
}
