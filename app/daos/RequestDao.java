package daos;

import models.Request;
import org.sql2o.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDao extends BaseDao {

    public static String tableName = "request";

    public Map<String, Object> find(Long id) {
        String sql = "SELECT " +
                "r.id, r.student_id, " +
                "s.id AS subject_id, s.code AS subject_code, s.name AS subject_name, " +
                "mt.id AS mobility_type_id, mt.name AS mobility_type, " +
                "d.id AS degree_id, d.name AS degree_name, " +
                "c.id AS centre_id, c.name AS centre_name, " +
                "u.id AS university_id, u.name AS university_name " +
                "FROM " + tableName + " r " +
                "JOIN " + SubjectDao.tableName + " s ON r.origin_subject_id = s.id " +
                "JOIN " + MobilityTypeDao.tableName + " mt ON r.mobility_type_id = mt.id " +
                "JOIN " + DegreeDao.tableName + " d ON s.degree_id = d.id " +
                "JOIN " + CentreDao.tableName + " c ON d.centre_id = c.id " +
                "JOIN " + UniversityDao.tableName + " u ON c.university_id = u.id " +
                "WHERE r.id = :id";

        List<Map<String, Object>> requests;
        try(Connection con = this.db.open()) {
            requests = toMapList(con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchTable());
        }

        Map<String, Object> request;
        if (requests.size() > 0) {
            request = requests.get(0);
        } else {
            request = new HashMap<>();
        }

        return request;
    }

    public List<Map<String, Object>> index() {
        String sql = "SELECT " +
                "r.id, " +
                "r.origin_subject_id AS subject_id, s.code AS subject_code, s.name AS subject_name " +
                "FROM " + tableName + " r " +
                "JOIN " + SubjectDao.tableName + " s ON r.origin_subject_id = s.id " +
                "ORDER BY r.updated_at";

        List<Map<String, Object>> requests;
        try(Connection con = this.db.open()) {
            requests = toMapList(con.createQuery(sql)
                    .executeAndFetchTable());
        }

        return requests;
    }

    public List<Map<String, Object>> studentRequests(Long userId) {
        String sql = "SELECT " +
                "r.id, " +
                "r.origin_subject_id AS subject_id, s.code AS subject_code, s.name AS subject_name " +
                "FROM " + tableName + " r " +
                "JOIN " + SubjectDao.tableName + " s ON r.origin_subject_id = s.id " +
                "WHERE student_id = :userId " +
                "ORDER BY r.updated_at";

        List<Map<String, Object>> requests;
        try(Connection con = this.db.open()) {
            requests = toMapList(con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchTable());
        }

        return requests;
    }

    public List<Map<String, Object>> recognizerRequests(Long userId) {
        String sql = "SELECT " +
                "r.id, " +
                "r.origin_subject_id AS subject_id, s.code AS subject_code, s.name AS subject_name " +
                "FROM " + tableName + " r " +
                "JOIN " + SubjectDao.tableName + " s ON r.origin_subject_id = s.id " +
                "WHERE r.origin_subject_id IN (" +
                "   SELECT urs.subject_id " +
                "   FROM " + UserRecognizeSubjectDao.tableName + " urs " +
                "   WHERE urs.user_id = :userId" +
                ") " +
                "ORDER BY r.updated_at";

        List<Map<String, Object>> requests;
        try(Connection con = this.db.open()) {
            requests = toMapList(con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetchTable());
        }

        return requests;
    }

    public Long create(Request request) {
        String sql = "INSERT INTO " + tableName + " " +
                "(student_id, origin_subject_id, mobility_type_id) " +
                "VALUES (:studentId, :originSubjectId, :mobilityTypeId)";

        return insertModel(sql, request);
    }
}
