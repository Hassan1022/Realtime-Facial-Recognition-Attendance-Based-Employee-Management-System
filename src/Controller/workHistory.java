package Controller;

import Model.employeeManagementSystem.entities.worksOn.worksOn;
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

public class workHistory implements Initializable {
    private Stage profileStage;
    private Stage stage;
    private ArrayList<worksOn> works;

    @FXML
    private LineChart<String, Number> projectRatings;
    @FXML
    private LineChart<String, Double> pHoursWorked;

    @FXML
    private Label setEmployeeName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUpdateStage(Stage primaryStage, Stage profileStage, ArrayList<worksOn> w) {

            this.stage = primaryStage;
            this.profileStage = profileStage;
            this.works = w;
            setEmployeeName.setTextFill(Color.GREEN);
        try {
            setEmployeeName.setText("Work History of Employee with Id: " + works.get(0).getEmployeeId());
        }
        catch (Exception exception){
            setEmployeeName.setText("Sorry you don't have any Work History. ");
        }
    }

    public void projectRatingsShow(ActionEvent event) {
            projectRatings.getData().clear();
            pHoursWorked.getData().clear();
            XYChart.Series<String, Number> show = new XYChart.Series<String, Number>();
            XYChart.Series<String, Double> showHours = new XYChart.Series<String, Double>();
            worksOn work;
            try {
                for (int i = 0; i < works.size(); i++) {
                    work = works.get(i);
                    String projectId = "Project " + work.getProjectId();
                    int rating = work.getPerformanceRatings();
                    double workingHours = work.getHoursWorked();

                    show.getData().add(new XYChart.Data<String, Number>(projectId, rating));

                    showHours.getData().add(new XYChart.Data<String, Double>(projectId, workingHours));
                }
            }
            catch (NullPointerException exception){
                return;
            }
            show.setName("Project Ratings of Each Project");
            showHours.setName("Hours Worked on Each Project");
            projectRatings.getData().add(show);
            pHoursWorked.getData().add(showHours);
        }


    public void backToProfile(ActionEvent event){
        stage.close();
        profileStage.show();
    }

}