package daos;

import models.Degree;
import models.User;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

public class CentreDao extends BaseDao {

    public static String tableName = "centre";

    public List<Degree> degrees(Long centreId) {
        String sql = "SELECT " +
                "d.id, d.name " +
                "FROM " + DegreeDao.tableName + " d " +
                "WHERE d.centre_id = :centreId";

        List<Degree> degrees;
        try(Connection con = this.db.open()) {
            degrees = con.createQuery(sql)
                    .addParameter("centreId", centreId)
                    .executeAndFetch(Degree.class);
        }

        return degrees;
    }

    public List<User> recognizers(Long centreId) {
        String sql = "SELECT " +
                "u.* " +
                "FROM " + CentreDao.tableName + " c " +
                "JOIN " + DegreeDao.tableName + " d ON d.centre_id = c.id " +
                "JOIN " + SubjectDao.tableName + " s ON s.degree_id = d.id " +
                "JOIN " + UserRecognizeSubjectDao.tableName + " urs ON urs.subject_id = s.id " +
                "JOIN " + UserDao.tableName + " u ON urs.user_id = u.id " +
                "WHERE c.id = :centreId " +
                "GROUP BY u.id";

        List<User> recognizers;
        try(Connection con = this.db.open()) {
            recognizers = con.createQuery(sql)
                    .addParameter("centreId", centreId)
                    .executeAndFetch(User.class);
        }

        return recognizers;
    }
}
