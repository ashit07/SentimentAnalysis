package config;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    private Properties properties = new Properties();
    public static final PropertyLoader propertyLoader = new PropertyLoader();
    private PropertyLoader() {
        loadProperties();
    }

    private void loadProperties() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream("properties/config.properties"));
        } catch (IOException e) {
            System.err.println("Got error while loading properties file: "+e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key, null);
    }


}
