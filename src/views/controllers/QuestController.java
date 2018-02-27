package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class QuestController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane questList;

    @FXML
    void questListClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert questList != null : "fx:id=\"questList\" was not injected: check your FXML file 'quest.fxml'.";

    }
}
