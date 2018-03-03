package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.player.PlayerSave;
import models.quests.Quest;
import views.interfaces.PlayerController;

public class BattleScreenController implements PlayerController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView backgroundImage;

	@FXML
	private VBox player1Box;

	@FXML
	private VBox player2Box;

	@FXML
	private VBox player3Box;

	@FXML
	private VBox player4Box;

	@FXML
	private VBox enemy1Box;

	@FXML
	private VBox enemy2Box;

	@FXML
	private VBox enemy3Box;

	@FXML
	private VBox enemy4Box;

	@FXML
	private ImageView currentPlayerImage;

	@FXML
	private Label currentPlayerName;

	@FXML
	private ImageView playerOrder1Image;

	@FXML
	private ImageView playerOrder2Image;

	@FXML
	private ImageView playerOrder3Image;

	@FXML
	private ImageView playerOrder4Image;

	@FXML
	private HBox itemsMenuBox;

	@FXML
	private ListView<?> itemList1;

	@FXML
	private ListView<?> itemList2;

	@FXML
	void attackButtonClicked(ActionEvent event) {

	}

	@FXML
	void defendButtonClicked(ActionEvent event) {

	}

	@FXML
	void itemList1Clicked(MouseEvent event) {

	}

	@FXML
	void itemList2Clicked(MouseEvent event) {

	}

	@FXML
	void itemsButtonClicked(ActionEvent event) {

	}

	@FXML
	void skipButtonClicked(ActionEvent event) {

	}

	@FXML
	void initialize() {

	}
	
	private PlayerSave ps;
	private Quest q;

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) 
	{
		ps = playerSave;
		q = quest;
		update();
	}

	@Override
	public void update() 
	{
		
	}
}
