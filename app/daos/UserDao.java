package daos;

import models.User;
import org.sql2o.Connection;

public class UserDao extends BaseDao {

    public User find(int id) {
        String sql = "SELECT * " +
                "FROM user " +
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
                "FROM user u " +
                "JOIN oauth_access_token oat ON oat.user_id = u.id " +
                "WHERE access_token = :accessToken";

        User user = null;
        try(Connection con = this.db.open()) {
            user = con.createQuery(sql)
                    .addParameter("accessToken", accessToken)
                    .executeAndFetchFirst(User.class);
        }

        return user;
    }
}
