package Controller;

import Model.employeeManagementSystem.entities.attendance.initiateLiveAttendance;
import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
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
import java.util.ResourceBundle;

public class employeeProfileController implements Initializable {

    // declaring all the blank labels in the
    private InputStream input;
    private  Stage profileStage;
    public static Stage loginStage;
    @FXML
    private Label welcomeLabel;

    @FXML
    private Label employeeName, employeeId, age, gender, phone, email, address, nId, dob,
            relativeName, relation, relativePhone;
    @FXML
    private Label departmentId, designation, salary, absentLm;
    @FXML
    private ImageView employeePhoto;


    private updatePromptController upController;
    private insertController insertController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void showEmployeeInfo(int id, Stage stage, Stage login) throws Exception {
        profileStage=stage;
        loginStage=login;
        // importing the model class that communicates with the employee table
        // and returns the employee info
        employeeDaoImplement e = new employeeDaoImplement();
        employee employee = e.retrieveEmployeeData(id);

        // now setting the required labels on the ui

        // adding the welcome message for admin and general employees
        // the  message is a little different

        welcomeLabel.setText("Welcome " + employee.getFirstName()+ "" + employee.getMiddleName() +
                    " " + employee.getLastName() + ", Admin.");


        employeeName.setText(employee.getFirstName() + " " + employee.getMiddleName() +
                " " + employee.getLastName() );
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
        input=employee.getEmployeePhoto().getImage();
        if (input != null && input.available() > 1) {
            System.out.println("image available");
            Image image = new Image(input);
            employeePhoto.setImage(image);
        }
        absentLm.setText("" +
                new initiateLiveAttendance().retreiveAttendance(id) + " days.");

    }
    public void login(ActionEvent event) throws Exception {
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
    public void Insert(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/insert.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Insert new Employee Data");
        insertController = (insertController)loader.getController();
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        insertController.setStage(primaryStage, profileStage);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }
    public void updatePrompt(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/updatePrompt.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Update Employee Info");
        upController = (updatePromptController)loader.getController();
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        upController.setUpdatePromptStage(primaryStage, profileStage);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }
    public void deletePrompt(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/deletePrompt.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Delete Employee Info");
        deleteController dc = (deleteController)loader.getController();
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
    }
    public void initializeAttendance(ActionEvent event) throws Exception{
        initiateLiveAttendance attendance=new initiateLiveAttendance();
        attendance.insert_attendance();
    }
    public void addDepartment(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/addDepartment.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Add New Department");
        addDepartment ad = (addDepartment) loader.getController();
        ad.setStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }

    public void goToDeptUpdatePrompt(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/updateDeptPrompt.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Update Department Info");
        updateDeptPrompt udp = (updateDeptPrompt) loader.getController();
        udp.setUpdatePromptStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }
    public void addProjects(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/addProjects.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Add new Projects");
        addProjects addP = (addProjects) loader.getController();
        addP.setUpdatePromptStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }
    public void updateProjectPrompt() throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/updateProjectsPrompt.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Update Project Info");
        updateProjectsPrompt udp = (updateProjectsPrompt) loader.getController();
        udp.setUpdatePromptStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }

    public void addWorkInfo(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/addWorkInfo.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Add Employee Work Info");
        addWorkInfo add = (addWorkInfo) loader.getController();
        add.setUpdatePromptStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }
    public void workHistoryPrompt(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/viewWorkPrompt.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Employee Work History");
        viewWorkPrompt view = (viewWorkPrompt) loader.getController();
        view.setUpdatePromptStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }
    public void viewAttendance(ActionEvent event) throws Exception{
        Stage primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/View/viewAttendancePrompt.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Employee Attendance History");
        viewAttendancePrompt view = (viewAttendancePrompt) loader.getController();
        view.setUpdatePromptStage(primaryStage, profileStage);
        // passing the stage object to the next stage so that we can use this to close
        // this stage when we open the next one
        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();
        profileStage.close();
    }

    public void reFresh(ActionEvent event) throws Exception{
        showEmployeeInfo(Integer.parseInt(employeeId.getText()), profileStage, loginStage);
    }
}
