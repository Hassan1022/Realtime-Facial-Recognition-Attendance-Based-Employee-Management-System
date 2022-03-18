package Controller;

import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.worksOn.daoWorksOn.implementWorksOn.worksOnDaoImplement;
import Model.employeeManagementSystem.entities.worksOn.worksOn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewWorkPrompt implements Initializable {
    private ArrayList<worksOn> w;

    @FXML
    private TextField eId;
    @FXML
    private Label errorMessage;
    private Stage update_prompt_stage;
    private Stage profileStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // used to check whether that id exists in the db
    public boolean check() {

        try {
            worksOnDaoImplement wo = new worksOnDaoImplement();
            w=wo.retrieveWorksOnData(Integer.parseInt(eId.getText()));
            if(w.isEmpty()){
                return false;
            }
        }

        catch (SQLException exception){
            return false;
        }
        return true;
    }


    public void updateFromPrompt(ActionEvent event) throws Exception{

        if (!check()){
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("This employee does not " +
                    "have a work history. Please enter a valid Id.");
            return;
        }

        Stage primaryStage = new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/workHistory.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Employee Work History");
        workHistory wh = (workHistory) loader.getController();
        wh.setUpdateStage(primaryStage, profileStage, w);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        update_prompt_stage.close();
    }

    public void setUpdatePromptStage(Stage stage, Stage profileStage){
        this.update_prompt_stage=stage;
        this.profileStage=profileStage;
    }
}
