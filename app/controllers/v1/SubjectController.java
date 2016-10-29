package controllers.v1;

import com.google.inject.Inject;
import daos.SubjectDao;
import http.actions.OAuth2Action;
import models.Subject;
import play.mvc.Result;
import play.mvc.With;

@With(OAuth2Action.class)
public class SubjectController extends BaseController {

    @Inject
    private SubjectDao subjectDao;

    public Result index() {
        return ok(gson.toJson(
                subjectDao.index()
        ));
    }

    public Result show(Long id) {
        Subject s = subjectDao.find(id);

        return ok(gson.toJson(s));
    }
}
