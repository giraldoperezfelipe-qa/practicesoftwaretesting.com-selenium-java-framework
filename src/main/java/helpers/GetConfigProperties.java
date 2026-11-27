package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class GetConfigProperties {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = loadProperties();

    private GetConfigProperties() {
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = GetConfigProperties.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException(CONFIG_FILE
                        + " not found on classpath. Copy config.example.properties to "
                        + CONFIG_FILE + " in src/test/resources.");
            }
            props.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }
        return props;
    }

    public static String get(String key) {
        String value = System.getProperty(key);
        if (isBlank(value)) {
            value = System.getenv(toEnvName(key));
        }
        if (isBlank(value)) {
            value = PROPERTIES.getProperty(key);
        }
        if (isBlank(value)) {
            throw new IllegalStateException("Missing config value for key: " + key);
        }
        return value.trim();
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    private static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    private static String toEnvName(String key) {
        // user.admin.password -> USER_ADMIN_PASSWORD
        return key.replace('.', '_').toUpperCase();
    }
}