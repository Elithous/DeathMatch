package loot.models;

import character.models.Characters;

public class Consumable extends Loot{

	private int effectOnHealth;
	
	public Consumable(int effectOnHealth) {
		this.setEffectOnHealth(effectOnHealth);
	}

	public int getEffectOnHealth() {
		return effectOnHealth;
	}

	public void setEffectOnHealth(int effectOnHealth) {
		this.effectOnHealth = effectOnHealth;
	}
	
	public void use(Character target) {
		
	}
}
