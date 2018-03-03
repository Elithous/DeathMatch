package views.models;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import loot.models.Loot;

public class LootListItem extends HBox
{
	private Loot loot;
	private ImageView icon;
	private Label info;
	
	public LootListItem(Loot loot) 
	{
		this.loot = loot;
		icon = new ImageView();
		icon.setFitHeight(60);
		icon.setFitWidth(60);
		info = new Label(loot.getName());
		this.getChildren().add(icon);
		this.getChildren().add(info);
		updateInfo();
	}

	public void updateInfo()
	{
		icon.setImage(loot.getImage());
		info.setText(loot.getName());
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
