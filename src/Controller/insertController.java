package Controller;

import Model.employeeManagementSystem.entities.employee.daoEmployee.implementEmloyee.employeeDaoImplement;
import Model.employeeManagementSystem.entities.employee.employee;
import Model.employeeManagementSystem.entities.employee.employeeImage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class insertController implements Initializable {

    private Stage insertStage, profileStage;

    @FXML
    private TextField eName, eAge, eGender, ePhone, eEmail, eAddress, nationalId,
            relativename, relaTion, relativephone, dId, desigNation, saLary, eType;

    @FXML
    private PasswordField password, rPassword;

    @FXML
    private DatePicker date;

    @FXML
    private Button Insert;

    @FXML
    private Label employeeId, message;

    private employeeImage ei=null;
    private employee empData;
    private employeeDaoImplement em;
    private int newId;
    private String d;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.em = new employeeDaoImplement();
            this.newId = em.retrieveId();
            employeeId.setText(this.newId + "");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @FXML
    public void insert(ActionEvent event) throws Exception {


        if(checkError()) {
            insertIntoDb();
        }
        else{
            return;
        }
        }


        public boolean checkError() throws Exception{
        try {
           if (eEmail.getText() == null || eEmail.getText() == "" || eName.getText() == null || eName.getText() == ""
                    || eAge.getText() == null || eGender.getText() == "" || ePhone.getText() == null || ePhone.getText() == ""
                    || eAddress.getText() == null || eAddress.getText() == "" || nationalId.getText() == null || nationalId.getText() == ""
                    || (date.getValue().toString()) == null || (date.getValue().toString()).equals("") || relativename.getText() == null || relativename.getText() == ""
                    || relaTion.getText() == null || relaTion.getText() == "" || relativephone.getText() == null || relativephone.getText() == ""
                    || dId.getText() == null || dId.getText() == "" || desigNation.getText() == null || desigNation.getText() == ""
                    || saLary.getText() == null || saLary.getText() == "" || password.getText() == null || rPassword.getText() == ""
                    || ei==null){
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
            if(ePhone.getText().trim().length()!=11 || relativephone.getText().trim().length()!=11){
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
    public String insertIntoDb() throws ParseException {
        String result = null;

            String array[] = eName.getText().split(" ");
            String fName = "", mName = "", lName = "";
            if (array.length == 3) {
                fName=array[0];
                mName = array[1];
                lName=array[2];
            }
            else if (array.length==1){
                fName=array[0];
            }
            else {
                fName = array[0];
                lName = array[1];
            }
            d = date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Date bdate = getDate(d);
            employee e = null;
            try {
                if (!password.getText().equals(rPassword.getText())) {
                    throw new IndexOutOfBoundsException();
                } else {
                    e = new employee(this.newId, Integer.parseInt(dId.getText()), Integer.parseInt(eAge.getText()),
                            Integer.parseInt(saLary.getText()), fName, mName, lName, eEmail.getText(),
                            eGender.getText(), ePhone.getText(), eAddress.getText(),
                            desigNation.getText(), nationalId.getText(),
                            Integer.parseInt(eType.getText()), relativename.getText(), relaTion.getText(),
                            relativephone.getText(), ei, bdate, password.getText());

                    if (em.insertEmployeeData(e) == 1) {
                        message.setTextFill(Color.GREEN);
                        message.setText("Insertion Successful!!!");
                    }
                }

            } catch (IndexOutOfBoundsException exception) {
                message.setText("Passwords don't match. Please reenter password.");
            } catch (Exception exception) {
                message.setText("Insertion UnSuccessful!!! Please fill all the fields properly" +
                        " and Insert Again");
            }

            return result;
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
           }
           catch (Exception exception){
               ei=null;
           }

        }

        public void setStage(Stage insertStage, Stage profileStage){
            this.insertStage=insertStage;
            this.profileStage=profileStage;
        }

        public void backToProfileStage(ActionEvent event){
            insertStage.close();
            profileStage.show();
    }


}
