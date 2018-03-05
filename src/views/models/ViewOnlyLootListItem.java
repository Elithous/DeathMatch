package views.models;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import loot.models.Equipment;
import loot.models.Loot;

public class ViewOnlyLootListItem extends HBox {
	public final Loot loot;

	public ViewOnlyLootListItem(Loot loot) {
		this.loot = loot;
		ImageView icon = new ImageView(loot.getImage());
		Label info = new Label(loot.getName());
		if (loot instanceof Equipment)
			switch (loot.getRarity()) {
			case EPIC:
				info.setTextFill(Color.PURPLE);
				break;
			case LEGENDARY:
				info.setTextFill(Color.GOLD);
				break;
			case NORMAL:
				info.setTextFill(Color.BLACK);
				break;
			case RARE:
				info.setTextFill(Color.GREEN);
				break;
			}
		this.getChildren().addAll(icon, info);
	}
}
