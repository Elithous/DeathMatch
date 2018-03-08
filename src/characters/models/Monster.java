package characters.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import characters.enums.BattleChoice;
import characters.enums.EquipmentSlot;
import events.TurnEvent;

public class Monster extends Character
{
	public Monster() {
		
	}
	
	public Monster(Monster clone) {
		super(clone);
	}
	
	public TurnEvent decideNextTurn(Hero[] heros)
	{
		
		Random rand = new Random();
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		heroes.addAll(Arrays.asList(heros));
		heroes.removeAll(Collections.singleton(null));
		return new TurnEvent(heroes.get(rand.nextInt(heroes.size())), BattleChoice.ATTACK, null);

	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public int getAttack() {
		if (this.getEquipment(EquipmentSlot.MAIN_HAND) == null) {
			return new Random().nextInt((attack * 5 / 4) - (attack * 3 / 4)) + (attack * 3 / 4);
		} else {
			return super.getAttack();
		}
	}
}
