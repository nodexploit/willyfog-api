package daos;

import models.City;
import models.RequestDestinationSubject;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RequestDestinationSubjectDao extends BaseDao {

    public static String tableName = "request_destination_subject";

    public List<Map<String, Object>> requestDestinations(Long requestId) {
        String sql = "SELECT " +
                "rds.subject_id, rds.uri, " +
                "IFNULL(rds.subject_name, s.name) AS subject_name, IFNULL(rds.subject_credits, s.credits) AS subject_credits, " +
                "IFNULL(rds.subject_code, s.code) AS subject_code, " +
                "IFNULL(rds.degree_name, d.name) AS degree, " +
                "IFNULL(rds.centre_name, c.name) AS centre, " +
                "IFNULL(rds.university_name, u.name) AS university, " +
                "IFNULL(rds.city_name, ct.name) AS city, " +
                "IFNULL(rds.country_name, cnt.name) AS country " +
                "FROM " + tableName + " rds " +
                "LEFT JOIN " + SubjectDao.tableName + " s ON rds.subject_id = s.id " +
                "LEFT JOIN " + DegreeDao.tableName + " d ON s.degree_id = d.id " +
                "LEFT JOIN " + CentreDao.tableName + " c ON d.centre_id = c.id " +
                "LEFT JOIN " + UniversityDao.tableName + " u ON c.university_id = u.id " +
                "LEFT JOIN " + CityDao.tableName + " ct ON u.city_id = ct.id " +
                "LEFT JOIN " + CountryDao.tableName + " cnt ON ct.country_id = cnt.id " +
                "WHERE rds.request_id = :requestId";

        List<Map<String, Object>> requestDestinationSubjects;
        try(Connection con = this.db.open()) {
            requestDestinationSubjects = toMapList(con.createQuery(sql)
                    .addParameter("requestId", requestId)
                    .executeAndFetchTable());
        }

        return requestDestinationSubjects;
    }

    public List<Long> create(List<RequestDestinationSubject> rdss) {
        List<Long> ids = new ArrayList<>();
        String sql = "INSERT INTO " + tableName + " " +
                "(request_id, subject_id, subject_name, subject_credits, subject_code, country_name, centre_name, " +
                "city_name, university_name, degree_name, uri) " +
                "VALUES (:requestId, :subjectId, :subjectName, :subjectCredits, :subjectCode, :countryName, " +
                ":centreName, :cityName, :universityName, :degreeName, :uri)";

        ids.addAll(rdss.stream().map(rds -> insertModel(sql, rds)).collect(Collectors.toList()));

        return ids;
    }
}
