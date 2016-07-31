package controllers.v1;

import com.google.inject.Inject;
import daos.SubjectDao;
import models.Subject;
import play.mvc.Result;

public class SubjectController extends BaseController {

    @Inject
    private SubjectDao subjectDao;

    public Result show(Integer id) {
        Subject s = subjectDao.find(id);

        return ok(gson.toJson(s));
    }
}
