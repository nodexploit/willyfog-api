package daos;

import models.User;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

public class UserDao extends BaseDao {

    public static String tableName = "user";

    public User find(Long id) {
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

    public Map<String, Object> getUserInfo(Long userId) {
        String sql = "SELECT " +
                "d.id AS degree_id, " +
                "u.name, u.surname, u.nif, " +
                "u.email, d.name AS degree_name, " +
                "c.name AS centre_name, un.name AS university_name, " +
                "uhr.role_id, rol.name AS role_name " +
                "FROM " + tableName + " u " +
                "LEFT JOIN user_enrolled_degree AS ued ON u.id = ued.user_id " +
                "LEFT JOIN degree AS d ON ued.degree_id = d.id " +
                "LEFT JOIN user_coordinates_centre AS ucc ON ucc.user_id = u.id " +
                "LEFT JOIN centre AS c ON d.centre_id = c.id " +
                "LEFT JOIN university AS un ON c.university_id = un.id " +
                "JOIN user_has_role AS uhr ON u.id = uhr.user_id " +
                "JOIN role AS rol ON uhr.role_id = rol.id " +
                "WHERE u.id = :userId " +
                "LIMIT 1";

        List<Map<String, Object>> result;

        try(Connection con = this.db.open()) {
            result = toMapList(con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchTable());
        }

        if (result.size() <= 0) {
            throw new RuntimeException("User info not found for userId: " + userId);
        }

        return result.get(0);
    }

    public Long create(User user) {
        String sql = "INSERT INTO " + tableName + " " +
                "(name, surname, nif, email, digest) " +
                "VALUES (:name, :surname, :nif, :email, :digest)";

        return insertModel(sql, user);
    }
}
