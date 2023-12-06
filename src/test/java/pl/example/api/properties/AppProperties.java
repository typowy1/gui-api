package pl.example.api.properties;

import static propertiesConfig.ConfigurationProperties.getProperty;

public class AppProperties {
    private static final String APP_KEY = "app.key";
    private static final String BASE_URI = "base.uri";
    private static final String API_PROPERTY_NAME = "api";


    public static String getAppKey() {
        if (getKeyFromApiProperty(APP_KEY).isEmpty() || getKeyFromApiProperty(APP_KEY).startsWith("YOUR")) {
            return System.getProperty("APP_KEY");//dodane w jeninsie jako zmienne systemowe
        } else {
            return getKeyFromApiProperty(APP_KEY);
        }
    }

    public static String getBaseUri() {
        return getKeyFromApiProperty(BASE_URI);
    }

    public static String getKeyFromApiProperty(String key) {
        return getProperty(API_PROPERTY_NAME, key);
    }
}
