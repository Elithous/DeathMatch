package events;

import characters.enums.BattleChoice;

public class TurnEvent
{
	public final characters.models.Character TARGET;
	public final BattleChoice CHOICE;
	
	public TurnEvent(characters.models.Character target, BattleChoice choice)
	{
		TARGET = target;
		CHOICE = choice;
	}

}
