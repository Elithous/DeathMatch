package models.quests;

import characters.models.Monster;

public class Quest {

	public final Monster[] monsters;
	public final int difficulty;
	public Quest(Monster[] monsters, int difficulty) {
		this.monsters = monsters;
		this.difficulty = difficulty;
	}
	
	public Monster getStrongestMonster() {
		Monster monster = monsters[0];
		for (Monster nextMonster : monsters) {
			if (nextMonster.getLevel() > monster.getLevel()) {
				monster = nextMonster;
			}
		}
		return monster;
	}
	
}
