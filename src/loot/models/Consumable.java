package loot.models;

public class Consumable extends Loot
{

	private int effectOnHealth;
	
	public int getEffectOnHealth() 
	{
		return effectOnHealth;
	}

	public void setEffectOnHealth(int effectOnHealth) 
	{
		this.effectOnHealth = effectOnHealth;
	}
	
	public void use(characters.models.Character target) 
	{
		System.out.println("FOOOOOOOOO "+effectOnHealth);
		target.adjustHealth(effectOnHealth);
	}
}
