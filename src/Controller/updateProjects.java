package Controller;

import Model.employeeManagementSystem.entities.project.daoProject.implementProject.projectDaoImplement;
import Model.employeeManagementSystem.entities.project.project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;

public class updateProjects {
    private project p;
    private Stage profileStage;
    private Stage stage;
    private int projectId;

    @FXML
    private TextField pName, pManId,deptId, aTime,cTime;
    @FXML
    private Label pId;
    @FXML
    private Label message;


    private void setAllFields() {
        projectId=p.getProjectId();
        pName.setText(p.getProjectName());
        pManId.setText(p.getEmployeeIdManager() + "");
        deptId.setText(p.getAssociatedDeptId() + "");
        aTime.setText(p.getAllocatedTime());
        cTime.setText(p.getCompletionTime());

        pId.setText(p.getProjectId() + "");


    }




    public void setUpdateStage(Stage primaryStage, Stage profileStage, project p) {
        this.stage=primaryStage;
        this.profileStage=profileStage;
        this.p=p;
        setAllFields();
    }



    public void updateProjects(ActionEvent event){
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
            message.setText("Unable update the Project info! Please fill all the fields properly");
            return;
        }


        try{
            projectDaoImplement pd=new projectDaoImplement();
            project pr=new project(projectId, pMan, dId, project, alTime, cmtime);

            pd.updateProjectData(pr);

        }
        catch(SQLException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to update the Project info! Check the Project Manager Id and Department Id " +
                    "properly and resubmit.");
            return;
        }
        message.setTextFill(Color.GREEN);
        message.setText("Project has been successfully updated!!!");

    }

    public void goBackToProfile(ActionEvent event){
        stage.close();
        profileStage.show();

    }
}
