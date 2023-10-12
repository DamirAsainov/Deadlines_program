package com.example.endterm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController implements IScenes {

    @FXML
    private Button signUpButton;

    @FXML
    private Hyperlink signUpLogInButton;

    @FXML
    private TextField signUpLogin;

    @FXML
    private TextField signUpName;

    @FXML
    private PasswordField signUpPassword;
    @FXML
    private TextField signUpGroup;

    @Override
    public void openPreviousScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("auth.fxml"));
        Parent root = loader.load();
        LogInController controller = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void initialize() throws IOException {
        UserDB database = new UserDB();
        signUpButton.setOnAction(event -> {
            database.signUpUser(signUpLogin.getText(), signUpPassword.getText(),signUpName.getText(), signUpGroup.getText());
            signUpLogInButton.getScene().getWindow().hide();
            try {
                openPreviousScene();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        signUpLogInButton.setOnAction(actionEvent -> {
            signUpLogInButton.getScene().getWindow().hide();
            try {
                openPreviousScene();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
