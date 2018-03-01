package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import models.player.PlayerSave;
import models.quests.Quest;
import views.interfaces.PlayerController;

public class QuestController implements PlayerController{

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

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
