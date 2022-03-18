package Model.employeeManagementSystem.entities.project;

public class project {
    private static int projectCount=1;
    //automated project Id genrator
    private int projectId, employeeIdManager, associatedDeptId;
    private String projectName, allocatedTime, completionTime;

    public project(int projectId, int employeeIdManager,
                   int associatedDeptId, String projectName,
                   String allocatedTime, String completionTime)
    {
        this.projectId = projectId;
        this.employeeIdManager = employeeIdManager;
        this.associatedDeptId = associatedDeptId;
        this.projectName = projectName;
        this.allocatedTime = allocatedTime;
        this.completionTime = completionTime;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getEmployeeIdManager() {
        return employeeIdManager;
    }

    public void setEmployeeIdManager(int employeeIdManager) {
        this.employeeIdManager = employeeIdManager;
    }

    public int getAssociatedDeptId() {
        return associatedDeptId;
    }

    public void setAssociatedDeptId(int associatedDeptId) {
        this.associatedDeptId = associatedDeptId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAllocatedTime() {
        return allocatedTime;
    }

    public void setAllocatedTime(String allocatedTime) {
        this.allocatedTime = allocatedTime;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }
}
