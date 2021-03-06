package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import events.ChangeScreenEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import models.player.PlayerSave;
import models.quests.Quest;
import models.quests.QuestGenerator;
import views.enums.ScreenType;
import views.interfaces.PlayerController;
import views.models.QuestListItem;

public class QuestController extends EventPublisher implements PlayerController {
	private static int amountOfQuests = 5;

	private QuestListItem[] questItems = new QuestListItem[amountOfQuests];

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private VBox contentBox;
	
	@FXML
	private Label title;
	
	@FXML
	private VBox questBox;

    @FXML
    private HBox saveLoadBox;

    @FXML
    private Button saveButton;

    @FXML
    private Button loadButton;
    
    @FXML
    private Button quitButton;

	private PlayerSave playerSave;

    @FXML
    void loadButtonClicked(ActionEvent event) {
    	PlayerSave save = GameApp.load();
    	ChangeScreenEvent e = new ChangeScreenEvent(ScreenType.MANAGEMENT, null, save);
    	this.notifyListeners(e);
    	update();
    }

    @FXML
    void saveButtonClicked(ActionEvent event) {
    	GameApp.save(playerSave);
    	update();
    }

    @FXML
    void quitButtonAction(ActionEvent event) {
    	ChangeScreenEvent e = new ChangeScreenEvent(ScreenType.MAIN, null, null);
    	this.notifyListeners(e);
    	update();
    }

	@FXML
	void initialize() {
		assert questBox != null : "fx:id=\"questList\" was not injected: check your FXML file 'quest.fxml'.";

		StackPane parent = (StackPane) contentBox.getParent();
		SimpleDoubleProperty titleFontSize = new SimpleDoubleProperty();

		titleFontSize.bind(parent.heightProperty().divide(25));
		title.styleProperty().bind(Bindings.concat(title.styleProperty().getValue(), "-fx-font-size: ", titleFontSize.asString(), ";"));

		questBox.setSpacing(20);
		questBox.prefHeightProperty().bind(contentBox.heightProperty().multiply(.7));
		
		contentBox.prefWidthProperty().bind(parent.widthProperty());
		contentBox.prefHeightProperty().bind(parent.heightProperty());
		
		
		loadButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		saveButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		quitButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");

		SimpleDoubleProperty buttonFontSize = new SimpleDoubleProperty();

		buttonFontSize.bind(parent.heightProperty().divide(35));
		loadButton.styleProperty().bind(Bindings.concat(loadButton.styleProperty().getValue(),"-fx-font-size: ", buttonFontSize.asString(), ";"));
		saveButton.styleProperty().bind(Bindings.concat(saveButton.styleProperty().getValue(),"-fx-font-size: ", buttonFontSize.asString(), ";"));
		quitButton.styleProperty().bind(Bindings.concat(quitButton.styleProperty().getValue(),"-fx-font-size: ", buttonFontSize.asString(), ";"));
		
		loadButton.setBackground(GameApp.buttonBack);
		saveButton.setBackground(GameApp.buttonBack);
		quitButton.setBackground(GameApp.buttonBack);
		
		saveLoadBox.spacingProperty().bind(parent.widthProperty().divide(20));
		}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		this.addListener(app);
		this.playerSave = playerSave;
		
		Quest[] quests = QuestGenerator.generateQuests(playerSave);
		
		for (int i = 0; i < questItems.length; i++) {
			questItems[i] = new QuestListItem(quests[i]);
			questItems[i].getPlayButton().prefWidthProperty().bind(questBox.heightProperty().divide(5));

			//SimpleDoubleProperty buttonFontSize = new SimpleDoubleProperty();
			SimpleDoubleProperty textFontSize = new SimpleDoubleProperty();
			
			textFontSize.bind(questBox.heightProperty().divide(20));
			questItems[i].getQuestName().styleProperty().bind(Bindings.concat(questItems[i].getQuestName().styleProperty().getValue(), "-fx-font-size: ", textFontSize.asString(), ";"));
			
			//buttonFontSize.bind(questBox.heightProperty().divide(20));
			questItems[i].getPlayButton().styleProperty().bind(Bindings.concat(questItems[i].getPlayButton().styleProperty().getValue(), "-fx-font-size: ", textFontSize.asString(), ";"));	
			
			questItems[i].getQuestIcon().fitHeightProperty().bind(textFontSize);
			questItems[i].getQuestIcon().fitWidthProperty().bind(textFontSize);
			
			questItems[i].getPlayButton().addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {
				
				@Override
				public void handle(Event event) {
					Quest quest = ((QuestListItem)((Button) event.getSource()).getParent()).getQuest();
					ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.BATTLE, quest, playerSave);
					notifyListeners(cSEvent);
				}
			});
		}
		questBox.getChildren().addAll(questItems);
		update();
	}

	@Override
	public void update() {
		loadButton.setDisable(GameApp.load() == null);
	}
}
