package Model.employeeManagementSystem.entities.department.daoDepartment.implementDepartment;

import Model.employeeManagementSystem.connection.sqlConnection;
import Model.employeeManagementSystem.entities.department.daoDepartment.departmentDaoInterface;
import Model.employeeManagementSystem.entities.department.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// implementing the methods to do crud operations for the department table
public class departmentDaoImplement implements departmentDaoInterface {
    private static Connection connection= sqlConnection.getConnection();
    private String query="";
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    // since the original var is declared as static we name it as static
    // made static in the first place so that we can access it easily
    // without creating a new obj everytime
    @Override
    public int insertDeptData(department d) throws SQLException {
        query="insert into Department (departmentId, employeeIdHead, departmentName,\n" +
                " location, totalEmployees) values " +
                "(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, d.getDepartmentId());
        preparedStatement.setInt(2, d.getEmployeeIdHead());
        preparedStatement.setString(3, d.getDepartmentName());
        preparedStatement.setString(4, d.getLocation());
        preparedStatement.setInt(5, d.getTotalEmployees());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateDeptData(department d) throws SQLException {
        query="update Department set employeeIdHead=?, departmentName=?," +
                "location=?, totalEmployees=? where departmentId=?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, d.getEmployeeIdHead());
        preparedStatement.setString(2, d.getDepartmentName());
        preparedStatement.setString(3, d.getLocation());
        preparedStatement.setInt(4, d.getTotalEmployees());
        preparedStatement.setInt(5, d.getDepartmentId());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int deleteDeptData(int departmentId) throws SQLException {
        query="delete from Department where departmentId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, departmentId);
        return preparedStatement.executeUpdate();

    }

    @Override
    public department retrieveDeptData(int departmentId) throws SQLException {
        department d=null;
        query="select * from Department where departmentId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, departmentId);

        resultSet=preparedStatement.executeQuery();

        while (resultSet.next()){
            // creating an department object wth the retrieved info
            // and passing it to the class which has called this method
            d= new department(departmentId, resultSet.getInt("employeeIdHead"),
                    resultSet.getInt("totalEmployees"),
                    resultSet.getString("departmentName"),
                    resultSet.getString("location"));

        }

        return d;
    }

    public int  retrieveId() throws SQLException{
        query="select count(departmentId) as id from Department";
        preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
        while(resultSet.next()){
            id= resultSet.getInt("id");
        }
        return id +1;
    }
}
