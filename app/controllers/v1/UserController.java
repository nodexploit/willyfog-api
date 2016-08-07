package controllers.v1;

import com.google.inject.Inject;
import daos.UserDao;
import daos.UserEnrolledDegreeDao;
import http.ErrorResponse;
import http.SuccessReponse;
import models.User;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

public class UserController extends BaseController {

    @Inject
    private UserDao userDao;
    @Inject
    private UserEnrolledDegreeDao userEnrolledDegreeDao;

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

        return ok(gson.toJson(result.get(0)));
    }

    /**
     * TODO: handle values like degree_id = ""
     *
     * @return
     */
    public Result register() {
        String[] requiredParams = {"name", "surname", "nif", "email", "digest", "degree_id"};
        List<String> missingFields = checkRequiredParams(requiredParams);

        if (missingFields.size() > 0) {
            return ok(gson.toJson(
                    new ErrorResponse("Missing required fields", missingFields)
            ));
        }

        Map<String, String[]> params = request().body().asFormUrlEncoded();

        User user = new User();
        user.setName(params.get("name")[0]);
        user.setSurname(params.get("surname")[0]);
        user.setNif(params.get("nif")[0]);
        user.setEmail(params.get("email")[0]);
        user.setDigest(params.get("digest")[0]);

        if (!user.isValid()) {
            return ok(gson.toJson(
                    new ErrorResponse("User not valid", user.getErrors())
            ));
        }

        Long userId = userDao.create(user);
        Integer degreeId = Integer.valueOf(params.get("degree_id")[0]);

        Object enrollId = userEnrolledDegreeDao.enrollUser(userId, degreeId);

        if (enrollId == null) {
            return ok(gson.toJson(
                    new ErrorResponse("Degree not valid")
            ));
        }

        return ok(gson.toJson(
                new SuccessReponse("Success", null)
        ));
    }
}
