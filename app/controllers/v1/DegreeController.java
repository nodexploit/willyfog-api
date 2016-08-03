package controllers.v1;

import com.google.inject.Inject;
import daos.DegreeDao;
import models.Subject;
import play.mvc.Result;

import java.util.List;

public class DegreeController extends BaseController {

    @Inject
    private DegreeDao degreeDao;

    public Result subjects(Integer degreeId) {
        List<Subject> ss = degreeDao.subjects(degreeId);

        return ok(gson.toJson(ss));
    }
}
