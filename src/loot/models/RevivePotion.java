package loot.models;

public class RevivePotion extends Consumable
{
	private final float PERCENT_HEALTH = .1f;
	
	public RevivePotion() 
	{
		super(0);
	}

	@Override
	public void use(characters.models.Character target) 
	{
		target.adjustHealth((int)(target.getMaxHealth() * PERCENT_HEALTH));
	}
}
