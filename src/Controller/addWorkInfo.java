package Controller;
import java.sql.*;
import Model.employeeManagementSystem.entities.project.project;
import Model.employeeManagementSystem.entities.worksOn.daoWorksOn.implementWorksOn.worksOnDaoImplement;
import Model.employeeManagementSystem.entities.worksOn.worksOn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class addWorkInfo implements Initializable {

    @FXML
    private TextField employeeId, pId, pRatings, hours;
    @FXML
    private DatePicker startDate;

    @FXML
    private Label message;
    private Stage profilStage;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addWorkInfo(ActionEvent event) throws ParseException {
        String d;
        int employeeId, pId;
        int pRatings;
        double hours;
        Date startDate;
        if (this.employeeId.getText() == null ||
                this.employeeId.getText() == "" || this.pRatings.getText() == null ||
                this.pRatings.getText() == "" || this.pId.getText() == null ||
                this.hours.getText() == "" || this.hours.getText() == "" || this.pId.getText() == ""
        ) {
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Employee Work Info! PLease fill all" +
                    "the fields properly and submit again.");
            return;
        }

        try {
            d = this.startDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            startDate =getDate(d);
        }

        catch (NullPointerException e){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Employee Work Info! PLease fill all" +
                    " the fields properly and submit again.");
            return;
        }
        try{

            employeeId= Integer.parseInt(this.employeeId.getText());
            pId= Integer.parseInt(this.pId.getText());
            pRatings=Integer.parseInt(this.pRatings.getText());
            hours=Integer.parseInt(this.hours.getText());

        }
        catch (NumberFormatException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Employee Work Info! Please fill all the fields properly");
            return;
        }


        try{
           worksOn wo=new worksOn(employeeId,pId, hours, pRatings, startDate);

            worksOnDaoImplement wd=new worksOnDaoImplement();

            wd.insertWorksOnData(wo);

        }
        catch(SQLException exception){
            message.setTextFill(Color.RED);
            message.setText("Unable to create a new Employee Work Info! Check the Employee Id and Project Id " +
                    "properly and resubmit.");
            return;
        }
        message.setTextFill(Color.GREEN);
        message.setText("Employee Work Info has been successfully added!!!");

    }

    public Date getDate (String date) throws ParseException {
        java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date date1 = new Date(d.getTime());
        System.out.println(date1);
        return date1;
    }

    public void setUpdatePromptStage(Stage primaryStage, Stage profileStage) {
            this.stage=primaryStage;
            this.profilStage=profileStage;
    }

    public void goBackToProfile(ActionEvent event){
            stage.close();
            profilStage.show();
    }
}
