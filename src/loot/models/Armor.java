package loot.models;

import loot.enums.ArmorType;

public class Armor extends Equipment{

	public final ArmorType armorType;

	public Armor(ArmorType armorType) {
		this.armorType = armorType;
	}
}
