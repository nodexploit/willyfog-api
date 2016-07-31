package controllers.v1;

import com.google.inject.Inject;
import daos.RequestDao;
import daos.RequestDestinationSubjectDao;
import play.mvc.Result;

import java.util.List;
import java.util.Map;

public class RequestController extends BaseController {

    @Inject
    private RequestDao requestDao;
    @Inject
    private RequestDestinationSubjectDao requestDestinationDao;

    public Result show(Integer id) {
        Map<String, Object> r = requestDao.find(id);
        List<Map<String, Object>> rds = requestDestinationDao.requestDestinations(id);
        r.put("destination_subject", rds);

        return ok(gson.toJson(r));
    }

    public Result showUserRequests(Integer userId) {
        List<Map<String, Object>>  rs = requestDao.userRequests(userId);

        return ok(gson.toJson(rs));
    }
}
