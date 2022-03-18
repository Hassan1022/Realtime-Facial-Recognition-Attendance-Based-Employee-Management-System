package Model.employeeManagementSystem.connection;
import java.sql.*;
public class sqlConnection {
    //class that establishes the sql connection
    private static final String url = "jdbc:mysql://localhost:3306/employee_management_system", user = "root", password = "16101289";
    private static Connection connection = null;

    public static Connection getConnection() {
            try {
            connection = DriverManager.getConnection(url, user, password);

            }
            catch(SQLException e){
               e.printStackTrace();
            }

            finally {
                return connection;
            }

    }
}

