import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import org.sql2o.Sql2o;
import play.Configuration;

public class Module extends AbstractModule {

    @Inject
    private Configuration configuration;

    @Override
    protected void configure() {
        String host = configuration.getString("database.host");
        String dbName = configuration.getString("database.name");
        String dbUsername = configuration.getString("database.username");
        String dbPassword = configuration.getString("database.password");
        String dsn = "jdbc:mysql://" + host + ":3306/" + dbName;

        bind(Sql2o.class)
                .toInstance(new Sql2o(dsn, dbUsername, dbPassword));
    }
}
