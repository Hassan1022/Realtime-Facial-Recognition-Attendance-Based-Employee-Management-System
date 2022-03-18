package Model.employeeManagementSystem.entities.employee.daoEmployee;

import Model.employeeManagementSystem.entities.employee.employee;
import java.sql.SQLException;

public interface employeeDaoInterface {
    public  int insertEmployeeData(employee e) throws SQLException;
    public int updateEmployeeData(employee e) throws SQLException;
    public int deleteEmployeeData(int employeeId) throws SQLException;
    public employee retrieveEmployeeData(int employeeId) throws SQLException;
    public int  retrieveId() throws SQLException;

}
