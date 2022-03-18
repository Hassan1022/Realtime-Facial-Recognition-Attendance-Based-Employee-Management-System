package Model.employeeManagementSystem.entities.project.daoProject;

import Model.employeeManagementSystem.entities.project.project;

import java.sql.SQLException;

public interface projectDaoInterface {
    // This methods will be used by the subclass to do data manipulation for
    //the department table in the db

    public int insertProjectData(project p) throws SQLException;
    public int updateProjectData(project p) throws SQLException;
    public int deleteProjectData(int projectId) throws SQLException;
    public project retrieveProjectData(int projectId) throws SQLException;
    public int  retrieveId() throws SQLException;

}


