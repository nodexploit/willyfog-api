package daos;

import models.Degree;
import org.sql2o.Connection;

import java.util.List;

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
}
