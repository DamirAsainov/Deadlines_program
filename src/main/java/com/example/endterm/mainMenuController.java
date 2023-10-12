package com.example.endterm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class mainMenuController extends Scene implements IScenes{
    @FXML
    private Button buttonAddDeadline;

    @FXML
    private Button buttonGpa;

    @FXML
    private Button buttonGroup;

    @FXML
    private Label deadlineDate1;

    @FXML
    private Label deadlineDate2;

    @FXML
    private Label deadlineDate3;

    @FXML
    private Label deadlineDate4;

    @FXML
    private Label deadlineDate5;

    @FXML
    private Label deadlineText1;

    @FXML
    private Label deadlineText2;

    @FXML
    private Label deadlineText3;

    @FXML
    private Label deadlineText4;

    @FXML
    private Label deadlineText5;

    @FXML
    private Label labelGroup;

    @FXML
    private Label labelName;

    @FXML
    private Hyperlink profile;

    @FXML
    private ProgressBar progressBar1;

    @FXML
    private ProgressBar progressBar2;

    @FXML
    private ProgressBar progressBar3;

    @FXML
    private ProgressBar progressBar4;

    @FXML
    private ProgressBar progressBar5;

    @FXML
    private Hyperlink refresh;
    private List<Deadline> deadlineList;
    public mainMenuController(){}
    public mainMenuController(User user){
        setUserScene(user);
    }
    private void start(){
        labelName.setText(user.name);
        labelGroup.setText(user.group);
    }

    @Override
    public void openPreviousScene() throws Exception {

    }

    @FXML
    public void initialize() {
        start();
        profile.setOnAction(event -> {
            ProfileController controller = new ProfileController(user);
            profile.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
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
        });
        buttonAddDeadline.setOnAction(event -> {
            AddDeadlineController controller = new AddDeadlineController(user);
            buttonAddDeadline.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("addDeadlineScene.fxml"));
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
        });
        refresh.setOnAction(event -> {
            DeadlinesReader reader = new DeadlinesReader();
            URL url = null;
            try {
                //https://drive.google.com/u/0/uc?id=1qDlRoq0b4MSR-X08pvThPPyXhqdJV01l&export=download
                url = new URL("https://drive.google.com/u/0/uc?id=1qDlRoq0b4MSR-X08pvThPPyXhqdJV01l&export=download");
                deadlineList = reader.updateDeadlines(url, user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
              System.out.println("Updeted");
            }
            try{
                deadlineText1.setText(deadlineList.get(0).title + ". | " + deadlineList.get(0).course);
                deadlineDate1.setText(deadlineList.get(0).timestampEnd.toString().substring(5, 16));
                progressBar1.setProgress(deadlineList.get(0).getProgressBar());
                deadlineText2.setText(deadlineList.get(1).title + ". | "+ deadlineList.get(1).course);
                deadlineDate2.setText(deadlineList.get(1).timestampEnd.toString().substring(5, 16));
                progressBar2.setProgress(deadlineList.get(1).getProgressBar());
                deadlineText3.setText(deadlineList.get(2).title + ". | " + deadlineList.get(2).course);
                deadlineDate3.setText(deadlineList.get(2).timestampEnd.toString().substring(5, 16));
                progressBar3.setProgress(deadlineList.get(2).getProgressBar());
                deadlineText4.setText(deadlineList.get(3).title + ". | "+ deadlineList.get(3).course);
                deadlineDate4.setText(deadlineList.get(3).timestampEnd.toString().substring(5, 16));
                progressBar4.setProgress(deadlineList.get(3).getProgressBar());
                deadlineText5.setText(deadlineList.get(4).title + ". | "+ deadlineList.get(4).course);
                deadlineDate5.setText(deadlineList.get(4).timestampEnd.toString().substring(5, 16));
                progressBar5.setProgress(deadlineList.get(4).getProgressBar());

            }catch (IndexOutOfBoundsException e){
                System.out.println(e);
            }
        });
    }
}
