package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class WinController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> lootList;

    @FXML
    private Button continueButton;

    @FXML
    void continueButtonClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert lootList != null : "fx:id=\"lootList\" was not injected: check your FXML file 'win.fxml'.";
        assert continueButton != null : "fx:id=\"continueButton\" was not injected: check your FXML file 'win.fxml'.";

    }
}
