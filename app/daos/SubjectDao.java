package daos;

import models.Subject;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> index() {
        String sql = "SELECT " +
                "s.id, s.name, " +
                "d.name AS degree_name, u.name AS university_name, " +
                "c.name AS centre_name, cy.name AS country_name, " +
                "co.name AS country_name " +
                "FROM " + tableName + " s " +
                "JOIN " + DegreeDao.tableName + " d  ON s.degree_id = d.id " +
                "JOIN " + CentreDao.tableName + " c ON d.centre_id = c.id " +
                "JOIN " + UniversityDao.tableName + " u ON c.university_id = u.id " +
                "JOIN " + CityDao.tableName + " cy ON u.city_id = cy.id " +
                "JOIN " + CountryDao.tableName + " co ON cy.country_id = co.id";

        List<Map<String, Object>> subjects;
        try(Connection con = this.db.open()) {
            subjects = toMapList(con.createQuery(sql)
                    .executeAndFetchTable());
        }

        return subjects;
    }
}
