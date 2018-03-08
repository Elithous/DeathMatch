package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import characters.enums.EquipmentSlot;
import characters.models.Hero;
import controllers.GameApp;
import controllers.HeroGenerator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import loot.models.Equipment;
import models.player.PlayerSave;
import models.quests.Quest;
import views.interfaces.PlayerController;
import views.models.EquipmentListItem;

public class PartyManagementController implements PlayerController
{
	private int currentPlayer = 0;

	@FXML
	private VBox scroll;
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
	private VBox shopBox;
    
    @FXML
    private Pane playerPane;
    
    @FXML
    private ImageView currentPlayerImage;
    
    @FXML
    private ImageView helmBack;

    @FXML
    private ImageView bodyBack;

    @FXML
    private ImageView legsBack;

    @FXML
    private ImageView sideArmBack;

    @FXML
    private ImageView mainArmBack;

    @FXML
    private ImageView ring1Back;

    @FXML
    private ImageView ring2Back;

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
        receiveDrop(event.getDragboard(), EquipmentSlot.BODY);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void bodySlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }
    
    @FXML
    void shopBoxDragDropped(DragEvent event) 
    {
    	Equipment e = (Equipment) event.getDragboard().getContent(EquipmentListItem.lootDataFormat);
    	
    	for (Equipment eq : ps.getInventory().getEquipment())
    	{
    		if (eq.equals(e))
    		{
    			e = eq;
    		}
    	}
    	
    	ps.getInventory().goldTransaction(e.getValue());
    	if (e.isEquipped())
    	for (int i = 0; i < EquipmentSlot.values().length; i++)
    	{
    		if ( ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.values()[i]) == e )
    		{
    			ps.getPlayers()[currentPlayer].equip(EquipmentSlot.values()[i], null);
    		}
    	}
    	
    	ps.getInventory().removeEquipment(e);
    	
    	update();
    	
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void shopBoxDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void bodySlotDragStart(MouseEvent event) 
    {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.BODY)==null) return;

    	Dragboard db = bodySlot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(bodySlot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.BODY));
        content.putString(EquipmentSlot.BODY.toString());
        db.setContent(content);
        
        event.consume();
    }

    @FXML
    void listDragDropped(DragEvent event) 
    {
    	Equipment e = (Equipment) event.getDragboard().getContent(EquipmentListItem.lootDataFormat);
    	
    	for (Equipment eq : ps.getInventory().getEquipment())
    	{
    		if (eq.equals(e))
    		{
    			e = eq;
    		}
    	}	
    	ps.getPlayers()[currentPlayer].equip(EquipmentSlot.valueOf(event.getDragboard().getString()), null);
    	
       	update();
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void listDragOver(DragEvent event) 
    {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void helmSlotDragDropped(DragEvent event) {
        receiveDrop(event.getDragboard(), EquipmentSlot.HELM);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void helmSlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void helmSlotDragStart(MouseEvent event) 
    {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.HELM)==null) return;

    	Dragboard db = helmSlot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(helmSlot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.HELM));
        content.putString(EquipmentSlot.HELM.toString());
        db.setContent(content);
        
        event.consume();
    }

    @FXML
    void hireImageClicked(MouseEvent event) {
    	boolean full = true;
    	Hero[] h = ps.getPlayers().clone();
    	
    		if(h[0]==null) {
				full=false;
			}
    		if(h[1]==null) {
    			full=false;
    		}
    		if(h[2]==null) {
    			full=false;
    		}
    		if(h[3]==null) {
    			full=false;
    		}
    	
    	if(full==false) {
    		if(ps.getInventory().getGold() >=100) {
    			ps.addPlayer(HeroGenerator.generateHero(1));
    			ps.getInventory().goldTransaction(-100);
    		}
    	}
    		update();
    }

    @FXML
    void legsSlotDragDropped(DragEvent event) {
        receiveDrop(event.getDragboard(), EquipmentSlot.LEGS);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void legsSlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void legsSlotDragStart(MouseEvent event) 
    {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.LEGS)==null) return;

    	Dragboard db = legsSlot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(legsSlot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.LEGS));
        content.putString(EquipmentSlot.LEGS.toString());
        db.setContent(content);
        
        event.consume();
    }

    @FXML
    void mainArmSlotDragDropped(DragEvent event) 
    {
        receiveDrop(event.getDragboard(), EquipmentSlot.MAIN_HAND);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void mainArmSlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void mainArmSlotDragStart(MouseEvent event) {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.MAIN_HAND)==null) return;

    	Dragboard db = mainArmSlot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(mainArmSlot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.MAIN_HAND));
        content.putString(EquipmentSlot.MAIN_HAND.toString());
        db.setContent(content);
        
        event.consume();
    }

    @FXML
    void player1ImageClicked(MouseEvent event)  {
    	currentPlayer = 0;
    	update();
    }

    @FXML
    void player2ImageClicked(MouseEvent event) 
    {
    	if (ps.getPlayers()[1] != null)
    	{
    		currentPlayer = 1;
    		update();
    	}
    }


    @FXML
    void player3ImageClicked(MouseEvent event)
    {
    	if (ps.getPlayers()[2] != null)
    	{
    		currentPlayer = 2;
    		update();
    	}
    }

    @FXML
    void player4ImageClicked(MouseEvent event) 
    {
    	if (ps.getPlayers()[3] != null)
    	{
    		currentPlayer = 3;
    		update();
    	}
    }

    @FXML
    void ring1SlotDragDropped(DragEvent event) {
        receiveDrop(event.getDragboard(), EquipmentSlot.RING1);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void ring1SlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void ring1SlotDragStart(MouseEvent event) {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING1)==null) return;

    	Dragboard db = ring1Slot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(ring1Slot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING1));
        content.putString(EquipmentSlot.RING1.toString());
        db.setContent(content);
        
        event.consume();
    }

    @FXML
    void ring2SlotDragDropped(DragEvent event) {
        receiveDrop(event.getDragboard(), EquipmentSlot.RING2);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void ring2SlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void ring2SlotDragStart(MouseEvent event) {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING2)==null) return;

    	Dragboard db = ring2Slot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(ring2Slot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.RING2));
        content.putString(EquipmentSlot.RING2.toString());
        db.setContent(content);
        
        event.consume();
    }

    @FXML
    void sideArmSlotDragDropped(DragEvent event) {
        receiveDrop(event.getDragboard(), EquipmentSlot.OFFHAND);
        event.setDropCompleted(true);
        event.consume();
    }
    
    @FXML
    void sideArmSlotDragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    @FXML
    void sideArmSlotDragStart(MouseEvent event) {
    	if (ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.OFFHAND)==null) return;

    	Dragboard db = sideArmSlot.startDragAndDrop(TransferMode.ANY);
        
        db.setDragView(sideArmSlot.snapshot(null, null));
        
        ClipboardContent content = new ClipboardContent();
        content.put(EquipmentListItem.lootDataFormat, ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.OFFHAND));
        content.putString(EquipmentSlot.OFFHAND.toString());
        db.setContent(content);
        
        event.consume();
    }
    
    private void receiveDrop(Dragboard d, EquipmentSlot es)
    {
    	Equipment e = (Equipment) d.getContent(EquipmentListItem.lootDataFormat);
    	
    	for (Equipment eq : ps.getInventory().getEquipment())
    	{
    		if (eq.equals(e))
    		{
    			e = eq;
    		}
    	}
    	
    	System.out.println("Equip successful - "+ps.getPlayers()[currentPlayer].equip(es, (Equipment)e));
    	update();
    }

    @FXML
    void initialize() {
        topHBox.prefHeightProperty().bind(containerVBox.heightProperty().multiply(.8));
        bottomHBox.prefHeightProperty().bind(containerVBox.heightProperty().multiply(.2));
        bottomHBox.prefWidthProperty().bind(containerVBox.widthProperty());
        
        playerPane.prefWidthProperty().bind(topHBox.widthProperty().multiply(.6));
        playerPane.prefHeightProperty().bind(topHBox.heightProperty());
        
        equipmentList.prefWidthProperty().bind(topHBox.widthProperty().multiply(.4));
        
        scroll.prefWidthProperty().bind(equipmentList.prefWidthProperty().subtract(10));
        scroll.minHeightProperty().bind(equipmentList.heightProperty().subtract(10));
        
        // Bind the image objects in playerPane to positions and sizes
        currentPlayerImage.fitWidthProperty().bind(playerPane.widthProperty().divide(5));
        currentPlayerImage.fitHeightProperty().bind(currentPlayerImage.fitWidthProperty());
        currentPlayerImage.layoutXProperty().bind(playerPane.widthProperty().divide(2).subtract(currentPlayerImage.fitWidthProperty().divide(3)));
        currentPlayerImage.layoutYProperty().bind(playerPane.heightProperty().divide(2).subtract(currentPlayerImage.fitHeightProperty().divide(3)));
        
        SimpleDoubleProperty playerMidX = new SimpleDoubleProperty();
        SimpleDoubleProperty playerMidY = new SimpleDoubleProperty();
        SimpleDoubleProperty playerX = new SimpleDoubleProperty();
        SimpleDoubleProperty playerY = new SimpleDoubleProperty();
        playerX.bind(playerPane.widthProperty().divide(2).subtract(currentPlayerImage.fitWidthProperty().divide(2)));
        playerY.bind(playerPane.heightProperty().divide(2).subtract(currentPlayerImage.fitHeightProperty().divide(2)));
        playerMidX.bind(playerX.add(currentPlayerImage.fitWidthProperty().divide(2)));
        playerMidY.bind(playerY.add(currentPlayerImage.fitHeightProperty().divide(2)));
        
        // Bind each slot location to the player
        helmSlot.fitWidthProperty().bind(playerPane.widthProperty().divide(20));
        helmSlot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        helmSlot.layoutXProperty().bind(playerMidX.subtract(helmSlot.fitWidthProperty().divide(2)));
        helmSlot.layoutYProperty().bind(playerY.subtract(helmSlot.fitHeightProperty().multiply(1.2)));
        
        bodySlot.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        bodySlot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        bodySlot.layoutXProperty().bind(playerX.subtract(bodySlot.fitWidthProperty().multiply(1.2)));
        bodySlot.layoutYProperty().bind(playerMidY.subtract(bodySlot.fitHeightProperty().divide(2)));
        
        legsSlot.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        legsSlot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        legsSlot.layoutXProperty().bind(playerMidX.subtract(legsSlot.fitWidthProperty().divide(2)));
        legsSlot.layoutYProperty().bind(playerY.add(currentPlayerImage.fitHeightProperty()).add(legsSlot.fitHeightProperty().multiply(1.2)));
        
        mainArmSlot.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        mainArmSlot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        mainArmSlot.layoutXProperty().bind(playerX.subtract(mainArmSlot.fitWidthProperty().multiply(1.2)));
        mainArmSlot.layoutYProperty().bind(playerMidY.subtract(currentPlayerImage.fitHeightProperty().divide(3)).subtract(mainArmSlot.fitHeightProperty().divide(2)));
        
        sideArmSlot.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        sideArmSlot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        sideArmSlot.layoutXProperty().bind(playerX.add(currentPlayerImage.fitWidthProperty()).add(sideArmSlot.fitWidthProperty().multiply(0.2)));
        sideArmSlot.layoutYProperty().bind(playerMidY.subtract(currentPlayerImage.fitHeightProperty().divide(3)).subtract(sideArmSlot.fitHeightProperty().divide(2)));
        
        ring1Slot.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        ring1Slot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        ring1Slot.layoutXProperty().bind(playerMidX.subtract(ring1Slot.fitWidthProperty().multiply(2.1)));
        ring1Slot.layoutYProperty().bind(playerY.add(currentPlayerImage.fitHeightProperty()).add(ring1Slot.fitHeightProperty().multiply(1.2)));
        
        ring2Slot.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        ring2Slot.fitHeightProperty().bind(helmSlot.fitWidthProperty());
        ring2Slot.layoutXProperty().bind(playerMidX.add(ring1Slot.fitWidthProperty().multiply(2.1)));
        ring2Slot.layoutYProperty().bind(playerY.add(currentPlayerImage.fitHeightProperty()).add(ring2Slot.fitHeightProperty().multiply(1.2)));
        
        // Bind each slot back image to the slot
        helmBack.fitWidthProperty().bind(helmSlot.fitWidthProperty());
        helmBack.fitHeightProperty().bind(helmSlot.fitHeightProperty());
        helmBack.layoutXProperty().bind(helmSlot.layoutXProperty());
        helmBack.layoutYProperty().bind(helmSlot.layoutYProperty());

        bodyBack.fitWidthProperty().bind(bodySlot.fitWidthProperty());
        bodyBack.fitHeightProperty().bind(bodySlot.fitHeightProperty());
        bodyBack.layoutXProperty().bind(bodySlot.layoutXProperty());
        bodyBack.layoutYProperty().bind(bodySlot.layoutYProperty());

        legsBack.fitWidthProperty().bind(legsSlot.fitWidthProperty());
        legsBack.fitHeightProperty().bind(legsSlot.fitHeightProperty());
        legsBack.layoutXProperty().bind(legsSlot.layoutXProperty());
        legsBack.layoutYProperty().bind(legsSlot.layoutYProperty());

        mainArmBack.fitWidthProperty().bind(mainArmSlot.fitWidthProperty());
        mainArmBack.fitHeightProperty().bind(mainArmSlot.fitHeightProperty());
        mainArmBack.layoutXProperty().bind(mainArmSlot.layoutXProperty());
        mainArmBack.layoutYProperty().bind(mainArmSlot.layoutYProperty());

        sideArmBack.fitWidthProperty().bind(sideArmSlot.fitWidthProperty());
        sideArmBack.fitHeightProperty().bind(sideArmSlot.fitHeightProperty());
        sideArmBack.layoutXProperty().bind(sideArmSlot.layoutXProperty());
        sideArmBack.layoutYProperty().bind(sideArmSlot.layoutYProperty());

        ring1Back.fitWidthProperty().bind(ring1Slot.fitWidthProperty());
        ring1Back.fitHeightProperty().bind(ring1Slot.fitHeightProperty());
        ring1Back.layoutXProperty().bind(ring1Slot.layoutXProperty());
        ring1Back.layoutYProperty().bind(ring1Slot.layoutYProperty());

        ring2Back.fitWidthProperty().bind(ring2Slot.fitWidthProperty());
        ring2Back.fitHeightProperty().bind(ring2Slot.fitHeightProperty());
        ring2Back.layoutXProperty().bind(ring2Slot.layoutXProperty());
        ring2Back.layoutYProperty().bind(ring2Slot.layoutYProperty());
        
        // Text size for the name label and the stats label
        SimpleDoubleProperty nameTextSize = new SimpleDoubleProperty();
        nameTextSize.bind(playerPane.heightProperty().divide(15));
        currentPlayerName.styleProperty().bind(Bindings.concat(currentPlayerName.styleProperty().getValue(), "-fx-font-size: ", nameTextSize.asString(), ";"));
        currentPlayerName.layoutXProperty().bind(playerPane.widthProperty().divide(2).subtract(currentPlayerName.widthProperty().divide(2)));
        currentPlayerName.layoutYProperty().bind(playerPane.heightProperty().multiply(.1));
        currentPlayerName.prefWidthProperty().bind(playerPane.widthProperty());

        SimpleDoubleProperty statsTextSize = new SimpleDoubleProperty();
        statsTextSize.bind(playerPane.heightProperty().divide(30));
        currentPlayerStats.styleProperty().bind(Bindings.concat(currentPlayerStats.styleProperty().getValue(), "-fx-font-size: ", statsTextSize.asString(), ";"));
        currentPlayerStats.prefHeightProperty().bind(playerPane.heightProperty().multiply(.5));
        currentPlayerStats.prefWidthProperty().bind(playerPane.widthProperty().multiply(.2));
        currentPlayerStats.layoutXProperty().bind(playerPane.widthProperty().multiply(.05));
        currentPlayerStats.layoutYProperty().bind(playerPane.heightProperty().divide(2).subtract(currentPlayerStats.heightProperty().divide(2)));
        
        //Bind the shop size to the bottom HBox
        shopBox.prefWidthProperty().bind(bottomHBox.widthProperty().multiply(.3));
        ((ImageView) shopBox.getChildren().get(0)).fitHeightProperty().bind(shopBox.heightProperty().subtract(10));
        ((ImageView) shopBox.getChildren().get(0)).fitWidthProperty().bind(shopBox.widthProperty().subtract(10));
        //((ImageView) shopBox.getChildren().get(0))
        
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
		
		VBox content = scroll;
		content.getChildren().removeAll(scroll.getChildren());

		HBox goldBox = new HBox();
		Label goldLabel = new Label("" + ps.getInventory().getGold());
		ImageView goldImage = new ImageView("file:Assets/Other/Gold.png");
		goldBox.getChildren().addAll(goldImage, goldLabel);
		content.getChildren().add(goldBox);
		
		if(ps.getPlayers()[0]==null) {
			currentPlayer=1;
		}
		
    	System.out.println(ps.getPlayers()[currentPlayer].getEquipment(EquipmentSlot.MAIN_HAND));
		for (Equipment e : ps.getInventory().getEquipment())
		{
			System.out.println(e);
			EquipmentListItem li = new EquipmentListItem(e);
			content.getChildren().add(li);
		}		
		equipmentList.setContent(content);
		
		if(content.getHeight() < equipmentList.getHeight()) {
			content.setPrefHeight(equipmentList.getHeight() - 10);
		}
		
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
		
//		for (int i = 0; i < 3; i++) {
//		if(ps.getPlayers()[i] == null) {
//			hireImage.setVisible(true);
//		}
//		}
		
	}
}
