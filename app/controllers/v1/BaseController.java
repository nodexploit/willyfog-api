package controllers.v1;

import com.google.gson.Gson;
import com.google.inject.Inject;
import http.actions.OAuth2Action;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.With;

@With(OAuth2Action.class)
public class BaseController extends Controller {

    @Inject
    protected Gson gson;

    public static Result ok(String content) {
        return Results.ok(content).as("application/json");
    }
}
