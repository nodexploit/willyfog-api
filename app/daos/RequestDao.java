package daos;

import org.sql2o.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDao extends BaseDao {

    public static String tableName = "request";

    public Map<String, Object> find(Integer id) {
        String sql = "SELECT " +
                "r.id, " +
                "s.id AS subject_id, s.code AS subject_code, s.name AS subject_name " +
                "FROM " + tableName + " r " +
                "JOIN " + SubjectDao.tableName + " s ON r.origin_subject_id = s.id " +
                "WHERE r.id = :id";

        List<Map<String, Object>> requests;
        try(Connection con = this.db.open()) {
            requests = toMapList(con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchTable());
        }

        Map<String, Object> request;
        if (requests.size() > 0) {
            request = requests.get(0);
        } else {
            request = new HashMap<>();
        }

        return request;
    }

    public List<Map<String, Object>> userRequests(Integer userId) {
        String sql = "SELECT " +
                "r.id, " +
                "r.origin_subject_id AS subject_id, s.code AS subject_code, s.name AS subject_name " +
                "FROM " + tableName + " r " +
                "JOIN " + SubjectDao.tableName + " s ON r.origin_subject_id = s.id " +
                "WHERE student_id = :userId";

        List<Map<String, Object>> requests;
        try(Connection con = this.db.open()) {
            requests = toMapList(con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchTable());
        }

        return requests;
    }
}
