package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import characters.models.Monster;
import controllers.GameApp;
import events.ChangeScreenEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import models.player.PlayerSave;
import models.quests.Quest;
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
	void questListClicked(MouseEvent event) {
		
	}

	@FXML
	void initialize() {
		assert questBox != null : "fx:id=\"questList\" was not injected: check your FXML file 'quest.fxml'.";
		

		StackPane parent = (StackPane) contentBox.getParent();
		SimpleDoubleProperty titleFontSize = new SimpleDoubleProperty();

		titleFontSize.bind(parent.heightProperty().divide(25));
		title.styleProperty().bind(Bindings.concat("-fx-font-size: ", titleFontSize.asString(), ";"));

		questBox.setSpacing(20);
		questBox.prefHeightProperty().bind(contentBox.heightProperty().multiply(.7));
		
		contentBox.prefWidthProperty().bind(parent.widthProperty());
		contentBox.prefHeightProperty().bind(parent.heightProperty());
		
	}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		this.addListener(app);
		
		// TODO get quests and put them in questItems array
		Monster[] mons = {new Monster()};
		mons[0].setName("Test");
		
		for (int i = 0; i < questItems.length; i++) {
			questItems[i] = new QuestListItem(new Quest(mons, 0));
			questItems[i].getPlayButton().prefWidthProperty().bind(questBox.heightProperty().divide(5));

			//SimpleDoubleProperty buttonFontSize = new SimpleDoubleProperty();
			SimpleDoubleProperty textFontSize = new SimpleDoubleProperty();
			
			textFontSize.bind(questBox.heightProperty().divide(20));
			questItems[i].getQuestName().styleProperty().bind(Bindings.concat("-fx-font-size: ", textFontSize.asString(), ";"));
			
			//buttonFontSize.bind(questBox.heightProperty().divide(20));
			questItems[i].getPlayButton().styleProperty().bind(Bindings.concat("-fx-font-size: ", textFontSize.asString(), ";"));	
			
			questItems[i].getQuestIcon().fitHeightProperty().bind(textFontSize);
			questItems[i].getQuestIcon().fitWidthProperty().bind(textFontSize);
			
			questItems[i].getPlayButton().addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() {
				
				@Override
				public void handle(Event event) {
					// TODO pass the correct quest from QuestListItem possibly using event.getSource()
					
					ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.BATTLE, quest, playerSave);
					notifyListeners(cSEvent);
				}
			});
		}
		questBox.getChildren().addAll(questItems);
	}

	@Override
	public void update() {
		
	}
}
