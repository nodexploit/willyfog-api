package daos;

import models.University;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

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
}
