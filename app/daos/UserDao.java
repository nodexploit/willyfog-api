package daos;

import models.User;
import org.sql2o.Connection;

public class UserDao extends BaseDao {

    public static String tableName = "user";

    public User find(Integer id) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE id = :id";

        User user = null;
        try(Connection con = this.db.open()) {
             user = con.createQuery(sql)
                     .addParameter("id", id)
                     .executeAndFetchFirst(User.class);
        }

        return user;
    }

    public User find(String accessToken) {
        String sql = "SELECT u.* " +
                "FROM " + tableName + " u " +
                "JOIN " + OAuth2Dao.tableName + " oat ON oat.user_id = u.id " +
                "WHERE oat.access_token = :accessToken";

        User user = null;
        try(Connection con = this.db.open()) {
            user = con.createQuery(sql)
                    .addParameter("accessToken", accessToken)
                    .executeAndFetchFirst(User.class);
        }

        return user;
    }
}
