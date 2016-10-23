package controllers.v1;

import com.google.inject.Inject;
import daos.CentreDao;
import models.Degree;
import models.User;
import play.mvc.Result;

import java.util.List;

public class CentreController extends BaseController {

    @Inject
    private CentreDao centreDao;

    public Result degrees(Long centreId) {
        List<Degree> ds = centreDao.degrees(centreId);

        return ok(gson.toJson(ds));
    }

    public Result recognizers(Long centreId) {
        List<User> rs = centreDao.recognizers(centreId);

        return ok(gson.toJson(rs));
    }
}
