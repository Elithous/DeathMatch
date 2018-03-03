package characters.models;

import java.util.Random;

import characters.enums.BattleChoice;
import events.TurnEvent;

public class Monster extends Character
{
	public TurnEvent decideNextTurn(Hero[] heros)
	{
		Random rand = new Random();
		Hero hero = heros[rand.nextInt(heros.length)];
		return new TurnEvent(hero, BattleChoice.ATTACK, null);

	}
}
