package Controller;

import Model.employeeManagementSystem.entities.department.daoDepartment.implementDepartment.departmentDaoImplement;
import Model.employeeManagementSystem.entities.department.department;
import Model.employeeManagementSystem.entities.employee.employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class upDeptController implements Initializable {
    @FXML
    private TextField headId,dName,location, totalEmployees;
    @FXML
    private Label dId, message;
    private department d;
    private Stage stage, profileStage;
    private int deptId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void update(ActionEvent event) throws Exception{

        //profileStage.close();

        String deptName;
        int dHead;
        String locations;
        int total;

        if (headId.getText()==null ||
                headId.getText()=="" || dName.getText()==null ||
                dName.getText()=="" || location.getText()==null ||
                location.getText()=="" || totalEmployees.getText()==null
                || totalEmployees.getText()==""){
            message.setTextFill(Color.RED);
            message.setText("Unable to update Department! PLease fill all" +
                    "the fields properly and submit again.");
            return;
        }
        try{

            deptName= dName.getText();
            dHead=Integer.parseInt(headId.getText());
            locations= location.getText();
            total= Integer.parseInt(totalEmployees.getText());

        }
        catch (NumberFormatException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to  update the Department! Please place numbers " +
                    "at Department Id, Department Head and Total Employees");
            return;
        }


        try{
            departmentDaoImplement di =new departmentDaoImplement();
            department d=new department(deptId, dHead, total,
                    deptName, locations);

            di.updateDeptData(d);

        }
        catch(SQLException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to update Department! Please re submit.");
            return;
        }
        message.setTextFill(Color.GREEN);
        message.setText("Department info has been successfully updated!");
    }

    public void setUpdateStage(Stage primaryStage, Stage profileStage, department d) {
        this.stage=primaryStage;
        this.profileStage=profileStage;
        this.d=d;
        setFields();
    }
    public void setFields(){
        this.deptId=d.getDepartmentId();
        dId.setText(d.getDepartmentId()  + "");
        headId.setText(d.getEmployeeIdHead() + "");
        dName.setText(d.getDepartmentName());
        location.setText(d.getLocation());
        totalEmployees.setText(d.getTotalEmployees() + "");

    }
    public void goBackToProfile(ActionEvent event){
        stage.close();
        profileStage.show();
    }
}
