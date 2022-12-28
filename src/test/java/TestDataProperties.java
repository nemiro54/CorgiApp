import com.andersen.corgiapp.connection.DatabaseProperties;
import com.andersen.corgiapp.exception.FileNotValidException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class TestDataProperties extends DatabaseProperties {

    public TestDataProperties() {
        super();
    }

    @Override
    public void init() {
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load("src/test/resources/test-db.properties");
        } catch (ConfigurationException e) {
            throw new FileNotValidException("Something wrong with db.properties file");
        }
        this.url = config.getString("db.url");
        this.username = config.getString("db.user");
        this.password = config.getString("db.password");
    }
}
