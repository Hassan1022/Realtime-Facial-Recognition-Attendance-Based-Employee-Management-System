package Controller;
import View.*;
import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    // retrieving both the text field and password fields input from the
    // fxml
    private Stage prevStage=null;
    @FXML
    private TextField employeeId;

    @FXML
    private PasswordField employeePass;

    @FXML

    private Label messageLabel;
    // initializing login
    private employeeProfileController employeeProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public  void login(ActionEvent event) throws Exception {
        String id=employeeId.getText();
        employee employee=getPassword(id);
        if(employee!=null &&
                employee.getPassword().equals(employeePass.getText())){
            employeeId.clear();
            employeePass.clear();
            messageLabel.setText("");
            prevStage.close();
            if (employee.getEmployeeType()==1) {

                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("/View/employeeProfile.fxml").openStream());
                primaryStage.setTitle("Employee Profile");
                employeeProfile = (employeeProfileController) loader.getController();
                employeeProfile.showEmployeeInfo(Integer.parseInt(id), primaryStage, prevStage);

                primaryStage.setScene(new Scene(root, 600, 275));
                //employeeProfile.showEmployeeInfo(Integer.parseInt(employeeId.getText()));
                primaryStage.show();
            }
            else{
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                Pane root = loader.load(getClass().getResource("/View/user.fxml").openStream());
                primaryStage.setTitle("Employee Profile");
                user u = (user) loader.getController();
                u.showEmployeeInfo(Integer.parseInt(id), primaryStage, prevStage);

                primaryStage.setScene(new Scene(root, 600, 275));
                //employeeProfile.showEmployeeInfo(Integer.parseInt(employeeId.getText()));
                primaryStage.show();
            }
        }
         else {
            messageLabel.setText("Invalid employee Id or password. Please try again!!!");
        }

    }

    public employee getPassword(String employeeId){
        int id;
        try {
            id = Integer.parseInt(employeeId);
        }
        catch (Exception e){
            return null;
        }
        employeeDaoImplement e=new employeeDaoImplement();
        employee employee=null;
        try {
            employee = e.retrieveEmployeeData(id);
        }
        catch (SQLException exception){
            return null;
        }
            return employee;
        }
    public void setPrevStage(Stage stage){
        prevStage=stage;
    }

}




