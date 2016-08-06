package daos;

import models.Centre;
import models.University;
import org.sql2o.Connection;

import java.util.List;

public class UniversityDao extends BaseDao {

    public static String tableName = "university";

    /**
     * TODO: handle pagination
     * @return
     */
    public List<University> all() {
        String sql = "SELECT " +
                "u.id, u.name, u.code " +
                "FROM " + tableName + " u " +
                "LIMIT 100";

        List<University> universities;
        try(Connection con = this.db.open()) {
            universities = con.createQuery(sql)
                    .executeAndFetch(University.class);
        }

        return universities;
    }

    public List<Centre> centres(Integer universityId) {
        String sql = "SELECT " +
                "c.id, c.name, c.code " +
                "FROM " + CentreDao.tableName + " c " +
                "WHERE c.university_id = :universityId";

        List<Centre> centres;
        try(Connection con = this.db.open()) {
            centres = con.createQuery(sql)
                    .addParameter("universityId", universityId)
                    .executeAndFetch(Centre.class);
        }

        return centres;
    }

    public List<University> cityUniversities(Integer cityId) {
        String sql = "SELECT " +
                "u.id, u.name, u.code " +
                "FROM " + tableName + " u " +
                "WHERE u.city_id = :cityId";

        List<University> universities;
        try(Connection con = this.db.open()) {
            universities = con.createQuery(sql)
                    .addParameter("cityId", cityId)
                    .executeAndFetch(University.class);
        }

        return universities;
    }
}
