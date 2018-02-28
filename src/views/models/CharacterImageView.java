package views.models;

import characters.models.Character;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class CharacterImageView extends Parent{

	private Character character;
	private ImageView characterImage;
	private ProgressBar health;
	
	public CharacterImageView(Character character) {
		this.setCharacter(character);
		setCharacterImage(new ImageView(character.getImage()));
		
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
