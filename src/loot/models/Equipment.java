package loot.models;

import interfaces.IHasStats;

public class Equipment extends Loot implements IHasStats{

	private boolean isEquipped;
	protected int maxHealth;
	protected int strength;
	protected int dexterity;
	protected int intelligence;
	protected int armor;
<<<<<<< HEAD
=======
	
	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return maxHealth;
	}
	
	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return strength;
	}
	
	@Override
	public int getDexterity() {
		// TODO Auto-generated method stub
		return dexterity;
	}
	
	@Override
	public int getIntelligence() {
		// TODO Auto-generated method stub
		return intelligence;
	}
	
	@Override
	public int getArmor() {
		// TODO Auto-generated method stub
		return armor;
	}
	
	@Override
	public void setMaxHealth(int hp) {
		// TODO Auto-generated method stub
		this.maxHealth = hp;
	}
	
	@Override
	public void setStrength(int str) {
		// TODO Auto-generated method stub
		this.strength = str;
	}
	
	@Override
	public void setDexterity(int dex) {
		// TODO Auto-generated method stub
		this.dexterity = dex;
	}
	
	@Override
	public void setIntelligence(int intel) {
		// TODO Auto-generated method stub
		this.intelligence = intel;
	}
	
	@Override
	public void setArmor(int armor) {
		// TODO Auto-generated method stub
		this.armor = armor;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
>>>>>>> origin/Quest_loot_Player_Models
}
