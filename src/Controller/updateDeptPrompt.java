package Controller;

import Model.employeeManagementSystem.entities.department.daoDepartment.implementDepartment.departmentDaoImplement;

import Model.employeeManagementSystem.entities.department.department;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateDeptPrompt implements Initializable {

@FXML
private TextField updatedId;
@FXML
private Label errorMessage;
private department d=null;
//private updateController update;
private Stage update_prompt_stage;
private upDeptController uController;
private Stage profileStage;

@Override
public void initialize(URL url, ResourceBundle resourceBundle) {

        }
// used to check whether that id exists in the db and illegal values
public boolean check() {
        if(updatedId.getText()==null || updatedId.getText()==""){
                return false;
        }

        try {
                departmentDaoImplement d1 = new departmentDaoImplement();
                d=d1.retrieveDeptData((Integer.parseInt(updatedId.getText())));
                if (d==null){
                        return false;
                }
        }
        catch (SQLException exception){
            return false;
        }
        catch (NumberFormatException exception){
            return false;
        }

        return true;
        }


public void updateFromPrompt(ActionEvent event) throws Exception{

        if (!check()){
                errorMessage.setTextFill(Color.RED);
                errorMessage.setText("Incorrect department Id! Please enter a valid Id.");
                return;
        }

        Stage primaryStage = new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/updept.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Update Department Info");
        uController = (upDeptController) loader.getController();
        uController.setUpdateStage(primaryStage, profileStage, d);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        update_prompt_stage.close();
        }

public void setUpdatePromptStage(Stage stage, Stage profileStage){
        this.update_prompt_stage=stage;
        this.profileStage=profileStage;
        }
}