package daos;

import models.Country;
import org.sql2o.Connection;

import java.util.List;

public class CountryDao extends BaseDao {

    public static String tableName = "country";

    public List<Country> all() {
        String sql = "SELECT " +
                "id, name, code " +
                "FROM " + tableName;

        List<Country> countries;
        try(Connection con = this.db.open()) {
            countries = con.createQuery(sql)
                    .executeAndFetch(Country.class);
        }

        return countries;
    }
}
