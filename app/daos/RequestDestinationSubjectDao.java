package daos;

import models.RequestDestinationSubject;
import org.sql2o.Connection;

import java.util.List;

public class RequestDestinationSubjectDao extends BaseDao {

    public static String tableName = "request_destination_subject";

    public List<RequestDestinationSubject> requestDestinations(Integer requestId) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE request_id = :requestId";

        List<RequestDestinationSubject> requestDestinationSubjects;
        try(Connection con = this.db.open()) {
            requestDestinationSubjects = con.createQuery(sql)
                    .addParameter("requestId", requestId)
                    .executeAndFetch(RequestDestinationSubject.class);
        }

        return requestDestinationSubjects;
    }
}
