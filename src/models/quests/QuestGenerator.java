package models.quests;

import characters.MonsterGenerator;
import characters.models.Hero;
import characters.models.Monster;
import models.player.PlayerSave;

public class QuestGenerator {
	
	
	public static Quest[] generateQuests(PlayerSave playerSave) {
		Quest[] quests = {
			generateQuest(playerSave,1), generateQuest(playerSave,2), generateQuest(playerSave,3), generateQuest(playerSave,4), generateQuest(playerSave,5)
		};
		return quests;
	}
	
	private static Quest generateQuest(PlayerSave playerSave, int difficulty) {
		Hero[] h = playerSave.getPlayers();
		int heroTotalLevel = 0;
		Quest quest = new Quest(MonsterGenerator.generateMonsters(heroTotalLevel), 0);
		
		
		for(int i=0; i<playerSave.getPlayers().length; i++) {
			heroTotalLevel = h[i].getLevel();
		}
		
		int monsterLevel1 = heroTotalLevel -2;
		int monsterLevel2 = heroTotalLevel -1;
		int monsterLevel3 = heroTotalLevel;
		int monsterLevel4 = heroTotalLevel +1;
		int monsterLevel5 = heroTotalLevel +2;
		
		if(monsterLevel1 <=0) {
			monsterLevel1=1;
		}
		
		if(monsterLevel2 <=0) {
			monsterLevel2=1;
		}
				
		switch(difficulty) {
		case 1: 
			quest = new Quest(MonsterGenerator.generateMonsters(heroTotalLevel -2), heroTotalLevel -2);
		break;
		
		case 2: 
			quest = new Quest(MonsterGenerator.generateMonsters(heroTotalLevel -1), heroTotalLevel -1);
		break;
		
		case 3:
			quest = new Quest(MonsterGenerator.generateMonsters(heroTotalLevel), heroTotalLevel);
		break;
		
		case 4: 
			quest = new Quest(MonsterGenerator.generateMonsters(heroTotalLevel +1), heroTotalLevel +1);
		break;
		
		case 5: 
			quest = new Quest(MonsterGenerator.generateMonsters(heroTotalLevel +2), heroTotalLevel +2);
		break;
		}
		
		return quest;
	}


		



}

