package controllers.v1;

import com.google.inject.Inject;
import daos.UniversityDao;
import models.Centre;
import models.University;
import play.mvc.Result;

import java.util.List;

public class UniversityController extends BaseController {

    @Inject
    private UniversityDao universityDao;

    public Result index() {
        List<University> us = universityDao.all();

        return ok(gson.toJson(us));
    }

    public Result centres(Long universityId) {
        List<Centre> cs = universityDao.centres(universityId);

        return ok(gson.toJson(cs));
    }
}
