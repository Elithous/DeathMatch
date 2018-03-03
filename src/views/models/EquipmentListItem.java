package views.models;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import loot.models.Equipment;
import loot.models.Loot;

public class EquipmentListItem extends HBox
{
	private Equipment loot;
	private ImageView icon;
	private Label info;
	private HBox me;
	
	public EquipmentListItem(Equipment loot) 
	{
		this.loot = loot;
		me = this;
		icon = new ImageView();
		icon.setFitHeight(60);
		icon.setFitWidth(60);
		info = new Label(loot.getName()+(loot.isEquipped()? " - Equipped" : ""));
		this.getChildren().add(icon);
		this.getChildren().add(info);
		updateInfo();
		
		if (!loot.isEquipped())
		this.setOnDragDetected(new EventHandler<MouseEvent>()
		{
		    public void handle(MouseEvent event) 
		    {
		        Dragboard db = me.startDragAndDrop(TransferMode.ANY);
		        
		        db.setDragView(me.snapshot(null, null));
		        
		        ClipboardContent content = new ClipboardContent();
		        content.put(lootDataFormat, loot);
		        db.setContent(content);
		        
		        event.consume();
		    }
		});
	}
	
	public static final DataFormat lootDataFormat = new DataFormat("loot.models.Loot");

	public void updateInfo()
	{
		icon.setImage(loot.getImage());
		info.setText(loot.getName()+(loot.isEquipped()? " - Equipped" : ""));
		switch(loot.getRarity())
		{
		case EPIC: info.setTextFill(Color.PURPLE);
			break;
		case LEGENDARY:info.setTextFill(Color.GOLD);
			break;
		case NORMAL:info.setTextFill(Color.BLACK);
			break;
		case RARE:info.setTextFill(Color.GREEN);
			break;			
		}
	}
}
