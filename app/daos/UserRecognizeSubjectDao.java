package daos;

import org.sql2o.Connection;

import java.util.List;

public class UserRecognizeSubjectDao extends BaseDao {

    public static String tableName = "user_recognize_subject";

    public List<Long> subjectRecognizers(Long subjectId) {
        String sql = "SELECT user_id " +
                "FROM " + tableName + " " +
                "WHERE subject_id = :subjectId";

        List<Long> recognizerIds;
        try(Connection con = this.db.open()) {
            recognizerIds = con.createQuery(sql)
                    .addParameter("subjectId", subjectId)
                    .executeScalarList(Long.class);
        }

        return recognizerIds;
    }

    public void deleteSubject(Long userId, Long subjectId) {
        String sql = "DELETE FROM " + tableName + " " +
                "WHERE user_id = :userId AND subject_id = :subjectId";

        try(Connection con = this.db.open()) {
            con.createQuery(sql)
                    .addParameter("userId", userId)
                    .addParameter("subjectId", subjectId)
                    .executeUpdate();
        }
    }
}
