package TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/global.properties");
            prop.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar global.properties", e);
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
