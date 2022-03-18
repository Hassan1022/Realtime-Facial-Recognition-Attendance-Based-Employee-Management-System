package Model.employeeManagementSystem.entities.worksOn;

import java.util.Date;

public class worksOn {
    private int  employeeId, projectId, performanceRatings;
    private double hoursWorked;

    private Date startDate;

    public worksOn(int employeeId, int projectId, double hoursWorked,
                   int performanceRatings, Date startDate)
    {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.hoursWorked = hoursWorked;
        this.performanceRatings = performanceRatings;
        this.startDate = startDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public int getPerformanceRatings() {
        return performanceRatings;
    }

    public void setPerformanceRatings(int performanceRatings) {
        this.performanceRatings = performanceRatings;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
