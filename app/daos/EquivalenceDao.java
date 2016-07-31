package daos;

import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

public class EquivalenceDao extends BaseDao {

    public static String tableName = "equivalence";

    public List<Map<String, Object>> getEquivalences(String subjectName) {
        String sql = "SELECT e.id, s.id AS subject_id, s.name AS subject_name, " +
                "se.id AS equivalent_id, se.name AS equivalent_name " +
                "FROM " + tableName + " AS e " +
                "JOIN subject AS s ON e.subject_id = s.id " +
                "JOIN subject AS se ON e.subject_eq_id = se.id " +
                "WHERE s.name LIKE :subjectName " +
                "OR se.name LIKE :subjectName";

        List<Map<String, Object>> result = null;
        try(Connection con = this.db.open()) {
            result = toMapList(con.createQuery(sql)
                    .addParameter("subjectName", "%" + subjectName + "%")
                    .executeAndFetchTable());
        }

        return result;
    }
}
