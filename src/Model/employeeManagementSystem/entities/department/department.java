package Model.employeeManagementSystem.entities.department;

public class department {
    private static int departmentCount=1;
    // used to automate the department id generation
    private int departmentId,employeeIdHead,totalEmployees;
    private String departmentName,location;

    public department(int departmentId, int employeeIdHead,
                      int totalEmployees, String departmentName,
                      String location)
    {
        this.departmentId = departmentId;
        this.employeeIdHead = employeeIdHead;
        this.totalEmployees = totalEmployees;
        this.departmentName = departmentName;
        this.location = location;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getEmployeeIdHead() {
        return employeeIdHead;
    }

    public void setEmployeeIdHead(int employeeIdHead) {
        this.employeeIdHead = employeeIdHead;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
