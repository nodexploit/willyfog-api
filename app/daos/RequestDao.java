package daos;

import models.Request;
import org.sql2o.Connection;

import java.util.List;

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

    public List<Request> userRequests(Integer userId) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE student_id = :userId";

        List<Request> requests;
        try(Connection con = this.db.open()) {
            requests = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetch(Request.class);
        }

        return requests;
    }
}
