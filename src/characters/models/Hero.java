package characters.models;

import controllers.HeroGenerator;

public class Hero extends Character
{
	private int currentExp;
	private int requiredExp = 10;
	private final float LEVELUP_MULTIPLIER = 1.5f;
	
	public int getCurrentExp()
	{
		return currentExp;
	}
	
	public int getRequiredExp()
	{
		return requiredExp;
	}
	
	public void addExp(int amount)
	{
		currentExp += amount;
		
		while(currentExp>requiredExp)
		{
			currentExp -= requiredExp;
			requiredExp = (int)Math.floor(requiredExp * LEVELUP_MULTIPLIER);
			level++;
			HeroGenerator.levelUpHero(this);
		}
	}
}
