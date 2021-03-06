package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import controllers.HeroGenerator;
import events.ChangeScreenEvent;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lib.EventPublisher;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;

public class MainMenuController extends EventPublisher implements PlayerController {
	
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private StackPane stackPane;

	@FXML
	private ImageView backgroundImage;

	@FXML
	private VBox contentBox;

	@FXML
	private Label titleLabel;

	@FXML
	private Button newGameButton;

	@FXML
	private Button loadGameButton;

	@FXML
	private Button settingsButton;

	@FXML
	private Button exitButton;

	@FXML
	void exitButtonAction(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	void loadGameButtonAction(ActionEvent event) 
	{
		ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.MANAGEMENT, null, (GameApp.load()==null? HeroGenerator.createNewGame() : GameApp.load()));
		notifyListeners(cSEvent);
	}

	@FXML
	void newGameButtonAction(ActionEvent event) 
	{
		ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.MANAGEMENT, null, HeroGenerator.createNewGame());
		notifyListeners(cSEvent);
	}

	@FXML
	void settingButtonAction(ActionEvent event) {

	}

	@FXML
	void initialize() {

		backgroundImage.fitWidthProperty().bind(stackPane.widthProperty());
		backgroundImage.fitHeightProperty().bind(stackPane.heightProperty());
		backgroundImage.setPreserveRatio(false);

		SimpleDoubleProperty buttonFontSize = new SimpleDoubleProperty();

		buttonFontSize.bind(stackPane.heightProperty().divide(30));
		contentBox.styleProperty().bind(Bindings.concat(contentBox.styleProperty().getValue(), "-fx-font-size: ", buttonFontSize.asString(), ";"));

	//	titleLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", titleFontSize.asString(), ";"));
		
		
		exitButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		settingsButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		newGameButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		loadGameButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		
		exitButton.setBackground(GameApp.buttonBack);
		settingsButton.setBackground(GameApp.buttonBack);
		newGameButton.setBackground(GameApp.buttonBack);
		loadGameButton.setBackground(GameApp.buttonBack);
		
		exitButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		settingsButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		newGameButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		loadGameButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");

		settingsButton.setDisable(true);
		PlayerSave save = GameApp.load();
		if(save == null) {
			loadGameButton.setDisable(true);
		}

		contentBox.spacingProperty().bind(stackPane.heightProperty().divide(90));

	}

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		this.addListener(app);
	}

	@Override
	public void update() {
		
	}
}
