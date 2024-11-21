import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException {
        // Update with your database credentials
        String url = "jdbc:mysql://localhost:3307/railway";
        String username = "root";
        String password = ""; // Your MySQL password if any

        return DriverManager.getConnection(url, username, password);
    }
}
