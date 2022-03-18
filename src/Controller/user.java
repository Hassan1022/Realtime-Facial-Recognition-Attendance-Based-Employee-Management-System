package Controller;

import Model.employeeManagementSystem.entities.attendance.initiateLiveAttendance;
import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
import Model.employeeManagementSystem.entities.worksOn.daoWorksOn.implementWorksOn.worksOnDaoImplement;
import Model.employeeManagementSystem.entities.worksOn.worksOn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class user implements Initializable {
    private Stage prevStage;
    private Stage primaryStage;
    private int emplloyeeId;
    private Stage loginStage;
    private Stage profileStage;
    private InputStream input;

    private ArrayList<Integer> attendance;
    private ArrayList<Integer> lateCount;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label employeeName, employeeId, age, gender, phone, email, address, nId, dob,
            relativeName, relation, relativePhone;
    @FXML
    private Label departmentId, designation, salary, absentLm;
    @FXML
    private ImageView employeePhoto;

    private int id;

    private ArrayList<worksOn> w;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showEmployeeInfo(int id, Stage stage, Stage login) throws Exception {
        this.id=id;
        profileStage=stage;
        loginStage=login;
        // importing the model class that communicates with the employee table
        // and returns the employee info
        employeeDaoImplement e = new employeeDaoImplement();
        employee employee = e.retrieveEmployeeData(id);

        // now setting the required labels on the ui

        // adding the welcome message for admin and general employees
        // the  message is a little different

        welcomeLabel.setText("Welcome " + employee.getFirstName() + "" + employee.getMiddleName() +
                    " " + employee.getLastName());


        employeeName.setText(employee.getFirstName() + " " + employee.getMiddleName() +
                " " + employee.getLastName());
        employeeId.setText(employee.getEmployeeId() + "");
        age.setText(employee.getAge() + "");
        gender.setText(employee.getSex());
        phone.setText(employee.getPhone());
        email.setText(employee.getEmail() + "");
        address.setText(employee.getAdress() + "");
        nId.setText(employee.getNationalId() + "");
        dob.setText(employee.getBirthDate() + "");
        relativeName.setText(employee.getRelativeName() + "");
        relation.setText(employee.getRelation() + "");
        relativePhone.setText(employee.getRelativePhone());
        departmentId.setText(employee.getDeptId() + "");
        salary.setText(employee.getSalary() + "");
        designation.setText(employee.getDesignation());
        input=new employeeDaoImplement().retrieveEmployeeData(id)
                .getEmployeePhoto().getImage();
        if (input != null && input.available() > 1) {
            System.out.println("image available");
            Image image = new Image(input);
            employeePhoto.setImage(image);
        }
        absentLm.setText("" +
                new initiateLiveAttendance().retreiveAttendance(id) + " days.");


    }   public void login(ActionEvent event) throws Exception {
        // after signing out we return to the initial login page
//        Stage primaryStage=new Stage();
//        FXMLLoader loader=new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("login.fxml").openStream());
//        primaryStage.setTitle("Employee Management System- Login");
        // profileStage.setScene(new Scene(root, 600, 275));
        // profileStage.show();
        profileStage.close();
        loginStage.show();
    }

    public void retrieve() {

        try {
            worksOnDaoImplement wo = new worksOnDaoImplement();
            w=wo.retrieveWorksOnData(id);
        }

        catch (SQLException exception){
            System.out.println(exception);
        }

    }
    public void gotoWorkInfo(ActionEvent event) {
        retrieve();

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/View/workHistory.fxml").openStream());
            primaryStage.setTitle("Employee Management System- Employee Work History");
            workHistory wh = (workHistory) loader.getController();
            wh.setUpdateStage(primaryStage, profileStage, w);

            primaryStage.setScene(new Scene(root, 600, 275));
            primaryStage.show();
            profileStage.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void gotoAttendanceHistory(ActionEvent event) throws Exception{

        initiateLiveAttendance a = new initiateLiveAttendance();

        attendance =a.retreiveMonthAttendance(id);

        lateCount =a.retreiveLateAttendance(id);

        try {
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getResource("/View/attendanceHistory.fxml").openStream());
            primaryStage.setTitle("Employee Management System- Employee Attendance History");
            attendanceHistory aH = (attendanceHistory) loader.getController();
            aH.setUpdateStage(primaryStage, profileStage, attendance,lateCount, id);

            primaryStage.setScene(new Scene(root, 600, 275));
            primaryStage.show();
            profileStage.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
