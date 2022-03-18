package Controller;

import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
import Model.employeeManagementSystem.entities.employee.employeeImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class updateController implements Initializable {
    private Stage updateStage;
    private employee e;
    private String d;
    private employeeImage ei;
    private employeeDaoImplement em=new employeeDaoImplement();
    private boolean isDate=true;
    private  Date bdate;
    private String choose="";
    @FXML
    private TextField eName, eAge, eGender, ePhone, eEmail, eAddress, nationalId,
            relativename, relaTion, relativephone, dId, desigNation, saLary, eType;
    @FXML
    private Label employeeId, message;

    @FXML
    private PasswordField password, rPassword;

    @FXML
    private DatePicker date;
    private Stage profileStage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public boolean checkError() throws Exception{
        try {
            if (eEmail.getText() == null || eEmail.getText() == "" || eName.getText() == null || eName.getText() == ""
                    || eAge.getText() == null || eGender.getText() == "" || ePhone.getText() == null || ePhone.getText() == ""
                    || eAddress.getText() == null || eAddress.getText() == "" || nationalId.getText() == null || nationalId.getText() == ""
                    || relativename.getText() == null || relativename.getText() == ""
                    || relaTion.getText() == null || relaTion.getText() == "" || relativephone.getText() == null || relativephone.getText() == ""
                    || dId.getText() == null || dId.getText() == "" || desigNation.getText() == null || desigNation.getText() == ""
                    || saLary.getText() == null || saLary.getText() == "" || password.getText() == null || rPassword.getText() == ""){
                message.setTextFill(Color.RED);
                message.setText("Please fill al the fields properly and try again!");
                return false;
            }
        }
        catch (NullPointerException e) {
            message.setTextFill(Color.RED);
            message.setText("Please fill al the fields properly and try again!");
            return false;
        }
        if(ePhone.getText().length()!=11 || relativephone.getText().length()!=11){
            message.setTextFill(Color.RED);
            message.setText("Please enter a valid phone number.");
            return false;
        }

        if( !(password.getText().equals(rPassword.getText()))){
            message.setTextFill(Color.RED);
            message.setText("Passwords don't match. Please enter again.");
            return false;
        }
        return true;
    }

    public void updateCurrentData(ActionEvent event) throws Exception {


        String result = null;
        // if photo is not updated use the old one
        if (!checkError()){
            return;
        }

        if (choose.equals("Error") || choose.equals("")){
            ei=e.getEmployeePhoto();
        }


        try {
            d = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("This is the problem " + d);
            bdate = getDate(d);
        }
        catch (NullPointerException exception){
            bdate=e.getBirthDate();
        }

        String array[] = eName.getText().split(" ");
        String fName = "", mName = "", lName = "";
        if (array.length == 3) {
            mName = array[1];
        }
        fName = array[0];
        lName = array[2];
        employee e = null;
        try {


                e = new employee(Integer.parseInt(employeeId.getText()), Integer.parseInt(dId.getText()), Integer.parseInt(eAge.getText()),
                        Integer.parseInt(saLary.getText()), fName, mName, lName, eEmail.getText(),
                        eGender.getText(), ePhone.getText(), eAddress.getText(),
                        desigNation.getText(), nationalId.getText(),
                        Integer.parseInt(eType.getText()), relativename.getText(), relaTion.getText(),
                        relativephone.getText(), ei, bdate, password.getText());

                if (em.updateEmployeeData(e) == 1) {
                    message.setTextFill(Color.GREEN);
                    message.setText("Update Successful!!!");
                }
        }

        catch (IndexOutOfBoundsException exception) {
            message.setText("Passwords don't match. Please re enter password.");
        }

        catch (SQLException exception){
            message.setText("Update UnSuccessful!!! Please fill all the fields properly" +
                    "and submit Again.");
        }

    }

    public Date getDate (String date) throws ParseException {
        java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date date1 = new Date(d.getTime());
        System.out.println(date1);
        return date1;
    }
    public void getEmployeePhoto (ActionEvent event) throws Exception {
        try {
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(null);
            FileInputStream fs = new FileInputStream(file.getAbsolutePath());
            ei = new employeeImage(fs);
            choose="Done";
        }
        catch (Exception exception){
            choose="Error";
        }
    }




    public void setUpdateStage(Stage stage, Stage profileStage , employee e){
        this.updateStage=stage;
        this.profileStage=profileStage;
        this.e=e;
        setAllFields();
    }

    public void setAllFields() {
        employeeId.setText(e.getEmployeeId() + "");
        eName.setText(e.getFirstName() + " " + e.getMiddleName() + " " + e.getLastName());
        eAge.setText(e.getAge() + "");
        eGender.setText(e.getSex() + "");
        ePhone.setText(e.getPhone() + "");
        eEmail.setText(e.getEmail() + "");
        eAddress.setText(e.getAdress() + "");
        nationalId.setText(e.getNationalId() + "");
        relativename.setText(e.getRelativeName() + "");
        relaTion.setText(e.getRelation() + "");
        relativephone.setText(e.getRelativePhone() + "");
        dId.setText(e.getDeptId() + "");
        desigNation.setText(e.getDesignation() + "");
        saLary.setText(e.getSalary() + "");
        eType.setText(e.getEmployeeType() + "");
        password.setText(e.getPassword());
        rPassword.setText(e.getPassword());
        try {
            String dAte = "" + e.getBirthDate();
            String array[]=dAte.split("-");

            int year=Integer.parseInt(array[0]);
            int month=Integer.parseInt(array[1]);
            int day=Integer.parseInt(array[2]);

            date.setValue(LocalDate.of(year, month, day));

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void backToProfileStage(ActionEvent event) throws Exception{
        updateStage.close();
        profileStage.show();
        //        Stage primaryStage = new Stage();
//        FXMLLoader loader = new FXMLLoader();
//        Pane root = loader.load(getClass().getResource("/View/employeeProfile.fxml").openStream());
//        primaryStage.setTitle("Employee Profile");
//        employeeProfileController employeeProfile = (employeeProfileController) loader.getController();
//        employeeProfile.showEmployeeInfo(Integer.parseInt(employeeId.getText()), primaryStage, employeeProfileController.loginStage);
//
//        primaryStage.setScene(new Scene(root, 600, 275));
//        //employeeProfile.showEmployeeInfo(Integer.parseInt(employeeId.getText()));
//        primaryStage.show();


    }


}
