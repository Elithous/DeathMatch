package models.quests;

import java.util.Random;

import characters.models.Monster;

public class Quest {

	public final Monster[] monsters;
	public final int difficulty;
	public final String name;
	public Quest(Monster[] monsters, int difficulty) {
		this.monsters = monsters;
		this.difficulty = difficulty;
		int choice = new Random().nextInt(4);
		switch (choice) {
		
		case 0:
			name = "A wild " + getStrongestMonster().getName() + (monsters.length > 1 ? " (and friends) " : " ") + "appeared!";
			break;
		case 1:
			name = "Protect the City!";
			break;
		case 2:
			name = "A strange occurrence";
			break;
		default:
			String monsterName = getStrongestMonster().getName();
			if (monsterName.equalsIgnoreCase("Slime")) {
				name = "Humble Beinnings";
			} else if (monsterName.equalsIgnoreCase("Skeleton")) {
				name = "The Attack of the Dead";
			} else if (monsterName.equalsIgnoreCase("Goblin")) {
				name = "A Robbery of the Smelliest Kind";
			} else if (monsterName.equalsIgnoreCase("Mage")) {
				name = "An Evil Magic Show";
			} else if (monsterName.equalsIgnoreCase("Monster")) {
				name = "Creatures From the Deep Lagoon";
			} else if (monsterName.equalsIgnoreCase("Imp")) {
				name = "Dance of the Demons";
			} else if (monsterName.equalsIgnoreCase("Ratking")) {
				name = "A King's Challenge";
			} else if (monsterName.equalsIgnoreCase("Dragon")) {
				name = "A Firey Fight";
			} else if (monsterName.equalsIgnoreCase("Boss")) {
				name = "The Final Fight?";
			} else {
				name = "An error has occured. Please inform Guppy imediatly.";
			}
		}
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
