package views.controllers;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import characters.enums.BattleChoice;
import characters.models.Hero;
import characters.models.Monster;
import controllers.BattleController;
import controllers.GameApp;
import events.ChangeScreenEvent;
import events.TurnEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;
import views.models.CharacterImageView;

public class BattleScreenController extends EventPublisher implements PlayerController {

	private PlayerSave playerSave;
	private Quest quest;
	private BattleController batCon;

	private CharacterImageView[] players = new CharacterImageView[4];
	private CharacterImageView[] enemies = new CharacterImageView[4];

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private HBox playerOrderBox;

	@FXML
	private Pane currentPlayerPane;

	@FXML
	private VBox optionsVBox;

	@FXML
	private Pane sideBox;

	@FXML
	private VBox containerVBox;

	@FXML
	private HBox selectionBox;

	@FXML
	private Pane battlePane;

	@FXML
	private ImageView backgroundImage;

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
	void attackButtonClicked(ActionEvent event) 
	{
		if (batCon.isWaitingForInput()) 
		{
			TurnEvent e = new TurnEvent(batCon.getOrder().peek(), BattleChoice.ATTACK, null);
			notifyListeners(e);
		}
	}

	@FXML
	void defendButtonClicked(ActionEvent event) 
	{
		if (batCon.isWaitingForInput()) 
		{
			TurnEvent e = new TurnEvent(batCon.getOrder().peek(), BattleChoice.DEFEND, null);
			notifyListeners(e);
		}
	}

	@FXML
	void itemList1Clicked(MouseEvent event) {

	}

	@FXML
	void itemList2Clicked(MouseEvent event) {

	}

	@FXML
	void itemsButtonClicked(ActionEvent event) {
		itemsMenuBox.setVisible(!itemsMenuBox.isVisible());
	}

	@FXML
	void skipButtonClicked(ActionEvent event) {
		// TEMPORARY CODE
		this.notifyListeners(new ChangeScreenEvent(ScreenType.WIN, quest, playerSave));
	}

