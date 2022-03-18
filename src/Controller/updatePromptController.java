package Controller;


import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
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

public class updatePromptController implements Initializable {
    @FXML
    private TextField updatedId;
    @FXML
    private Label errorMessage;
    private employee e =null;
    private updateController update;
    private Stage update_prompt_stage;
    private updateController uController;
    private Stage profileStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    // used to check whether that id exists in the db
    public boolean check() {

        try {
            employeeDaoImplement e1 = new employeeDaoImplement();
            e=e1.retrieveEmployeeData(Integer.parseInt(updatedId.getText()));
        }

        catch (NumberFormatException exception){
            return false;
        }

        catch (SQLException exception){
            return false;
        }
        return true;
    }


    public void updateFromPrompt(ActionEvent event) throws Exception{

        if (!check()){
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Incorrect employee Id! Please enter a valid Id.");
            return;
        }

        Stage primaryStage = new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/update.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Update Employee Info");
        uController = (updateController) loader.getController();
        uController.setUpdateStage(primaryStage, profileStage, e);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        update_prompt_stage.close();
    }

    public void setUpdatePromptStage(Stage stage, Stage profileStage){
        this.update_prompt_stage=stage;
        this.profileStage=profileStage;
    }
}
