package loot.models;

public class RevivePotion extends Consumable
{
	private final float PERCENT_HEALTH = .1f;
	
	@Override
	public void use(characters.models.Character target) 
	{
		if(target.getCurrentHealth() <= 0) {
			target.adjustHealth((int)(target.getMaxHealth() * PERCENT_HEALTH));
			System.out.println("Success - revive");
		} else {
			System.out.println("Fail - not revive");
		}
	}
}
