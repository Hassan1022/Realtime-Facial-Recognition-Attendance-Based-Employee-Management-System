package Model.employeeManagementSystem.entities.attendance.daoAttendance;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface initiateLiveAttendanceInterface {
    public void insert_attendance() throws Exception;
    public int  retreiveAttendance(int employeeId) throws SQLException, ParseException;
    public ArrayList<Integer>retreiveMonthAttendance(int employeeId) throws SQLException, ParseException;
}
