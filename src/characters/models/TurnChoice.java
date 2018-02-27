package characters.models;

import characters.enums.BattleChoice;

public class TurnChoice 
{
	public final Character TARGET;
	public final BattleChoice CHOICE;
	
	public TurnChoice(Character target, BattleChoice choice)
	{
		TARGET = target;
		CHOICE = choice;
	}
}
