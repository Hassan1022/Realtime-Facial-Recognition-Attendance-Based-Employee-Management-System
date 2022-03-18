package Model.employeeManagementSystem.entities.department.daoDepartment;
import Model.employeeManagementSystem.entities.department.department;

import java.sql.SQLException;


public interface departmentDaoInterface {
// This methods will be used by the subclass to do data manipulation for
//the department table in the db

    public int insertDeptData(department d) throws SQLException;
    public int updateDeptData(department d) throws SQLException;
    public int deleteDeptData(int departmentId) throws SQLException;
    public department retrieveDeptData(int departmentId) throws SQLException;
    public int  retrieveId() throws SQLException;

}
