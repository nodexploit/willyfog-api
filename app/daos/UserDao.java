package daos;

import models.User;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> getUserInfo(Integer userId) {
        String sql = "SELECT u.name, u.surname, u.nif, " +
                "u.email, d.name AS degree_name, " +
                "c.name AS centre_name, un.name AS university_name " +
                "FROM " + tableName + " u " +
                "JOIN user_enrolled_degree AS ued ON u.id = ued.user_id " +
                "JOIN degree AS d ON ued.degree_id = d.id " +
                "JOIN centre AS c ON d.centre_id = c.id " +
                "JOIN university AS un ON c.university_id = un.id " +
                "WHERE u.id = :userId";

        List<Map<String, Object>> result = null;

        try(Connection con = this.db.open()) {
            result = toMapList(con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchTable());
        }

        return result;
    }
}
