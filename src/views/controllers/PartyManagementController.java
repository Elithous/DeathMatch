package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.player.PlayerSave;
import models.quests.Quest;
import views.interfaces.PlayerController;

public class PartyManagementController implements PlayerController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane equipmentList;

    @FXML
    private VBox containerVBox;
    
    @FXML 
    private HBox topHBox;
    
    @FXML
    private HBox bottomHBox;
    
    @FXML
    private Pane playerPane;
    
    @FXML
    private ImageView currentPlayerImage;

    @FXML
    private ImageView helmSlot;

    @FXML
    private ImageView bodySlot;

    @FXML
    private ImageView legsSlot;

    @FXML
    private ImageView sideArmSlot;

    @FXML
    private ImageView mainArmSlot;

    @FXML
    private ImageView ring1Slot;

    @FXML
    private ImageView ring2Slot;

    @FXML
    private Label currentPlayerName;

    @FXML
    private Label currentPlayerStats;

    @FXML
    private ImageView player1Image;

    @FXML
    private ImageView player2Image;

    @FXML
    private ImageView player3Image;

    @FXML
    private ImageView player4Image;

    @FXML
    private ImageView hireImage;

    @FXML
    void bodySlotDragDropped(DragEvent event) {

    }

    @FXML
    void bodySlotDragStart(MouseEvent event) {

    }

    @FXML
    void equipmentListDragDropped(DragEvent event) {

    }

    @FXML
    void equipmentListDragStart(MouseEvent event) {

    }

    @FXML
    void helmSlotDragDropped(DragEvent event) {

    }

    @FXML
    void helmSlotDragStart(MouseEvent event) {

    }

    @FXML
    void hireImageClicked(MouseEvent event) {

    }

    @FXML
    void legsSlotDragDropped(DragEvent event) {

    }

    @FXML
    void legsSlotDragStart(MouseEvent event) {

    }

    @FXML
    void mainArmSlotDragDropped(DragEvent event) {

    }

    @FXML
    void mainArmSlotDragStart(MouseEvent event) {

    }

    @FXML
    void player1ImageClicked(MouseEvent event) {

    }

    @FXML
    void player2ImageClicked(MouseEvent event) {

    }

    @FXML
    void player3ImageClicked(MouseEvent event) {

    }

    @FXML
    void player4ImageClicked(MouseEvent event) {

    }

    @FXML
    void ring1SlotDragDropped(DragEvent event) {

    }

    @FXML
    void ring1SlotDragStart(MouseEvent event) {

    }

    @FXML
    void ring2SlotDragDropped(DragEvent event) {

    }

    @FXML
    void ring2SlotDragStart(MouseEvent event) {

    }

    @FXML
    void sideArmSlotDragDropped(DragEvent event) {

    }

    @FXML
    void sideArmSlotDragStart(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert equipmentList != null : "fx:id=\"equipmentList\" was not injected: check your FXML file 'party.fxml'.";
        assert currentPlayerImage != null : "fx:id=\"currentPlayerImage\" was not injected: check your FXML file 'party.fxml'.";
        assert helmSlot != null : "fx:id=\"helmSlot\" was not injected: check your FXML file 'party.fxml'.";
        assert bodySlot != null : "fx:id=\"bodySlot\" was not injected: check your FXML file 'party.fxml'.";
        assert legsSlot != null : "fx:id=\"legsSlot\" was not injected: check your FXML file 'party.fxml'.";
        assert sideArmSlot != null : "fx:id=\"sideArmSlot\" was not injected: check your FXML file 'party.fxml'.";
        assert mainArmSlot != null : "fx:id=\"mainArmSlot\" was not injected: check your FXML file 'party.fxml'.";
        assert ring1Slot != null : "fx:id=\"ring1Slot\" was not injected: check your FXML file 'party.fxml'.";
        assert ring2Slot != null : "fx:id=\"ring2Slot\" was not injected: check your FXML file 'party.fxml'.";
        assert currentPlayerName != null : "fx:id=\"currentPlayerName\" was not injected: check your FXML file 'party.fxml'.";
        assert currentPlayerStats != null : "fx:id=\"currentPlayerStats\" was not injected: check your FXML file 'party.fxml'.";
        assert player1Image != null : "fx:id=\"player1Image\" was not injected: check your FXML file 'party.fxml'.";
        assert player2Image != null : "fx:id=\"player2Image\" was not injected: check your FXML file 'party.fxml'.";
        assert player3Image != null : "fx:id=\"player3Image\" was not injected: check your FXML file 'party.fxml'.";
        assert player4Image != null : "fx:id=\"player4Image\" was not injected: check your FXML file 'party.fxml'.";
        assert hireImage != null : "fx:id=\"hireImage\" was not injected: check your FXML file 'party.fxml'.";
        // assert topHBox != null : "fx:id=\"topHBox\" was not injected: check your FXML file 'party.fxml'.";
        // assert playerPane != null : "fx:id=\"playerPane\" was not injected: check your FXML file 'party.fxml'.";
        
        topHBox.prefHeightProperty().bind(containerVBox.heightProperty().multiply(.8));
        bottomHBox.prefHeightProperty().bind(containerVBox.heightProperty().multiply(.2));
        bottomHBox.prefWidthProperty().bind(containerVBox.widthProperty());
        
        playerPane.prefWidthProperty().bind(topHBox.widthProperty().multiply(.6));
        playerPane.prefHeightProperty().bind(topHBox.heightProperty());
        
        equipmentList.prefWidthProperty().bind(topHBox.widthProperty().multiply(.4));
        
        // Bind the image objects in playerPane to positions and sizes
        currentPlayerImage.fitWidthProperty().bind(playerPane.widthProperty().divide(5));
        currentPlayerImage.fitHeightProperty().bind(currentPlayerImage.fitWidthProperty());
        currentPlayerImage.layoutXProperty().bind(playerPane.widthProperty().subtract(currentPlayerImage.fitWidthProperty().divide(2)).divide(2));
        currentPlayerImage.layoutYProperty().bind(playerPane.heightProperty().subtract(currentPlayerImage.fitHeightProperty().divide(2)).divide(2));
        
        // helmSlot.fitWidthProperty();
        //
        
        //Bind the image objects in bottomHBox to the height and spacing correctly
        player1Image.fitHeightProperty().bind(bottomHBox.heightProperty().multiply(.7));
        player1Image.fitWidthProperty().bind(player1Image.fitHeightProperty());
        player2Image.fitHeightProperty().bind(bottomHBox.heightProperty().multiply(.7));
        player2Image.fitWidthProperty().bind(player2Image.fitHeightProperty());
        player3Image.fitHeightProperty().bind(bottomHBox.heightProperty().multiply(.7));
        player3Image.fitWidthProperty().bind(player3Image.fitHeightProperty());
        player4Image.fitHeightProperty().bind(bottomHBox.heightProperty().multiply(.7));
        player4Image.fitWidthProperty().bind(player4Image.fitHeightProperty());
        hireImage.fitHeightProperty().bind(bottomHBox.heightProperty().multiply(.4));
        hireImage.fitWidthProperty().bind(hireImage.fitHeightProperty());
        
        bottomHBox.spacingProperty().bind(bottomHBox.heightProperty().divide(2));
        //
        
    }

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
