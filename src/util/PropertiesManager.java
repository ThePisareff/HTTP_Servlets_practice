package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesManager(){}

    private static void loadProperties() {
        try (InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
}
