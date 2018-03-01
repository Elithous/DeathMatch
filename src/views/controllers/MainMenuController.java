package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import events.ChangeScreenEvent;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
	void loadGameButtonAction(ActionEvent event) {

	}

	@FXML
	void newGameButtonAction(ActionEvent event) {
		ChangeScreenEvent cSEvent = new ChangeScreenEvent(ScreenType.MANAGEMENT, null, null);
		notifyListeners(cSEvent);
	}

	@FXML
	void settingButtonAction(ActionEvent event) {

	}

	@FXML
	void initialize() {

		backgroundImage.fitWidthProperty().bind(stackPane.widthProperty());
		backgroundImage.fitHeightProperty().bind(stackPane.heightProperty());

		SimpleDoubleProperty buttonFontSize = new SimpleDoubleProperty();
		SimpleDoubleProperty titleFontSize = new SimpleDoubleProperty();

		buttonFontSize.bind(stackPane.heightProperty().divide(30));
		contentBox.styleProperty().bind(Bindings.concat("-fx-font-size: ", buttonFontSize.asString(), ";"));

		titleFontSize.bind(stackPane.heightProperty().divide(10));
		titleLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", titleFontSize.asString(), ";"));
		;

		exitButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		settingsButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		newGameButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));
		loadGameButton.prefWidthProperty().bind(stackPane.widthProperty().divide(5));

		settingsButton.setDisable(true);

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
