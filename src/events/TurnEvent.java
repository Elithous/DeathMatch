package events;

import characters.enums.BattleChoice;
import characters.models.Character;
import lib.Event;
import loot.models.Consumable;

public class TurnEvent extends Event 
{
	public final Character character;
	public final BattleChoice choice;
	public final Consumable item;
	
	public TurnEvent(Character character, BattleChoice choice, Consumable item)
	{
		this.character = character;
		this.choice = choice;
		this.item = item;
	}	

}
