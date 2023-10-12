package com.example.endterm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddDeadlineController extends Scene implements IScenes {
    @FXML
    private TextField TextFieldCourse;
    @FXML
    private TextField TextFieldTimeEnd;
    @FXML
    private TextField TextFieldTitle;
    @FXML
    private Button buttonAddDeadline;
    @FXML
    private Button buttonBack;
    public AddDeadlineController(User user){
        setUserScene(user);
    }
    @Override
    public void initialize() throws Exception {
        buttonAddDeadline.setOnAction(event -> {
            DeadlineDB database = new DeadlineDB();
            Deadline deadline = new Deadline(TextFieldTitle.getText(), TextFieldCourse.getText(), TextFieldTimeEnd.getText());
            database.addDeadline(deadline, user);
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
        buttonAddDeadline.getScene().getWindow().hide();
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
