import com.google.inject.AbstractModule;
import org.sql2o.Sql2o;

public class Module extends AbstractModule {

    @Override
    protected void configure() {
        bind(Sql2o.class)
                .toInstance(new Sql2o("jdbc:mysql://localhost:3306/willyfog_db", "root", "root"));
    }
}
