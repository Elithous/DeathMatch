package views.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;

public class ManagementTabsController implements PlayerController {
	
	@FXML
	private TabPane tabPane;

	@FXML
	private StackPane partyTab;

	@FXML
	private StackPane questTab;

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
			Parent questBox = app.loadFXML(ScreenType.QUEST, playerSave, quest);
			questTab.getChildren().add(questBox);
			((StackPane) questTab.getChildren().get(0)).prefWidthProperty().bind(questTab.widthProperty());
			((StackPane) questTab.getChildren().get(0)).prefHeightProperty().bind(questTab.heightProperty());
			
			Parent partyBox = app.loadFXML(ScreenType.PARTY, playerSave, quest);
			partyTab.getChildren().add(partyBox);
			((StackPane) partyTab.getChildren().get(0)).prefWidthProperty().bind(partyTab.widthProperty());
			((StackPane) partyTab.getChildren().get(0)).prefHeightProperty().bind(partyTab.heightProperty());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		tabPane.tabMinWidthProperty().bind((tabPane.widthProperty().subtract(40)).divide(2));
		
	}

	@Override
	public void update() {
		
	}
}
