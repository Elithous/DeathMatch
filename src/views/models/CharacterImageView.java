package views.models;

import characters.models.Character;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import views.controllers.BattleScreenController;

public class CharacterImageView extends VBox {

	private Character character;
	private BattleScreenController bsc;
	private ImageView characterImage;
	private ProgressBar health = new ProgressBar();

	public CharacterImageView(Character character, BattleScreenController bsc) 
	{
		this.setCharacter(character);
		this.bsc = bsc;
		if (character != null)
		{
			characterImage = new ImageView(character.getImage());
			this.getChildren().addAll(health, characterImage);
			this.characterImage.setPreserveRatio(true);
			this.characterImage.fitHeightProperty().bind(this.heightProperty().multiply(.8));
			this.characterImage.fitWidthProperty().bind(this.widthProperty());
			this.health.prefHeightProperty().bind(this.heightProperty().multiply(.2));
			this.health.prefWidthProperty().bind(this.widthProperty().multiply(.35));
			this.health.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			this.health.setStyle("-fx-accent: green;");
			
			characterImage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() 
			{
			     @Override
			     public void handle(MouseEvent event) 
			     {
			    	 bsc.characterClicked(character);
			    	 event.consume();
			     }
			});
			
			update();
		}
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public ImageView getCharacterImage() {
		return characterImage;
	}

	public void setCharacterImage(ImageView characterImage)
	{
		this.characterImage = characterImage;
	}

	public ProgressBar getHealth()
	{
		return health;
	}

	public void setHealth(ProgressBar health)
	{
		this.health = health;
	}
	
	public void update() 
	{
		health.setProgress(((float)character.getCurrentHealth())/character.getMaxHealth());
		this.characterImage.setImage(character.getImage());
	}
}
