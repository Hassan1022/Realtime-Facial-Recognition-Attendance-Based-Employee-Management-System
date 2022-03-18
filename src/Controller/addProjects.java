package Controller;

import Model.employeeManagementSystem.entities.department.department;
import Model.employeeManagementSystem.entities.project.daoProject.implementProject.projectDaoImplement;
import Model.employeeManagementSystem.entities.project.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addProjects implements Initializable {
    @FXML
    private TextField pName, pManId,deptId, aTime,cTime;
    @FXML
    private Label pId;
    @FXML
    private Label message;
    private int projectId;
    private projectDaoImplement pd;
    private Stage stage;
    private Stage profileStage;

    @Override


    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            pd=new projectDaoImplement();
            projectId=pd.retrieveId();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        pId.setText(projectId + "");
    }

    public void addProjects(ActionEvent event){

        String project;
        int pMan;
        String alTime,cmtime;
        int dId;

        if (pName.getText()==null ||
                pName.getText()=="" || deptId.getText()==null ||
                deptId.getText()=="" || aTime.getText()==null ||
                aTime.getText()=="" || pManId.getText()=="" || pManId.getText()==null){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Project! PLease fill all" +
                    "the fields properly and submit again.");
            return;
        }
        try{

            project= pName.getText();
            pMan=Integer.parseInt(pManId.getText());
            dId= Integer.parseInt(deptId.getText());
            alTime= aTime.getText();
            cmtime=cTime.getText();

        }
        catch (NumberFormatException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Project! Please fill all the fields properly");
            return;
        }


        try{
            project p=new project(projectId, pMan, dId, project, alTime, cmtime);

            pd.insertProjectData(p);

        }
        catch(SQLException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Project! Check the Project Manager Id and Department Id " +
                    "properly and resubmit.");
            return;
        }
        message.setTextFill(Color.GREEN);
        message.setText("Department addition successful!!!");

    }



    public void setUpdatePromptStage(Stage primaryStage, Stage profileStage) {
        this.stage=primaryStage;
        this.profileStage=profileStage;
    }

    public void goBackToProfile(ActionEvent event){
        stage.close();
        profileStage.show();

    }



}
