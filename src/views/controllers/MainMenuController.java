package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button exitButton;

    @FXML
    void exitButtonAction(ActionEvent event) {

    }

    @FXML
    void loadGameButtonAction(ActionEvent event) {

    }

    @FXML
    void newGameButtonAction(ActionEvent event) {

    }

    @FXML
    void settingButtonAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'main.fxml'.";
        assert backgroundImage != null : "fx:id=\"backgroundImage\" was not injected: check your FXML file 'main.fxml'.";
        assert newGameButton != null : "fx:id=\"newGameButton\" was not injected: check your FXML file 'main.fxml'.";
        assert loadGameButton != null : "fx:id=\"loadGameButton\" was not injected: check your FXML file 'main.fxml'.";
        assert settingsButton != null : "fx:id=\"settingsButton\" was not injected: check your FXML file 'main.fxml'.";
        assert exitButton != null : "fx:id=\"exitButton\" was not injected: check your FXML file 'main.fxml'.";

        backgroundImage.fitWidthProperty().bind(stackPane.widthProperty());
        backgroundImage.fitHeightProperty().bind(stackPane.heightProperty());
    }
}
