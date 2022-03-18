package Controller;

import Model.employeeManagementSystem.entities.department.daoDepartment.implementDepartment.departmentDaoImplement;
import Model.employeeManagementSystem.entities.project.daoProject.implementProject.projectDaoImplement;
import Model.employeeManagementSystem.entities.project.project;
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
import org.w3c.dom.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class updateProjectsPrompt implements Initializable {
    private Stage stage;
    private Stage profileStage;
    private project p;

    @FXML
    private TextField updatedId;

    @FXML
    private Label errorMessage;

    private updateProjects uController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public boolean check() {
        if(updatedId.getText()==null || updatedId.getText()==""){
            return false;
        }

        try {
            projectDaoImplement d1 = new projectDaoImplement();
            p=d1.retrieveProjectData((Integer.parseInt(updatedId.getText())));
        }
        catch (SQLException exception){
            return false;
        }
        catch (NumberFormatException exception){
            return false;
        }
        return true;
    }





    public void updateProjects(ActionEvent event) throws Exception{
        if (!check()){
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Incorrect department Id! Please enter a valid Id.");
            return;
        }

        Stage primaryStage = new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/updateProjects.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Update Project Info");
        uController = (updateProjects) loader.getController();
        uController.setUpdateStage(primaryStage, profileStage, p);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        stage.close();
    }

    public void setUpdatePromptStage(Stage primaryStage, Stage profileStage) {
        this.stage=primaryStage;
        this.profileStage=profileStage;
    }
}
