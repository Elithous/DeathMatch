package views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import characters.enums.BattleChoice;
import characters.models.Character;
import characters.models.Hero;
import characters.models.Monster;
import controllers.BattleController;
import controllers.GameApp;
import events.ChangeScreenEvent;
import events.TurnEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import loot.models.Consumable;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ActionType;
import views.enums.ScreenType;
import views.interfaces.PlayerController;
import views.models.CharacterImageView;
import views.models.ConsumableListItem;

public class BattleScreenController extends EventPublisher implements PlayerController {

	private PlayerSave playerSave;
	private Quest quest;
	private BattleController batCon;
	private ActionType action = ActionType.NONE;
	private Consumable item = null;
	private CharacterImageView[] players = new CharacterImageView[4];
	private CharacterImageView[] enemies = new CharacterImageView[4];

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	@FXML
	private Label logLabel1;
	
	@FXML
	private Label logLabel2;
	
	@FXML
	private Label logLabel3;

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
	private Button attackButton;
	
	@FXML
	private Button defendButton;
	
	@FXML
	private Button itemsButton;
	
	@FXML
	private Button skipButton;

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
	private ScrollPane itemsMenuBox;

	@FXML
	private VBox itemList1;
	
	@FXML
	private VBox itemList2;

	@FXML
	void attackButtonClicked(ActionEvent event) 
	{
		if (action == ActionType.ATTACK)
		{
			action = ActionType.NONE;
			attackButton.setDisable(false);
			defendButton.setDisable(false);
			itemsButton.setDisable(false);
		}
		else
		if (batCon.isWaitingForInput() && action == ActionType.NONE) 
		{
			action = ActionType.ATTACK;
			defendButton.setDisable(true);
			itemsButton.setDisable(true);
		}
	}

	@FXML
	void defendButtonClicked(ActionEvent event) 
	{
		TurnEvent e = new TurnEvent(batCon.getOrder().peek(), BattleChoice.DEFEND, null);
		notifyListeners(e);
	}

	@FXML
	void itemList1Clicked(MouseEvent event) {

	}

	@FXML
	void itemList2Clicked(MouseEvent event) {

	}

	@FXML
	void itemsButtonClicked(ActionEvent event) 
	{
		if (action == ActionType.ITEM)
		{
			action = ActionType.NONE;
			item = null;
			itemsMenuBox.setVisible(false);
			for(Node node : itemList1.getChildren())
				{
					node.setDisable(false);
				}
				for(Node node : itemList2.getChildren())
				{
					node.setDisable(false);
				}
			attackButton.setDisable(false);
			defendButton.setDisable(false);
			itemsButton.setDisable(false);
		}
		else
		if (batCon.isWaitingForInput() && action == ActionType.NONE) 
		{
			action = ActionType.ITEM;
			itemsMenuBox.setVisible(true);
			attackButton.setDisable(true);
			defendButton.setDisable(true);
		}
	}

	@FXML
	void skipButtonClicked(ActionEvent event) 
	{
		TurnEvent e = new TurnEvent(batCon.getOrder().peek(), BattleChoice.SKIP, null);
		notifyListeners(e);
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
			button.styleProperty().bind(Bindings.concat(button.styleProperty().getValue(), "-fx-font-size: ", textFontSize.asString(), ";"));
			button.prefWidthProperty().bind(optionsVBox.widthProperty().multiply(.8));
		}

		// Scale currentPlayer stuff
		currentPlayerImage.fitHeightProperty().bind(currentPlayerPane.heightProperty().multiply(.6));
		currentPlayerImage.fitWidthProperty().bind(currentPlayerImage.fitHeightProperty());
		currentPlayerImage.layoutXProperty()
				.bind(currentPlayerPane.widthProperty().subtract(currentPlayerImage.fitWidthProperty()).divide(2));

		currentPlayerName.styleProperty()
				.bind(Bindings.concat(currentPlayerName.styleProperty().getValue(), "-fx-font-size: ", textFontSize.asString(), ";"));
		currentPlayerName.layoutXProperty()
				.bind(currentPlayerPane.widthProperty().subtract(currentPlayerName.widthProperty()).divide(2));
		currentPlayerName.layoutYProperty()
				.bind(currentPlayerImage.fitHeightProperty().add(currentPlayerName.heightProperty()));

		((HBox) itemsMenuBox.getContent()).prefWidthProperty().bind(itemsMenuBox.widthProperty());
		
		itemList1.prefWidthProperty().bind(itemsMenuBox.widthProperty().divide(2));
		itemList2.prefWidthProperty().bind(itemsMenuBox.widthProperty().divide(2));

		logLabel1.layoutYProperty().bind(battlePane.heightProperty().subtract(logLabel1.heightProperty()));
		logLabel2.layoutYProperty().bind(logLabel1.layoutYProperty().subtract(logLabel2.heightProperty()));
		logLabel3.layoutYProperty().bind(logLabel2.layoutYProperty().subtract(logLabel3.heightProperty()));
		
