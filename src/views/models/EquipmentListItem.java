package views.models;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import loot.models.Equipment;
import loot.models.Weapon;

public class EquipmentListItem extends HBox
{
	private Equipment loot;
	private ImageView icon;
	private Label info;
	private HBox me;
	private Label stats;
	private VBox forSpacing = new VBox();
	
	public EquipmentListItem(Equipment loot) 
	{
		this.loot = loot;
		me = this;
		icon = new ImageView();
		icon.setFitHeight(60);
		icon.setFitWidth(60);
		info = new Label(loot.getName()+(loot.isEquipped()? " - Equipped" : ""));
		this.getChildren().add(icon);
		updateInfo();
		stats = new Label();
		StringBuilder sb = new StringBuilder();
		if(loot instanceof Weapon) {
			Weapon wep = (Weapon) loot;
			sb.append(loot.getAttack() > 0 ? "" + wep.getMinAttack() + "-" + wep.getMaxAttack() + " Attack  " : "");
		} else {
			sb.append(loot.getAttack() > 0 ? "+" + loot.getArmor() + " Attack  " : "");
		}
		sb.append(loot.getArmor() > 0 ? "+" + loot.getArmor() + " Armor  " : "");
		sb.append(loot.getMaxHealth() > 0 ? "+" + loot.getMaxHealth() + " Max Health  " : "");
		sb.append(loot.getStrength() > 0 ? "+" + loot.getStrength() + " Strength  " : "");
		sb.append(loot.getDexterity() > 0 ? "+" + loot.getDexterity() + " Dexterity  " : "");
		sb.append(loot.getIntelligence() > 0 ? "+" + loot.getIntelligence() + " Intelligence  " : "");
		sb.append(loot.getRequiredStrength() > 0 ? "" + loot.getRequiredStrength() + " Required Strength  " : "");
		sb.append(loot.getRequiredDexterity() > 0 ? "" + loot.getRequiredDexterity() + " Required Dexterity  " : "");
		sb.append(loot.getRequiredIntelligence() > 0 ? "" + loot.getRequiredIntelligence() + " Required Intelligence  " : "");
		stats.setText(sb.toString());
		stats.setTextFill(new Color(.4, .4, .4, 1));
		forSpacing.getChildren().addAll(info, stats);
		this.getChildren().add(forSpacing);
		
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
