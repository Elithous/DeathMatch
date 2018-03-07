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
	
	public String use(characters.models.Character target, characters.models.Character user) 
	{
		String result =  user.getName()+" used "+name+" on "+target.getName()+"! ";
		if (target.getCurrentHealth() > 0)
		{
			target.adjustHealth(effectOnHealth);
			if (effectOnHealth>0)
				result += "It dealt "+target.getName()+" "+effectOnHealth+" damage!";
			else
				result += "It healed "+target.getName()+" "+effectOnHealth+" health!";
		}
		else
			result += user.getName() + " wasted "+name+"!";
		return result;
	}
}
