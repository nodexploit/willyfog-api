package daos;

import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

public class RequestDestinationSubjectDao extends BaseDao {

    public static String tableName = "request_destination_subject";

    public List<Map<String, Object>> requestDestinations(Integer requestId) {
        String sql = "SELECT " +
                "rds.subject_id, " +
                "IFNULL(rds.subject_name, s.name) AS name, IFNULL(rds.subject_credits, s.credits) AS credits, " +
                "IFNULL(rds.subject_code, s.code) AS code, " +
                "IFNULL(rds.centre_name, c.name) AS centre_name, IFNULL(rds.centre_code, c.code) AS centre_code " +
                "FROM request_destination_subject rds " +
                "LEFT JOIN subject s ON rds.subject_id = s.id " +
                "LEFT JOIN degree d ON s.degree_id = d.id " +
                "LEFT JOIN centre c ON d.centre_id = c.id " +
                "WHERE rds.request_id = :requestId";

        List<Map<String, Object>> requestDestinationSubjects;
        try(Connection con = this.db.open()) {
            requestDestinationSubjects = toMapList(con.createQuery(sql)
                    .addParameter("requestId", requestId)
                    .executeAndFetchTable());
        }

        return requestDestinationSubjects;
    }
}
