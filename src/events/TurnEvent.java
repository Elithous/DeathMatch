package events;

import characters.enums.BattleChoice;
import characters.models.Character;
import lib.Event;

public class TurnEvent extends Event 
{
	public final Character character;
	public final BattleChoice choice;
	
	public TurnEvent(Character character, BattleChoice choice)
	{
		this.character = character;
		this.choice = choice;
	}	
}
