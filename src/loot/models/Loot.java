package loot.models;

import java.io.Serializable;

import javafx.scene.image.Image;
import loot.enums.Rarity;

public class Loot implements Serializable
{
	protected String name;
	protected String description;
	protected int value;
	protected transient Image image;
	protected String imageURL;

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	protected Rarity rarity;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Image getImage() 
	{
		if (image == null) image = new Image(imageURL);
		return image;
	}
	
	public void setImage(String url) 
	{
		this.image = new Image(url);
		if (image != null) imageURL = url;
	}
	
	
}
