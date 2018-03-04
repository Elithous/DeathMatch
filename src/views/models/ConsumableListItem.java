package views.models;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import loot.models.Consumable;

public class ConsumableListItem extends HBox {
	
	public final Consumable item;
	
	public ConsumableListItem(Consumable item) {
		this.item = item;
		ImageView icon = new ImageView(item.getImage());
		Label info = new Label(item.getName());
		this.getChildren().addAll(icon, info);
	}

}
