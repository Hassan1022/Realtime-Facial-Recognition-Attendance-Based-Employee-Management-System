package Controller;

import Model.employeeManagementSystem.entities.attendance.initiateLiveAttendance;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewAttendancePrompt implements Initializable {
    private ArrayList<Integer> attendance;
    private ArrayList<Integer> lateCount;

    private int employeeId=0;
    @FXML
    private TextField eId;
    @FXML
    private Label errorMessage;
    private Stage update_prompt_stage;
    private Stage profileStage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public boolean check() throws ParseException{

        try {
            initiateLiveAttendance a = new initiateLiveAttendance();
            employeeId=Integer.parseInt(eId.getText());

            attendance =a.retreiveMonthAttendance(employeeId);

            lateCount =a.retreiveLateAttendance(employeeId);

            boolean present=false;
            for (int i=0; i<attendance.size(); i++){
                if (attendance.get(i)!=0){
                    present=true;
                }
            }
            if (!present){
                return false;
            }

        }

        catch (NumberFormatException exception){
            return false;
        }

        catch (SQLException exception){
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    public void updateFromPrompt(ActionEvent event) throws Exception{

        if (!check()){
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("This employee does not " +
                    "have an Attendance history. Please enter a valid Id.");
            return;
        }

        Stage primaryStage = new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/attendanceHistory.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Employee Attendance History");
        attendanceHistory aH = (attendanceHistory) loader.getController();
        aH.setUpdateStage(primaryStage, profileStage, attendance, lateCount,employeeId);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        update_prompt_stage.close();
    }

    public void setUpdatePromptStage(Stage stage, Stage profileStage){
        this.update_prompt_stage=stage;
        this.profileStage=profileStage;
    }

}
