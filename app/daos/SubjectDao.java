package daos;

import models.Subject;
import org.sql2o.Connection;

import java.util.List;

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

    public List<Subject> recognizerSubjects(Long userId) {
        String sql = "SELECT s.* " +
                "FROM " + tableName + " s " +
                "JOIN " + UserRecognizeSubjectDao.tableName + " urs ON urs.subject_id = s.id " +
                "WHERE urs.user_id = :userId";

        List<Subject> subjects;
        try(Connection con = this.db.open()) {
            subjects = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetch(Subject.class);
        }

        return subjects;
    }
}
