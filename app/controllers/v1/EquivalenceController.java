package controllers.v1;

import com.google.inject.Inject;
import daos.EquivalenceDao;
import http.actions.AdminAction;
import http.actions.OAuth2Action;
import play.mvc.Result;
import play.mvc.With;

import java.util.List;
import java.util.Map;

public class EquivalenceController extends BaseController {

    @Inject
    private EquivalenceDao equivalenceDao;

    public Result search(String subjectName) {
        List<Map<String, Object>> result = equivalenceDao.getEquivalences(subjectName);

        return ok(gson.toJson(result));
    }

    @With(AdminAction.class)
    public Result hello() {
        return ok("Hello world!");
    }
}
