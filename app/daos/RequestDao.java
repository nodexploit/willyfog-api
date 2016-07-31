package daos;

import models.Request;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

public class RequestDao extends BaseDao {

    public static String tableName = "request";

    public Request find(Integer id) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE id = :id";

        Request request;
        try(Connection con = this.db.open()) {
            request = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Request.class);
        }

        return request;
    }

    public List<Map<String, Object>> userRequests(Integer userId) {
        String sql = "SELECT * " +
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
