package controllers.v1;

import com.google.inject.Inject;
import daos.UniversityDao;
import models.University;
import play.mvc.Result;

import java.util.List;

public class CityController extends BaseController {

    @Inject
    private UniversityDao universityDao;

    public Result universities(Integer cityId) {
        List<University> us = universityDao.cityUniversities(cityId);

        return ok(gson.toJson(us));
    }
}
