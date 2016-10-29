package controllers.v1;

import com.google.inject.Inject;
import daos.UniversityDao;
import http.actions.OAuth2Action;
import models.University;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;

@With(OAuth2Action.class)
public class CityController extends BaseController {

    @Inject
    private UniversityDao universityDao;

    public Result universities(Long cityId) {
        List<University> us = universityDao.cityUniversities(cityId);

        return ok(gson.toJson(us));
    }
}
