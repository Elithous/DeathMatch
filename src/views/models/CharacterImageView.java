package views.models;

import characters.models.Character;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CharacterImageView extends VBox{

	private Character character;
	private ImageView characterImage = new ImageView();
	private ProgressBar health = new ProgressBar();
	
	public CharacterImageView(Character character) {
		this.setCharacter(character);
		setCharacterImage(new ImageView(character.getImage()));
		this.getChildren().addAll(health, characterImage);
		this.characterImage.fitHeightProperty().bind(this.heightProperty().multiply(.8));
		this.characterImage.fitWidthProperty().bind(this.widthProperty());
		this.health.prefHeightProperty().bind(this.heightProperty().multiply(.2));
		this.health.prefWidthProperty().bind(characterImage.fitWidthProperty());
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

	public void setCharacterImage(ImageView characterImage) {
		this.characterImage = characterImage;
	}

	public ProgressBar getHealth() {
		return health;
	}

	public void setHealth(ProgressBar health) {
		this.health = health;
	}
}
