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
		
		int monsterLevel1 = heroTotalLevel -3;
		int monsterLevel2 = heroTotalLevel -1;
		int monsterLevel3 = heroTotalLevel;
		int monsterLevel4 = heroTotalLevel +3;
		int monsterLevel5 = heroTotalLevel *2;
		
		if(monsterLevel1 <=0) {
			monsterLevel1=1;
		}
		
		if(monsterLevel2 <=0) {
			monsterLevel2=1;
		}
				
		switch(difficulty) {
		case 1: 
			quest = new Quest(MonsterGenerator.generateMonsters(monsterLevel1), monsterLevel1);
		break;
		
		case 2: 
			quest = new Quest(MonsterGenerator.generateMonsters(monsterLevel2), monsterLevel2);
		break;
		
		case 3:
			quest = new Quest(MonsterGenerator.generateMonsters(monsterLevel3), monsterLevel3);
		break;
		
		case 4: 
			quest = new Quest(MonsterGenerator.generateMonsters(monsterLevel4), monsterLevel4);
		break;
		
		case 5: 
			quest = new Quest(MonsterGenerator.generateMonsters(monsterLevel5), monsterLevel5);
		break;
		}
		
		return quest;
	}


		



}

