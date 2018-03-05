package views.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controllers.GameApp;
import events.ChangeScreenEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import loot.models.Consumable;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;
import views.models.CharacterImageView;
import views.models.ConsumableListItem;

public class BattleScreenController extends EventPublisher implements PlayerController {

	private PlayerSave playerSave;
	private Quest quest;

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
	private ScrollPane itemsMenuBox;

	@FXML
	private VBox itemList1;
	
	@FXML
	private VBox itemList2;

	@FXML
	void attackButtonClicked(ActionEvent event) {

	}

	@FXML
	void defendButtonClicked(ActionEvent event) {

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

		((HBox) itemsMenuBox.getContent()).prefWidthProperty().bind(itemsMenuBox.widthProperty());
		
		itemList1.prefWidthProperty().bind(itemsMenuBox.widthProperty().divide(2));
		itemList2.prefWidthProperty().bind(itemsMenuBox.widthProperty().divide(2));
	}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		this.addListener(app);
		this.playerSave = playerSave;
		this.quest = quest;


		for (int i = 0; i < playerSave.getPlayers().length; i++) {
			players[i] = new CharacterImageView(playerSave.getPlayers()[i]);
		}

		for (int i = 0; i < quest.monsters.length; i++) {
			enemies[i] = new CharacterImageView(quest.monsters[i]);
		}

		int count = 0;
		for (Node node : battlePane.getChildren()) {
			HBox box = node instanceof HBox ? (HBox) node : new HBox();
			for (Node node2 : box.getChildren()) {
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
	public void update() {
		for (CharacterImageView cIV : enemies) {
			if (cIV != null) {
				cIV.update();
			}
		}
		for (CharacterImageView cIV : players) {
			if (cIV != null) {
				cIV.update();
			}
		}

		SimpleDoubleProperty itemHeight = new SimpleDoubleProperty();
		itemHeight.bind(itemsMenuBox.heightProperty().divide(3));
		
		itemList1.getChildren().removeAll(itemList1.getChildren());
		itemList2.getChildren().removeAll(itemList2.getChildren());
		
		ArrayList<Consumable> items = playerSave.getInventory().getConsumables();
		for(int i = 0; i < items.size(); i++) {
			ConsumableListItem e = new ConsumableListItem(items.get(i));
			if(i % 2 == 0) {
				itemList1.getChildren().add(e);
			} else {
				itemList2.getChildren().add(e);
			}
			
			e.prefHeightProperty().bind(itemHeight);
			
			e.getButton().addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					// send an event to battle controller with the item chosen. Let them handle it
					System.out.println("Button clicked");
				}
			});
		}
		
		SimpleDoubleProperty itemListHeight = new SimpleDoubleProperty();
		itemListHeight.bind(itemHeight.multiply((items.size()/2) + (items.size() % 2)));
		((HBox)itemsMenuBox.getContent()).prefHeightProperty().bind(itemListHeight);

	}
}
