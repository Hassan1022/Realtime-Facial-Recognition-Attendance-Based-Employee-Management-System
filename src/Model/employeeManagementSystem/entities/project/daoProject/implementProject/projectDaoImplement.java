package Model.employeeManagementSystem.entities.project.daoProject.implementProject;

import Model.employeeManagementSystem.connection.sqlConnection;
import Model.employeeManagementSystem.entities.project.daoProject.projectDaoInterface;
import Model.employeeManagementSystem.entities.project.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class projectDaoImplement implements projectDaoInterface  {
    private static Connection connection= sqlConnection.getConnection();
    private String query="";
    private PreparedStatement preparedStatement=null;

    @Override
    public int insertProjectData(project p) throws SQLException {
        query="insert into Project (projectId, employeeIdManager, associatedDeptId, " +
                " projectName, allocatedTime, completionTime) values " +
                "(?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, p.getProjectId());
        preparedStatement.setInt(2, p.getEmployeeIdManager());
        preparedStatement.setInt(3, p.getAssociatedDeptId());
        preparedStatement.setString(4, p.getProjectName());
        preparedStatement.setString(5, p.getAllocatedTime());
        preparedStatement.setString(6, p.getCompletionTime());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int updateProjectData(project p) throws SQLException {
        query="update Project set employeeIdManager=?, associatedDeptId=?, " +
                "projectName=?, allocatedTime=?, completionTime=? where projectId=?";
        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, p.getEmployeeIdManager());
        preparedStatement.setInt(2, p.getAssociatedDeptId());
        preparedStatement.setString(3, p.getProjectName());
        preparedStatement.setString(4, p.getAllocatedTime());
        preparedStatement.setString(5, p.getCompletionTime());
        preparedStatement.setInt(6, p.getProjectId());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int deleteProjectData(int projectId) throws SQLException {
        query="delete from Project where projectId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, projectId);
        return preparedStatement.executeUpdate();
    }

    @Override
    public project retrieveProjectData(int projectId) throws SQLException {
        project p=null;
        query="select * from Project where projectId=?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, projectId);

        ResultSet resultSet=preparedStatement.executeQuery();

        while (resultSet.next()){
            // creating an department object wth the retrieved info
            // and passing it to the class which has called this method
            p= new project(projectId, resultSet.getInt("employeeIdManager"),
                    resultSet.getInt("associatedDeptId"),
                    resultSet.getString("projectName"),
                    resultSet.getString("allocatedTime"),
                    resultSet.getString("completionTime"));

        }
        return p;
    }

    public int  retrieveId() throws SQLException{
        query="select count(projectId) as id from Project";
        preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet=preparedStatement.executeQuery();
        int id=0;
        while(resultSet.next()){
            id= resultSet.getInt("id");
        }
        return id +1;
    }
}
