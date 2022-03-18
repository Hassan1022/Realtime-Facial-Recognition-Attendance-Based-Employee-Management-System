package Model.employeeManagementSystem.entities.worksOn.daoWorksOn.implementWorksOn;

import Model.employeeManagementSystem.connection.sqlConnection;
import Model.employeeManagementSystem.entities.worksOn.daoWorksOn.worksOnInterface;
import Model.employeeManagementSystem.entities.worksOn.worksOn;

import java.sql.*;
import java.util.ArrayList;

public class worksOnDaoImplement implements worksOnInterface {
    private static Connection connection = sqlConnection.getConnection();
    private String query = "";
    private PreparedStatement preparedStatement = null;

    @Override
    public int insertWorksOnData(worksOn works) throws SQLException {
        query = "insert into worksOn(employeeId, projectId, startDate, " +
                "hoursWorked, performanceRatings) values " +
                "(?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, works.getEmployeeId());
        preparedStatement.setInt(2, works.getProjectId());
        preparedStatement.setDate(3, (Date) works.getStartDate());
        preparedStatement.setDouble(4, works.getHoursWorked());
        preparedStatement.setInt(5, works.getPerformanceRatings());


        return preparedStatement.executeUpdate();


    }

    @Override
    public int updateWorksOnData(worksOn works) throws SQLException {
        query = "update worksOn set " + "startDate=?, hoursWorked=?, " +
                "performanceRatings=? where employeeId=? and projectId=?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setDate(1, (Date) works.getStartDate());
        preparedStatement.setDouble(2, works.getHoursWorked());
        preparedStatement.setInt(3, works.getPerformanceRatings());
        preparedStatement.setInt(4, works.getEmployeeId());
        preparedStatement.setInt(5, works.getProjectId());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int deleteWorksOnData(int employeeId, int projectId) throws SQLException {
        query = "delete from worksOn where employeeId=? and projectId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, projectId);

        return preparedStatement.executeUpdate();

    }

    // first overloading method here is used to retrieve project info
    // about all the projects of an employee
    // and second one is used to retrieve specific project info of an employee

    // retieving project history of an employee
    public ArrayList<worksOn>  retrieveWorksOnData(int employeeId) throws SQLException {
        // creating an arraylist for storing all the project info of an employee
        ArrayList<worksOn>worksOnList= new ArrayList<>();

        worksOn w=null;

        query = "select * from worksOn where employeeId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, employeeId);


        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // creating an department object wth the retrieved info
            // and passing it to the class which has called this method
            w = new worksOn(employeeId, resultSet.getInt("projectId"),
                    resultSet.getDouble("hoursWorked"),
                    resultSet.getInt("performanceRatings"),
                    (Date) resultSet.getDate("startDate"));
            // adding each project info of the employee as a separate worksOn object
            // in the arraylist in order to retrieve all the project history of an employee
            worksOnList.add(w);
            // here we are retrieving each project info of the employee in each iteration and
            // storing it into the arraylist
        }

        return worksOnList;
    }


    @Override
    public worksOn retrieveWorksOnData(int employeeId, int projectId) throws SQLException {
        worksOn w = null;
        query = "select * from worksOn where employeeId=? and projectId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, employeeId);
        preparedStatement.setInt(2, projectId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            // creating an department object wth the retrieved info
            // and passing it to the class which has called this method
            w = new worksOn(employeeId, projectId,
                    resultSet.getDouble("hoursWorked"),
                    resultSet.getInt("performanceRatings"),
                    (Date) resultSet.getDate("startDate"));
        }
        return w;
    }
}
