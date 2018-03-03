package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import events.ChangeScreenEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;

public class WinController extends EventPublisher implements PlayerController{

	private PlayerSave playerSave;
	
	@FXML VBox vBox;
	
	@FXML
	private Pane contentPane;
	
	@FXML
	private Label title;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> lootList;

    @FXML
    private Button continueButton;

    @FXML
    void continueButtonClicked(ActionEvent event) {
    	ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.WIN, null, playerSave);
    	this.notifyListeners(cSEvent);
    }

    @FXML
    void initialize() {
    	vBox.layoutXProperty().bind(contentPane.widthProperty().multiply(.1));
    	vBox.prefWidthProperty().bind(contentPane.widthProperty().multiply(.8));
    	vBox.prefHeightProperty().bind(contentPane.heightProperty());
    	
    	SimpleDoubleProperty textFontSize = new SimpleDoubleProperty();
    	
    	textFontSize.bind(contentPane.heightProperty().divide(10));
    	title.styleProperty().bind(Bindings.concat("-fx-font-size: ", textFontSize.asString(), ";"));
    	title.prefWidthProperty().bind(vBox.widthProperty());
    }

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		this.addListener(app);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
