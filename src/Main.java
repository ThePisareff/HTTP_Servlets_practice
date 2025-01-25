import util.ConnectionManager;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (var connectionManager = ConnectionManager.open()) {
            System.out.println("1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}