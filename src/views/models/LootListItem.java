package views.models;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import loot.models.Loot;

public class LootListItem extends Parent{

	private Loot loot;
	private ImageView equipmentIcon;
	private Label equipmentName;
	
	public LootListItem(Loot loot) {
		this.setLoot(loot);
		setEquipmentIcon(new ImageView(loot.getImage()));
		setEquipmentName(new Label(loot.getName()));
	}

	public Loot getLoot() {
		return loot;
	}

	public void setLoot(Loot loot) {
		this.loot = loot;
	}

	public ImageView getEquipmentIcon() {
		return equipmentIcon;
	}

	public void setEquipmentIcon(ImageView equipmentIcon) {
		this.equipmentIcon = equipmentIcon;
	}

	public Label getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(Label equipmentName) {
		this.equipmentName = equipmentName;
	}
}
