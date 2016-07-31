import com.google.inject.AbstractModule;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.sql2o.Sql2o;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        Config conf = ConfigFactory.load();
        String host = conf.getString("database.host");
        String dbName = conf.getString("database.name");
        String dbUsername = conf.getString("database.username");
        String dbPassword = conf.getString("database.password");
        String dsn = "jdbc:mysql://" + host + ":3306/" + dbName;

        bind(Sql2o.class)
                .toInstance(new Sql2o(dsn, dbUsername, dbPassword));
    }
}
