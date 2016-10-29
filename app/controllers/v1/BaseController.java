package controllers.v1;

import com.google.gson.Gson;
import com.google.inject.Inject;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseController extends Controller {

    @Inject
    protected Gson gson;

    public static Result ok(String content) {
        return Results.ok(content).as("application/json");
    }

    public List<String> checkRequiredParams(String[] fields) {
        List<String> requiredPostFields = new ArrayList<>();
        Map<String, String[]> params = request().body().asFormUrlEncoded();

        for (String key: fields) {
            if (params.get(key) == null) {
                requiredPostFields.add(key);
            }
        }

        return requiredPostFields;
    }
}
