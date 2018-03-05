package views.controllers;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import characters.models.Hero;
import controllers.GameApp;
import events.ChangeScreenEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import loot.LootGenerator;
import loot.LootGeneratorResult;
import loot.models.Loot;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;
import views.models.ViewOnlyLootListItem;

public class WinController extends EventPublisher implements PlayerController{

	private final float LEVEL_MULTIPLIER = 3.0f;
	
	private PlayerSave playerSave;

	@FXML
	private Pane contentPane;
	
	@FXML 
	private VBox vBox;
	
	@FXML
	private Label title;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane lootList;

    @FXML
    private Button continueButton;

    @FXML
    void continueButtonClicked(ActionEvent event) {
    	ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.MANAGEMENT, null, playerSave);
    	this.notifyListeners(cSEvent);
    }

    @FXML
    void initialize() {
    	vBox.layoutXProperty().bind(contentPane.widthProperty().multiply(.1));
    	vBox.prefWidthProperty().bind(contentPane.widthProperty().multiply(.8));
    	vBox.prefHeightProperty().bind(contentPane.heightProperty());
    	
    	SimpleDoubleProperty textFontSize = new SimpleDoubleProperty();
    	
    	textFontSize.bind(vBox.heightProperty().divide(10));
    	title.styleProperty().bind(Bindings.concat("-fx-font-size: ", textFontSize.asString(), ";"));
    	title.prefWidthProperty().bind(vBox.widthProperty());
    	
    	SimpleDoubleProperty buttonFontSize = new SimpleDoubleProperty();
    	
    	buttonFontSize.bind(vBox.heightProperty().divide(30));
    	continueButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", buttonFontSize.asString(), ";"));
    	continueButton.prefWidthProperty().bind(vBox.widthProperty().divide(5));
    	
    	lootList.prefWidthProperty().bind(vBox.widthProperty().multiply(.8));

    	lootList.prefHeightProperty().bind(vBox.heightProperty().multiply(.7));
    }

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		this.addListener(app);
		this.playerSave = playerSave;
		LootGeneratorResult loot = LootGenerator.generateLoot(quest);
		
		VBox content = new VBox();
		for(Loot lootAdd : loot.loot) {
			playerSave.getInventory().addLoot(lootAdd);

			ViewOnlyLootListItem li = new ViewOnlyLootListItem(lootAdd);
			content.getChildren().add(li);
		}
		lootList.setContent(content);
		
		// (level*cons)*.8f+((level*cons)*rand.nextfloat*.4f)
		Random rand = new Random();
		
		double totalExp = (quest.difficulty * LEVEL_MULTIPLIER) + ((quest.difficulty * LEVEL_MULTIPLIER)*rand.nextFloat()*0.4f);
		for(Hero hero : playerSave.getPlayers()) {
			hero.addExp((int) (totalExp/playerSave.getPlayers().length));
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
