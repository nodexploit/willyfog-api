package daos;

import com.google.inject.Inject;
import org.sql2o.Sql2o;
import org.sql2o.data.Column;
import org.sql2o.data.Row;
import org.sql2o.data.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {

    @Inject
    protected Sql2o db;

    /**
     * Converts Table object to a List<Map<String, Object> that is Gson-serializable.
     * @param t
     * @return
     */
    protected List<Map<String, Object>> toMapList(Table t) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Row> rows = t.rows();
        List<Column> columns = t.columns();

        for (int i = 0; i < rows.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            for (int j = 0; j < columns.size(); j++) {
                map.put(columns.get(j).getName(), rows.get(i).getObject(j));
            }

            mapList.add(map);
        }

        return mapList;
    }
}
