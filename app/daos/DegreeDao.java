package daos;

import models.Subject;
import org.sql2o.Connection;

import java.util.List;

public class DegreeDao extends BaseDao {

    public static String tableName = "degree";

    public List<Subject> subjects(Long degreeId) {
        String sql = "SELECT " +
                "s.id, s.code, s.name, s.credits " +
                "FROM " + SubjectDao.tableName + " s " +
                "WHERE s.degree_id = :degreeId";

        List<Subject> subjects;
        try(Connection con = this.db.open()) {
            subjects = con.createQuery(sql)
                    .addParameter("degreeId", degreeId)
                    .executeAndFetch(Subject.class);
        }

        return subjects;
    }
}
