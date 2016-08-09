package daos;

import org.sql2o.Connection;

public class RejectedRequestDao extends BaseDao {

    public static String tableName = "rejected_request";

    public Long reject(Long requestId, Long recognizerId) {
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
