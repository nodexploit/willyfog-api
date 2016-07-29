package daos;

import com.google.inject.Inject;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class UserDao {

    @Inject
    private Sql2o db;

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
}
