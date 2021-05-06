package ru.mngerasimenko.distancecalculator.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Settings {

    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String DB_LIMIT = "db.limit";


    public static final double EARTH_RADIUS = 6371.0;
    public static final double PI_180 = 3.1415926535897932384626433832795 / 180;

    public static final String ERROR_CITY_1 = "City names is empty";
    public static final String ERROR_COORDINATE_4 = "Coordinate is empty";
    public static final String ERROR_COORDINATE_3 = "The side of the world is not specified correctly";
    public static final String ERROR_COORDINATE_2 = "There is no side of the world in the coordinate";
    public static final String ERROR_COORDINATE_1 = "Coordinate format error";

    private static final Properties properties = new Properties();

    public synchronized static String getProperty(String name) {
        if (properties.isEmpty()) {
            try (InputStream is = Settings.class.getClassLoader().getResourceAsStream("config.properties")) {
                properties.load(is);
            } catch (IOException ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
        return properties.getProperty(name);
    }
}
