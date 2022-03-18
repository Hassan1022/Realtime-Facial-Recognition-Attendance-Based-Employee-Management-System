package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class attendanceHistory implements Initializable {
    private Stage profileStage;
    private Stage stage;
    private ArrayList<Integer> attendance;
    private ArrayList<Integer> lateCount;
    private int employeeId;

    private String [] month={"January", "February", "March", " April", "" +
        "May", "June", "July", "August", "September", "October", "November" , "December",};

    @FXML
    private LineChart<String, Number> projectRatings;

    @FXML
    private LineChart<String, Number> lateAttendance;

    @FXML
    private Label setEmployeeName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUpdateStage(Stage primaryStage, Stage profileStage, ArrayList<Integer> attendance, ArrayList<Integer> lateCount, int employeeId) {

        this.stage = primaryStage;
        this.profileStage = profileStage;
        this.attendance = attendance;
        this.lateCount=lateCount;
        this.employeeId=employeeId;

        setEmployeeName.setTextFill(Color.GREEN);
        try {
            setEmployeeName.setText("Attendance History of Employee with Id: " + this.employeeId);
        }
        catch (NullPointerException exception){
            setEmployeeName.setText("Sorry employee " + this.employeeId  + " don't have any Work History. ");
        }
    }

    public void attendanceShow(ActionEvent event) {

        projectRatings.getData().clear();
        lateAttendance.getData().clear();


        XYChart.Series<String, Number> show = new XYChart.Series<String, Number>();
        XYChart.Series<String, Number> show2 = new XYChart.Series<String, Number>();
        int attendanceNumber;
        int late;

        try {
            for (int i = 0; i < attendance.size(); i++) {
                attendanceNumber = attendance.get(i);
                late=lateCount.get(i);
                show.getData().add(new XYChart.Data<String, Number>("Month: " + month[i], attendanceNumber));
                show2.getData().add(new XYChart.Data<String, Number>("Month: " + month[i], late));
            }
        }
        catch (NullPointerException exception){
            return;
        }
        show.setName("Days Present in the previous months of this year.");
        projectRatings.getData().add(show);

        show2.setName("Total Late attendance given in the previous months of this year.");
        lateAttendance.getData().add(show2);

    }


    public void backToProfile(ActionEvent event){
        stage.close();
        profileStage.show();
    }
}
