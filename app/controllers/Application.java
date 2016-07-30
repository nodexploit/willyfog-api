package controllers;

import com.google.gson.Gson;
import com.google.inject.Inject;
import daos.UserDao;
import http.actions.OAuth2Action;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;

@With(OAuth2Action.class)
public class Application extends Controller {

    @Inject
    private UserDao userDao;
    @Inject
    private Gson gson;

    public Result greet() {
        String authorization = request().getHeader("Authorization");
        String[] split = authorization.split(" ");
        String accessToken = split[1];
        User u = userDao.find(accessToken);

        return ok(gson.toJson(u));
    }
}
