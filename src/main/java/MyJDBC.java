import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJDBC {
    private static final String url = "jdbc:mysql://localhost:3306/bane";
    //fiv
    private static final String username = "root";

    private static final String password = "Dzharkynbaev27";
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username , password);
    }
}
