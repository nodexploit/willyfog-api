package controllers.v1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import daos.RequestDao;
import daos.RequestDestinationSubjectDao;
import models.Request;
import models.RequestDestinationSubject;
import play.mvc.Result;
import scala.util.parsing.json.JSONObject;

import java.util.List;
import java.util.Map;

public class RequestController extends BaseController {

    @Inject
    private RequestDao requestDao;
    @Inject
    private RequestDestinationSubjectDao requestDestinationDao;

    public Result show(Integer id) {
        Map<String, Object> r = requestDao.find(id);
//        List<RequestDestinationSubject> rds = requestDestinationDao.requestDestinations(id);
//        r.put("destination_subject", rds);

        return ok(gson.toJson(r));
    }

    public Result showUserRequests(Integer userId) {
        List<Map<String, Object>>  rs = requestDao.userRequests(userId);

        return ok(gson.toJson(rs));
    }
}
