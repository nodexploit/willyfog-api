package daos;

import org.sql2o.Connection;

public class UserEnrolledDegreeDao extends BaseDao {

    public static String tableName = "user_enrolled_degree";

    public Object enrollUser(Long userId, Integer degreeId) {
        String sql = "INSERT INTO " + tableName + " " +
                "(user_id, degree_id) " +
                "VALUES (:userId, :degreeId)";

        Object lastInsertedId;
        try(Connection con = this.db.open()) {
            lastInsertedId = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("degreeId", degreeId)
                    .executeUpdate()
                    .getKey();
        }

        return lastInsertedId;
    }
}
