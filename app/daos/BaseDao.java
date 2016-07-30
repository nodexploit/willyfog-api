package daos;

import com.google.inject.Inject;
import org.sql2o.Sql2o;

public class BaseDao {

    @Inject
    protected Sql2o db;
}
