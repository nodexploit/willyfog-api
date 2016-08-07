package daos;

import org.sql2o.Connection;

public class UserHasRoleDao extends BaseDao {

    public static String tableName = "user_has_role";


    public Long userRole(Integer userId) {
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
}
