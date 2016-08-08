package daos;

import models.Subject;
import org.sql2o.Connection;

public class SubjectDao extends BaseDao {

    public static String tableName = "subject";

    public Subject find(Long id) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE id = :id";

        Subject subject = null;
        try(Connection con = this.db.open()) {
            subject = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Subject.class);
        }

        return subject;
    }
}
