package helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class GetConfigProperties {
    private static final String BASE_URL = "base.url";
    private static final Properties PROPERTIES = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = GetConfigProperties.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config.properties", e);
        }
        return props;
    }

    public static String getBaseUrl() {
        String systemOverride = System.getProperty(BASE_URL);
        if (systemOverride != null && !systemOverride.isBlank()) {
            return systemOverride;
        }
        return PROPERTIES.getProperty(BASE_URL);
    }
}