	@FXML
	void initialize() {
		// Scale top and bottom box to the screen
		battlePane.prefHeightProperty().bind(containerVBox.heightProperty().multiply(.7));
		selectionBox.prefHeightProperty().bind(containerVBox.heightProperty().multiply(.3));

		// Scale background image to top box
		backgroundImage.fitWidthProperty().bind(battlePane.widthProperty());
		backgroundImage.fitHeightProperty().bind(battlePane.heightProperty());

		// Scale Bottom menu sections
		currentPlayerPane.prefWidthProperty().bind(selectionBox.widthProperty().multiply(.2));
		optionsVBox.prefWidthProperty().bind(selectionBox.widthProperty().multiply(.2));
		sideBox.prefWidthProperty().bind(selectionBox.widthProperty().multiply(.6));

		// Scale Battle boxes
		for (Node node : battlePane.getChildren()) {
			HBox box = node instanceof HBox ? (HBox) node : new HBox();
			box.prefWidthProperty().bind(battlePane.widthProperty().divide(2));
			box.prefHeightProperty().bind(battlePane.heightProperty());
			for (Node node2 : box.getChildren()) {
				VBox box2 = node2 instanceof VBox ? (VBox) node2 : new VBox();
				box2.prefWidthProperty().bind(box.widthProperty().divide(2));
				box2.prefHeightProperty().bind(box.heightProperty());
			}
		}
		((HBox) battlePane.getChildren().get(2)).layoutXProperty().bind(battlePane.widthProperty().divide(2));

		// Scale sideMenu items
		playerOrderBox.prefWidthProperty().bind(sideBox.widthProperty());
		playerOrderBox.prefHeightProperty().bind(sideBox.heightProperty());
		boolean playerImage = false;
		for (Node node : playerOrderBox.getChildren()) {
			ImageView image = node instanceof ImageView ? (ImageView) node : new ImageView();
			if (playerImage) {
				image.fitWidthProperty().bind(sideBox.widthProperty().multiply(.15));
			} else {
				image.fitWidthProperty().bind(sideBox.widthProperty().divide(25));
			}
			image.setFitHeight(Double.MAX_VALUE);
			playerImage = !playerImage;
		}

		itemsMenuBox.prefWidthProperty().bind(sideBox.widthProperty());
		itemsMenuBox.prefHeightProperty().bind(sideBox.heightProperty());

		// Scale Buttons
		SimpleDoubleProperty textFontSize = new SimpleDoubleProperty();
		textFontSize.bind(selectionBox.heightProperty().divide(11));
		for (Node node : optionsVBox.getChildren()) {
			Button button = node instanceof Button ? (Button) node : new Button();
			button.styleProperty().bind(Bindings.concat("-fx-font-size: ", textFontSize.asString(), ";"));
			button.prefWidthProperty().bind(optionsVBox.widthProperty().multiply(.8));
		}

		// Scale currentPlayer stuff
		currentPlayerImage.fitHeightProperty().bind(currentPlayerPane.heightProperty().multiply(.6));
		currentPlayerImage.fitWidthProperty().bind(currentPlayerImage.fitHeightProperty());
		currentPlayerImage.layoutXProperty()
				.bind(currentPlayerPane.widthProperty().subtract(currentPlayerImage.fitWidthProperty()).divide(2));

		currentPlayerName.styleProperty()
				.bind(Bindings.concat("-fx-font-size: ", textFontSize.asString(), ";-fx-border-color: black;"));
		currentPlayerName.layoutXProperty()
				.bind(currentPlayerPane.widthProperty().subtract(currentPlayerName.widthProperty()).divide(2));
		currentPlayerName.layoutYProperty()
				.bind(currentPlayerImage.fitHeightProperty().add(currentPlayerName.heightProperty()));
	}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) 
	{
		this.addListener(app);
		this.playerSave = playerSave;
		this.quest = quest;
		batCon = new BattleController(playerSave, quest, this);
		this.addListener(batCon);
		
		for (Hero c : playerSave.getPlayers())
			if (c!= null) c.fullHeal();
		for (Monster m : quest.monsters)
			if (m!= null) m.fullHeal();

		for (int i = 0; i < players.length; i++) {
			players[i] = new CharacterImageView(playerSave.getPlayers()[i]);
		}

		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new CharacterImageView(quest.monsters[i]);
		}

		int count = 0;
		for (Node node : battlePane.getChildren()) {
			HBox box = node instanceof HBox ? (HBox) node : new HBox();
			for (Node node2 : box.getChildren()) {
				VBox box2 = node2 instanceof VBox ? (VBox) node2 : new VBox();
				if (count < 4) {
					if (enemies[count] != null)
						enemies[count].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
					box2.getChildren().add(enemies[count++]);
					if (enemies[count] != null)
						enemies[count].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
					box2.getChildren().add(enemies[count++]);
				} else {
					if (players[count % 4] != null)
						players[count % 4].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
					box2.getChildren().add(players[count++ % 4]);
					if (players[count % 4] != null)
						players[count % 4].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
					box2.getChildren().add(players[count++ % 4]);
				}
			}
		}
		update();
	}

	@Override
	public void update()
	{
		for (CharacterImageView cIV : enemies) 
		{
			if (cIV.getCharacter() != null) {
				cIV.update();
			}
		}
		for (CharacterImageView cIV : players) 
		{
			if (cIV.getCharacter() != null) {
				cIV.update();
			}
		}
		currentPlayerImage.setImage(batCon.getOrder().peek().getImage());
		currentPlayerName.setText(batCon.getOrder().peek().getName());
		
		List<characters.models.Character> list = (LinkedList<characters.models.Character>)batCon.getOrder();
		int two = 2;
		int three = 3;
		int four = 4;
		while (two >= list.size() || three >= list.size() || four >= list.size())
		{
			if (two >= list.size()) two -= list.size();
			if (three >= list.size()) three -= list.size();
			if (four >= list.size()) four -= list.size();
		}
		
		playerOrder1Image.setImage(list.get(1).getImage());
		playerOrder2Image.setImage(list.get(two).getImage());
		playerOrder3Image.setImage(list.get(three).getImage());
		playerOrder4Image.setImage(list.get(four).getImage());
	}
}
