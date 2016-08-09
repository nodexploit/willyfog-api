package daos;

import org.sql2o.Connection;

public class AcceptedRequestDao extends BaseDao {

    public static String tableName = "accepted_request";

    public Long accept(Long requestId, Long recognizerId) {
        String sql = "INSERT INTO " + tableName + " " +
                "(request_id, recognizer_id) " +
                "VALUES (:requestId, :recognizerId)";

        Long lastInsertedId;
        try(Connection con = this.db.open()) {
            lastInsertedId = (Long) con.createQuery(sql)
                    .addParameter("requestId", requestId)
                    .addParameter("recognizerId",recognizerId)
                    .executeUpdate()
                    .getKey();
        }

        return lastInsertedId;
    }
}
