package Controller;

import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class deleteController implements Initializable {
    private Stage deletePrompt;
    @FXML
    private TextField deleteId;

    @FXML
    private Label errorMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void deleteData(ActionEvent event) throws SQLException{
        if (checkAndDelete()){
            errorMessage.setTextFill(Color.GREEN);
            errorMessage.setText("Successfully deleted!!!");

        }
        else{
            errorMessage.setTextFill(Color.RED);
            errorMessage.setText("Invalid Id! Please enter a valid employee id.");
        }


    }

    public boolean checkAndDelete() throws SQLException{
        int id=0;
        if (deleteId.getText().equals("") || deleteId.getText().equals(null)){
           return false;
        }
        try{
            id=Integer.parseInt(deleteId.getText());

        }
        catch (Exception e){
            return false;
        }

        employeeDaoImplement ed=new employeeDaoImplement();
        int check=ed.deleteEmployeeData(id);
        if (check!=1){
            return false;
        }
        return true;
    }

}
