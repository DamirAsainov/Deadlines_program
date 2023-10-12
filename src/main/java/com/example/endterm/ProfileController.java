package com.example.endterm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController extends Scene implements IScenes {

    @FXML
    private TextField TextFieldGroup;

    @FXML
    private TextField TextFieldLogin;

    @FXML
    private TextField TextFieldName;

    @FXML
    private TextField TextFieldPassword;

    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonUpdate;
    public ProfileController(User user){
        setUserScene(user);
    }
    private void start(){
        TextFieldName.setText(user.name);
        TextFieldLogin.setText(user.login);
        TextFieldPassword.setText(user.password);
        TextFieldGroup.setText(user.group);
    }

    @Override
    public void initialize() throws Exception {
        start();
        buttonUpdate.setOnAction(event -> {
            UserDB database = new UserDB();
            User newUser = new User(TextFieldLogin.getText(), TextFieldPassword.getText(), TextFieldName.getText(), TextFieldGroup.getText(), user.id);
            user = newUser;
            database.updateUser(newUser);
            try {
                openPreviousScene();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        buttonBack.setOnAction(event -> {
            try {
                openPreviousScene();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void openPreviousScene() throws Exception {
        mainMenuController controller = new mainMenuController(user);
        buttonUpdate.getScene().getWindow().hide();
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
        stage.setScene(new javafx.scene.Scene(root));
        stage.show();
    }

}
