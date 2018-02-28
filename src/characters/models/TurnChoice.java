package characters.models;

import characters.enums.BattleChoice;

public class TurnChoice
{
	public final Character character;
	public final BattleChoice choice;
	
	public TurnChoice(Character character, BattleChoice choice)
	{
		this.character = character;
		this.choice = choice;
	}	
}
