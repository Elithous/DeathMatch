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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lib.EventPublisher;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;

public class LossController extends EventPublisher implements PlayerController{

	@FXML
	private Pane pane;
	
	@FXML
	private ImageView background;
	
	@FXML
	private Button menuButton;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void mainMenuButtonClicked(ActionEvent event)
    {
    	ChangeScreenEvent e = new ChangeScreenEvent(ScreenType.MAIN, null, null);
    	this.notifyListeners(e);
    }

    @FXML
    void initialize() 
    {
    	SimpleDoubleProperty fontSize = new SimpleDoubleProperty();
    	
    	fontSize.bind(pane.heightProperty().divide(30));
    	menuButton.styleProperty().bind(Bindings.concat(menuButton.styleProperty().getValue().toString(), "-fx-font-size: ", fontSize.asString(), ";"));
    	menuButton.prefWidthProperty().bind(pane.widthProperty().divide(7));
    	menuButton.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(menuButton.widthProperty().divide(2)));
    	menuButton.layoutYProperty().bind(pane.heightProperty().multiply(.7));
    	
    	background.setPreserveRatio(false);
    	background.fitHeightProperty().bind(pane.heightProperty());
    	background.fitWidthProperty().bind(pane.widthProperty());
    	background.setLayoutX(0);
    	background.setLayoutY(0);
    	
    }

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) 
	{
		this.addListener(app);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}
