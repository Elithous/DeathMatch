package loot.models;

import interfaces.IHasStats;

public class Equipment extends Loot implements IHasStats{

	private boolean isEquipped;
	protected int maxHealth;
	protected int strength;
	protected int dexterity;
	protected int intelligence;
	protected int armor;
	protected int requiredStrength;
	protected int requiredDexterity;
	protected int requiredIntelligence;

	public int getRequiredStrength() 
	{
		return requiredStrength;
	}

	public void setRequiredStrength(int requiredStrength) 
	{
		this.requiredStrength = requiredStrength;
	}

	public int getRequiredDexterity() 
	{
		return requiredDexterity;
	}

	public void setRequiredDexterity(int requiredDexterity) 
	{
		this.requiredDexterity = requiredDexterity;
	}

	public int getRequiredIntelligence() 
	{
		return requiredIntelligence;
	}

	public void setRequiredIntelligence(int requiredIntelligence) 
	{
		this.requiredIntelligence = requiredIntelligence;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}
	
	@Override
	public int getStrength() {
		return strength;
	}
	
	@Override
	public int getDexterity() {
		return dexterity;
	}
	
	@Override
	public int getIntelligence() {
		return intelligence;
	}
	
	@Override
	public int getArmor() {
		return armor;
	}
	
	@Override
	public void setMaxHealth(int hp) {
		this.maxHealth = hp;
	}
	
	@Override
	public void setStrength(int str) {
		this.strength = str;
	}
	
	@Override
	public void setDexterity(int dex) {
		this.dexterity = dex;
	}
	
	@Override
	public void setIntelligence(int intel) 
	{
		this.intelligence = intel;
	}
	
	@Override
	public void setArmor(int armor) {
		this.armor = armor;
	}

	public boolean isEquipped() {
		return isEquipped;
	}

	public void setEquipped(boolean isEquipped) {
		this.isEquipped = isEquipped;
	}
}
