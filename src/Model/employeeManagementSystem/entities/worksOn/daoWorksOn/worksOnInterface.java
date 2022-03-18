package Model.employeeManagementSystem.entities.worksOn.daoWorksOn;

import Model.employeeManagementSystem.entities.worksOn.worksOn;

import java.sql.SQLException;
import java.util.ArrayList;

public interface worksOnInterface {

    public int insertWorksOnData(worksOn works) throws SQLException;
    public int updateWorksOnData(worksOn work) throws SQLException;
    public int deleteWorksOnData(int employeeId, int projectId) throws SQLException;
    public ArrayList<worksOn> retrieveWorksOnData(int employeeId) throws SQLException;
    public worksOn retrieveWorksOnData(int employeeId, int projectId) throws SQLException;
}
