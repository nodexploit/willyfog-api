package daos;

import org.sql2o.Connection;

public class UserHasRoleDao extends BaseDao {

    public static String tableName = "user_has_role";

    public Long userRole(Long userId) {
        String sql = "SELECT role_id " +
                "FROM " + tableName + " " +
                "WHERE user_id = :userId";

        Long role;
        try(Connection con = this.db.open()) {
            role = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeScalar(Long.class);
        }

        return role;
    }

    public Long create(Long userId, Long roleId) {
        String sql = "INSERT INTO " + tableName + " " +
                "(user_id, role_id) " +
                "VALUES (:userId, :roleId)";

        Object lastInsertedId;
        try(Connection con = this.db.open()) {
            lastInsertedId = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("roleId", roleId)
                    .executeUpdate()
                    .getKey();
        }

        return (Long) lastInsertedId;
    }
}
