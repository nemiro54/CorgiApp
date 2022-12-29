package com.andersen.corgiapp.connection;

import com.andersen.corgiapp.exception.FileNotValidException;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DatabaseProperties {

    protected String url;
    protected String username;
    protected String password;

    public DatabaseProperties() {
        init();
    }

    public void init() {
        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load("db.properties");
        } catch (ConfigurationException e) {
            throw new FileNotValidException("Something wrong with db.properties file");
        }
        this.url = config.getString("db.url");
        this.username = config.getString("db.user");
        this.password = config.getString("db.password");
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