		logLabel1.styleProperty().bind(Bindings.concat(logLabel1.styleProperty().getValue(), "-fx-font-size: ", textFontSize, ";"));
		logLabel2.styleProperty().bind(logLabel1.styleProperty());
		logLabel3.styleProperty().bind(logLabel2.styleProperty());
	}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) 
	{
		this.addListener(app);
		this.playerSave = playerSave;
		this.quest = quest;
		batCon = new BattleController(playerSave, quest, this);
		batCon.takeTurn();
		this.addListener(batCon);
		
		for (Hero c : playerSave.getPlayers())
			if (c!= null) c.fullHeal();
		for (Monster m : quest.monsters)
			if (m!= null) m.fullHeal();

		for (int i = 0; i < players.length; i++) {
			players[i] = new CharacterImageView(playerSave.getPlayers()[i], this);
		}

		for (int i = 0; i < quest.monsters.length; i++) {
			enemies[i] = new CharacterImageView(quest.monsters[i], this);
		}

		int count = 0;
		for (Node node : battlePane.getChildren()) {
			HBox box = node instanceof HBox ? (HBox) node : null;
			if (box != null)
			for (Node node2 : box.getChildren()) 
			{
				VBox box2 = node2 instanceof VBox ? (VBox) node2 : new VBox();
				if (count < 4) {

					if (enemies[count] != null) {
						enemies[count].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
						box2.getChildren().add(enemies[count]);
					}
					count++;
					if (enemies[count] != null) {
						enemies[count].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
						box2.getChildren().add(enemies[count]);
					}
					count++;
				} else {
					if (players[count % 4] != null) {
						players[count % 4].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
						box2.getChildren().add(players[count % 4]);
					}
					count++;
					if (players[count % 4] != null) {
						players[count % 4].prefHeightProperty().bind(box2.heightProperty().multiply(.35));
						box2.getChildren().add(players[count % 4]);
					}
					count++;
				}
			}
		}
		update();
	}

	@Override
	public void update()
	{
		action = ActionType.NONE;
		item = null;
		attackButton.setDisable(false);
		defendButton.setDisable(false);
		itemsButton.setDisable(false);
		itemsMenuBox.setVisible(false);
		
		for (CharacterImageView cIV : enemies) 
		{
			if (cIV != null)
			if (cIV.getCharacter() != null) {
				cIV.update();
			}
		}
		for (CharacterImageView cIV : players) 
		{
			if (cIV != null)
			if (cIV.getCharacter() != null) {
				cIV.update();
			}
		}
		currentPlayerImage.setImage(batCon.getOrder().peek().getImage());
		currentPlayerName.setText(batCon.getOrder().peek().getName());
		
		List<characters.models.Character> list = (LinkedList<characters.models.Character>) batCon.getOrder();
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
		

		 		SimpleDoubleProperty itemHeight = new SimpleDoubleProperty();
		 		itemHeight.bind(itemsMenuBox.heightProperty().divide(3));
		 		
		 		itemList1.getChildren().removeAll(itemList1.getChildren());
		 		itemList2.getChildren().removeAll(itemList2.getChildren());
		 		
		 		ArrayList<Consumable> items = playerSave.getInventory().getConsumables();
		 		for(int i = 0; i < items.size(); i++) 
		 		{
		 			ConsumableListItem cli = new ConsumableListItem(items.get(i));
		 			if(i % 2 == 0) {
		 				itemList1.getChildren().add(cli);
		 			} else {
		 				itemList2.getChildren().add(cli);
		 			}
		 			
		 			cli.prefHeightProperty().bind(itemHeight);
		 			
		 			cli.getButton().addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {
		 
		 				@Override
		 				public void handle(Event event) 
		 				{
		 					item = cli.item;
		 					for(Node node : itemList1.getChildren())
		 					{
		 						node.setDisable(true);
		 					}
		 					for(Node node : itemList2.getChildren())
		 					{
		 						node.setDisable(true);
		 					}
		 				}
		 			});
		 		}
		 		
		 		SimpleDoubleProperty itemListHeight = new SimpleDoubleProperty();
		 		itemListHeight.bind(itemHeight.multiply((items.size()/2) + (items.size() % 2)));
		 		((HBox)itemsMenuBox.getContent()).prefHeightProperty().bind(itemListHeight);
	}
	
	public void switchScreen(ChangeScreenEvent e)
	{
		notifyListeners(e);
	}

	public void characterClicked(Character character) 
	{
		if (action == ActionType.NONE) return;
		TurnEvent e = null;
		if (action == ActionType.ATTACK) e = new TurnEvent(character, BattleChoice.ATTACK, null);
		else if (action == ActionType.DEFEND) e = new TurnEvent(character, BattleChoice.DEFEND, null);
		else 
			if (action == ActionType.ITEM && item != null) e = new TurnEvent(character, BattleChoice.USE_ITEM, item);
		notifyListeners(e);
	}
	
	public void addLog(String text)
	{
		logLabel3.setText(logLabel2.getText());
		logLabel2.setText(logLabel1.getText());
		logLabel1.setText(text);
	}
}
