package controllers.v1;

import com.google.inject.Inject;
import daos.UniversityDao;
import daos.UserDao;
import models.University;
import models.User;
import play.mvc.Result;

import java.util.List;

public class UniversityController extends BaseController {

    @Inject
    private UniversityDao universityDao;

    public Result index() {
        List<University> us = universityDao.all();

        return ok(gson.toJson(us));
    }
}
