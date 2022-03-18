package Controller;

import Model.employeeManagementSystem.entities.department.daoDepartment.implementDepartment.departmentDaoImplement;
import Model.employeeManagementSystem.entities.department.department;
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

public class addDepartment implements Initializable {
    @FXML
    private TextField headId,dName,location, totalEmployees;
    @FXML
    private Label  dId, message;
    private Stage stage;
    private Stage profileStage;
    private  departmentDaoImplement di;
    private int deptId;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            di=new departmentDaoImplement();
            deptId=di.retrieveId();
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        dId.setText(deptId + "");
    }


    public void addDepartment(ActionEvent event){

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
                message.setText("Unable to create a new Department! PLease fill all" +
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
            message.setText("Unable to create a new Department! Please place numbers " +
                            "at Department Id, Department Head Id and Total Employees");
            return;
        }


        try{
            department d=new department(deptId, dHead, total,
                    deptName, locations);

            di.insertDeptData(d);

        }
        catch(SQLException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Department! Please re submit.");
            return;
        }
        message.setTextFill(Color.GREEN);
        message.setText("Department addition successful!");

    }

    public void setStage(Stage stage, Stage profileStage){
        this.stage=stage;
        this.profileStage=profileStage;
    }

    public void backtoProfile(){
        stage.close();
        profileStage.show();
    }


}
