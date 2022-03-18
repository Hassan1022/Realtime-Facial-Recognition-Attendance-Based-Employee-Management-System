package Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee;
// importing the interface that we have to implement
import Model.employeeManagementSystem.entities.employee.daoEmployee.employeeDaoInterface;
// importing the connection class for connecting to the db
import Model.employeeManagementSystem.connection.sqlConnection;
// importing the employee class to use the employee object to store and retrieve data
import Model.employeeManagementSystem.entities.employee.employee;
import Model.employeeManagementSystem.entities.employee.employeeImage;

import java.io.FileInputStream;
import java.sql.*;

public class employeeDaoImplement implements employeeDaoInterface {
    // since the original var is declared as static we name it as static
    // made static in the first place so that we can access it easily
    // without creating a new obj everytime
    private static Connection connection=sqlConnection.getConnection();
    private String query="";
    private PreparedStatement preparedStatement;
    @Override
    public int insertEmployeeData(employee e) throws SQLException {
         query="insert into Employees (employeeId, departmentId, age, salary,\n" +
                " firstName, middleName, lastName, email, sex,\n" +
                " phone,address, designation,nationalId,\n" +
                " employeeType, relativeName, relation,relativePhone,\n" +
                " employeePhoto, birthDate, password) values " +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        preparedStatement = connection.prepareStatement(query);

        // time to set the values in the blank spots
        preparedStatement.setInt(1, e.getEmployeeId());
        preparedStatement.setInt(2, e.getDeptId());
        preparedStatement.setInt(3, e.getAge());
        preparedStatement.setInt(4, e.getSalary());
        preparedStatement.setString(5, e.getFirstName());
        preparedStatement.setString(6, e.getMiddleName());
        preparedStatement.setString(7, e.getLastName());
        preparedStatement.setString(8, e.getEmail());
        preparedStatement.setString(9, e.getSex());
        preparedStatement.setString(10, e.getPhone());
        preparedStatement.setString(11, e.getAdress());
        preparedStatement.setString(12, e.getDesignation());
        preparedStatement.setString(13, e.getNationalId());
        preparedStatement.setInt(14, e.getEmployeeType());
        preparedStatement.setString(15, e.getRelativeName());
        preparedStatement.setString(16, e.getRelation());
        preparedStatement.setString(17, e.getRelativePhone());
        preparedStatement.setBinaryStream(18, e.getEmployeePhoto().getImage());
        preparedStatement.setDate(19, e.getBirthDate());
        preparedStatement.setString(20, e.getPassword());


        // executing the query
        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateEmployeeData(employee e) throws SQLException{
        query="update Employees set departmentId=?, age=?, salary=?,\n" +
                " firstName=?, middleName=?, lastName=?, email=?, sex=?,\n" +
                " phone=?,address=?, designation=?,nationalId=?,\n" +
                " employeeType=?, relativeName=?, relation=?,relativePhone=?,\n" +
                " employeePhoto=?, birthDate=?, password=? where employeeId=?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, e.getDeptId());
        preparedStatement.setInt(2, e.getAge());
        preparedStatement.setInt(3, e.getSalary());
        preparedStatement.setString(4, e.getFirstName());
        preparedStatement.setString(5, e.getMiddleName());
        preparedStatement.setString(6, e.getLastName());
        preparedStatement.setString(7, e.getEmail());
        preparedStatement.setString(8, e.getSex());
        preparedStatement.setString(9, e.getPhone());
        preparedStatement.setString(10, e.getAdress());
        preparedStatement.setString(11, e.getDesignation());
        preparedStatement.setString(12, e.getNationalId());
        preparedStatement.setInt(13, e.getEmployeeType());
        preparedStatement.setString(14, e.getRelativeName());
        preparedStatement.setString(15, e.getRelation());
        preparedStatement.setString(16, e.getRelativePhone());
        preparedStatement.setBinaryStream(17, e.getEmployeePhoto().getImage());
        preparedStatement.setDate(18, e.getBirthDate());
        preparedStatement.setString(19,e.getPassword());

        preparedStatement.setInt(20, e.getEmployeeId());


        return preparedStatement.executeUpdate();
    }

    @Override
    public int deleteEmployeeData(int employeeId) throws SQLException{
        query="delete from Employees where employeeId=?";
        try {
            preparedStatement = connection.prepareStatement(query);
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
        preparedStatement.setInt(1, employeeId);
        return preparedStatement.executeUpdate();
    }

    @Override
    public employee retrieveEmployeeData(int employeeId) throws  SQLException{
        employee e=null;
        query="select * from Employees where employeeId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, employeeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            // creating an employee object and passing it to the class which has called this method
            e= new employee(employeeId, resultSet.getInt("departmentId"),resultSet.getInt("age") ,
                    resultSet.getInt("salary"), resultSet.getString("firstName"),
                    resultSet.getString("middleName"), resultSet.getString("lastName"),
                    resultSet.getString("email"),resultSet.getString("sex"),
                    resultSet.getString("phone"),resultSet.getString("address"),
                    resultSet.getString("designation"),resultSet.getString("nationalId"),
                    resultSet.getInt("employeeType"),resultSet.getString("relativeName"),
                    resultSet.getString("relation"),resultSet.getString("relativePhone"),
                    new employeeImage(resultSet.getBinaryStream("employeePhoto")), resultSet.getDate("birthDate"),
                    resultSet.getString("password"));
        }
        return e;
    }
    public int  retrieveId() throws SQLException{
        query="select count(employeeId) as id from Employees";
        preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
        while(resultSet.next()){
            id= resultSet.getInt("id");
        }
        return id +1;
    }

}
