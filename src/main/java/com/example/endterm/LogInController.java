package com.example.endterm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LogInController extends com.example.endterm.Scene implements IScenes{
    @FXML
    private Button LogInButton;
    @FXML
    private PasswordField LogInPassword;
    @FXML
    private TextField logInLogn;
    @FXML
    private Hyperlink logInSignUp;
    @FXML
    public void openPreviousScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("signUp.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void initialize(){
        LogInButton.setOnAction(actionEvent -> {
            String loginText = logInLogn.getText().trim();
            String passwordText = LogInPassword.getText().trim();
            if(!loginText.equals("") && !passwordText.equals("")){
                loginUser(loginText, passwordText);
            }
            else{
                System.out.println("Login or password is empty");
            }
        });
        logInSignUp.setOnAction(actionEvent -> {
            logInSignUp.getScene().getWindow().hide();
            try {
                openPreviousScene();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
    private void loginUser(String login, String password){
        UserDB database = new UserDB();
        User user = new User(login, password);
        ResultSet result= database.getResultSetUser(user);
        int counter = 0;
        try {
            while (result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter>0){
            mainMenuController controller = new mainMenuController(database.getUser(user));
            logInSignUp.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
            loader.setController(controller);
            Parent root = null;
            try {
                Pane mainPane = (Pane) loader.load();
                root = mainPane;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            System.out.println("No");
        }
    }
}
