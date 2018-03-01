package views.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;

public class ManagementTabsController implements PlayerController {

	Parent[] tabs = new Parent[2];

	@FXML
	private AnchorPane partyTab;

	@FXML
	private AnchorPane questTab;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void initialize() {

	}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		try {
			questTab.getChildren().add(app.loadFXML(ScreenType.QUEST, playerSave, quest));
			partyTab.getChildren().add(app.loadFXML(ScreenType.PARTY, playerSave, quest));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
