package loot.models;

public class RevivePotion extends Consumable
{
	private final float PERCENT_HEALTH = .1f;
	
	@Override
	public String use(characters.models.Character target, characters.models.Character user) 
	{
		String result =  user.getName()+" used "+name+" on "+target.getName()+"! ";
		if(target.getCurrentHealth() <= 0)
		{
			target.adjustHealth((int)(target.getMaxHealth() * PERCENT_HEALTH));
			return result + user.getName() + " revived "+target.getName();
		}
		return result + user.getName() + " wasted "+name+"!";
	}
}
