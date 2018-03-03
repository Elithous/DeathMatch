package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import characters.enums.EquipmentSlot;
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
import loot.models.Equipment;
import models.player.PlayerSave;
import models.quests.Quest;
import views.interfaces.PlayerController;
import views.models.LootListItem;

public class PartyManagementController implements PlayerController
{
	private int currentPlayer = 0;

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
    
    private PlayerSave ps;

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
    void player1ImageClicked(MouseEvent event)
    {
    	currentPlayer = 0;
    	update();
    }

    @FXML
    void player2ImageClicked(MouseEvent event) {
    	currentPlayer = 1;
    	update();
    }

    @FXML
    void player3ImageClicked(MouseEvent event) {
    	currentPlayer = 2;
    	update();
    }

    @FXML
    void player4ImageClicked(MouseEvent event) {
    	currentPlayer = 3;
    	update();
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
    void initialize() 
    {
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
    }

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app)
	{
        ps = playerSave;
        update();
	}

	@Override
	public void update() 
	{
		VBox content = new VBox();
		for (Equipment e : ps.getInventory().getEquipment())
		{
			LootListItem li = new LootListItem(e);
			content.getChildren().add(li);
		}
		equipmentList.setContent(content);
		currentPlayerImage.setImage(ps.getPlayers()[currentPlayer].getImage());
		currentPlayerName.setText(ps.getPlayers()[currentPlayer].getName());
		currentPlayerStats.setText(ps.getPlayers()[currentPlayer].loadStats());
		
		this.mainArmSlot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.MAIN_HAND)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.MAIN_HAND).getImage() : null);
		this.sideArmSlot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.OFFHAND)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.OFFHAND).getImage() : null);
		this.helmSlot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.HELM)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.HELM).getImage() : null);
		this.legsSlot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.LEGS)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.LEGS).getImage() : null);
		this.bodySlot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.BODY)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.BODY).getImage() : null);
		this.ring1Slot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING1)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING1).getImage() : null);
		this.ring2Slot.setImage(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING2)!=null? ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING2).getImage() : null);
		
		if (ps.getPlayers()[0] != null) player1Image.setImage(ps.getPlayers()[0].getImage());
		if (ps.getPlayers()[1] != null) player2Image.setImage(ps.getPlayers()[1].getImage());
		if (ps.getPlayers()[2] != null) player3Image.setImage(ps.getPlayers()[2].getImage());
		if (ps.getPlayers()[3] != null) player4Image.setImage(ps.getPlayers()[3].getImage());
	}
}
