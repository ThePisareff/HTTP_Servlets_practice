package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDrivers();
    }

    private ConnectionManager() {
    }

    private static void loadDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(PropertiesManager.get(URL_KEY),
                                               PropertiesManager.get(USERNAME_KEY),
                                               PropertiesManager.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
