package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BattleScreenController {

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
		assert backgroundImage != null : "fx:id=\"backgroundImage\" was not injected: check your FXML file 'battle.fxml'.";
		assert player1Box != null : "fx:id=\"player1Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert player2Box != null : "fx:id=\"player2Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert player3Box != null : "fx:id=\"player3Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert player4Box != null : "fx:id=\"player4Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert enemy1Box != null : "fx:id=\"enemy1Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert enemy2Box != null : "fx:id=\"enemy2Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert enemy3Box != null : "fx:id=\"enemy3Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert enemy4Box != null : "fx:id=\"enemy4Box\" was not injected: check your FXML file 'battle.fxml'.";
		assert currentPlayerImage != null : "fx:id=\"currentPlayerImage\" was not injected: check your FXML file 'battle.fxml'.";
		assert currentPlayerName != null : "fx:id=\"currentPlayerName\" was not injected: check your FXML file 'battle.fxml'.";
		assert playerOrder1Image != null : "fx:id=\"playerOrder1Image\" was not injected: check your FXML file 'battle.fxml'.";
		assert playerOrder2Image != null : "fx:id=\"playerOrder2Image\" was not injected: check your FXML file 'battle.fxml'.";
		assert playerOrder3Image != null : "fx:id=\"playerOrder3Image\" was not injected: check your FXML file 'battle.fxml'.";
		assert playerOrder4Image != null : "fx:id=\"playerOrder4Image\" was not injected: check your FXML file 'battle.fxml'.";
		assert itemsMenuBox != null : "fx:id=\"itemsMenuBox\" was not injected: check your FXML file 'battle.fxml'.";
		assert itemList1 != null : "fx:id=\"itemList1\" was not injected: check your FXML file 'battle.fxml'.";
		assert itemList2 != null : "fx:id=\"itemList2\" was not injected: check your FXML file 'battle.fxml'.";

	}
}
