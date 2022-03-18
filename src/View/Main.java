package View;

import Controller.loginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage=new Stage();
        FXMLLoader loader=new FXMLLoader();
        Parent root = loader.load(getClass().getResource("login.fxml").openStream());
        primaryStage.setTitle("Employee Management System- Login");
        loginController login=(loginController) loader.getController();
        login.setPrevStage(primaryStage);

        primaryStage.setScene(new Scene(root, 600, 275));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
