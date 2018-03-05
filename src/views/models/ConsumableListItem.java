package views.models;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import loot.models.Consumable;

public class ConsumableListItem extends HBox {
	
	public final Consumable item;
	
	private Button useButton;
	
	public ConsumableListItem(Consumable item) {
		this.item = item;
		ImageView icon = new ImageView(item.getImage());
		Label info = new Label(item.getName());
		useButton = new Button("Use");
		this.getChildren().addAll(icon, info, useButton);
		
		this.setAlignment(Pos.CENTER);
		
		this.spacingProperty().bind(this.widthProperty().divide(10));
		
		SimpleDoubleProperty buttonTextSize = new SimpleDoubleProperty();
		
		buttonTextSize.bind(this.heightProperty().divide(5));
		useButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", buttonTextSize.asString(), ";"));
		
		SimpleDoubleProperty infoTextSize = new SimpleDoubleProperty();
		
		infoTextSize.bind(this.heightProperty().divide(4));
		info.styleProperty().bind(Bindings.concat("-fx-font-size: ", infoTextSize.asString(), ";"));
		
		icon.fitHeightProperty().bind(this.heightProperty().divide(2));
		icon.fitWidthProperty().bind(icon.fitHeightProperty());
	}
	
	public Button getButton() {
		return useButton;
	}
	
}
