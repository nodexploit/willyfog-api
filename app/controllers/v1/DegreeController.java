package controllers.v1;

import com.google.inject.Inject;
import daos.DegreeDao;
import http.actions.OAuth2Action;
import models.Subject;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;

@With(OAuth2Action.class)
public class DegreeController extends BaseController {

    @Inject
    private DegreeDao degreeDao;

    public Result subjects(Long degreeId) {
        List<Subject> ss = degreeDao.subjects(degreeId);

        return ok(gson.toJson(ss));
    }
}
