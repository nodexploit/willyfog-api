package daos;

import models.RequestDestinationSubject;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

public class RequestDestinationSubjectDao extends BaseDao {

    public static String tableName = "request_destination_subject";

    public List<Map<String, Object>> requestDestinations(Integer requestId) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE request_id = :requestId";

        List<Map<String, Object>> requestDestinationSubjects;
        try(Connection con = this.db.open()) {
            requestDestinationSubjects = toMapList(con.createQuery(sql)
                    .addParameter("requestId", requestId)
                    .executeAndFetchTable());
        }

        return requestDestinationSubjects;
    }
}
