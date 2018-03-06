package characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import characters.enums.EquipmentSlot;
import characters.enums.MonsterType;
import characters.models.Monster;
import javafx.scene.image.Image;
import loot.LootGenerator;
import loot.enums.ArmorType;
import loot.models.Armor;

public class MonsterGenerator {

	private static HashMap<MonsterType, Monster> baseMonsters = new HashMap<>();
	
	/**
	 * A method that will generate monsters baised on the level that is put in. The kind of monster that is generated is dependent on the level put in.
	 * @param levelTotal - The total level of monsters that will be generated. Note that it will try to make the level of the monsters 1/4 of the total level.
	 * @return An array of monster, where the length of the array is the number of monsters, which will be between 1 and 4.
	 */
	public static Monster[] generateMonsters(int levelTotal) {
		
		if(baseMonsters.isEmpty()) {
			initializeMonsters();
		}
		int monsterLvlBase = levelTotal / 4;
		Random rand = new Random();
		ArrayList<Monster> monsters = new ArrayList<>();
		
		for (int x = 0; x < 4; x++) {
			if (levelTotal > monsterLvlBase) {
				int monsterLevel = rand.nextInt(levelTotal - monsterLvlBase) + monsterLvlBase;
				levelTotal -= monsterLevel;
				monsters.add(generateMonster(monsterLevel));
			} else {
				monsters.add(generateMonster(levelTotal));
				x = 4;
			}
		}
		Monster[] result = new Monster[monsters.size()];
		for (int x = 0; x < result.length; x++) {
			result[x] = (Monster) monsters.toArray()[x];
		}
		
		return result;
	}
	
	private static Monster generateMonster(int level) {
		//TODO
		int levelRange = 30 / MonsterType.values().length;
		Random rand = new Random();
		MonsterType monsterType = MonsterType.values()[(level / levelRange >= MonsterType.values().length ? MonsterType.values().length - 1 : level / levelRange)];
		Monster monsterBase = baseMonsters.get(monsterType);
		
		if (monsterType == MonsterType.Goblin || monsterType == MonsterType.Skeleton || monsterType == MonsterType.Imp || monsterType == MonsterType.Mage) {
			
			monsterBase.equip(EquipmentSlot.MAIN_HAND, LootGenerator.generateWeapon(level));
			//Armor Chance
			if (rand.nextInt(100) + 1 > 50) {
				Armor armorPeice;
				do {
					armorPeice = LootGenerator.generateArmor(level);
				} while(armorPeice.armorType != ArmorType.HELM);
				
				monsterBase.equip(EquipmentSlot.HELM, armorPeice);
				
				do {
					armorPeice = LootGenerator.generateArmor(level);
				} while(armorPeice.armorType != ArmorType.BODY);
				
				monsterBase.equip(EquipmentSlot.BODY, armorPeice);
				
				do {
					armorPeice = LootGenerator.generateArmor(level);
				} while(armorPeice.armorType != ArmorType.LEGS);
			}
			
			//Ring Chance
			if (rand.nextInt(100) + 1 > 66) {
				monsterBase.equip(EquipmentSlot.RING1, LootGenerator.generateRing(level));
			}
			
			//Second Weapon Chance
			if (rand.nextInt(100) + 1 > 66) {
				monsterBase.equip(EquipmentSlot.OFFHAND, LootGenerator.generateWeapon(level));
			}
		}
		
		//Setting Image
		String path = "file:Assets/Monsters/Monster Completed/" + monsterType.name() + "/" + monsterType.name();
		
		if (monsterType == MonsterType.Skeleton) {
			path += rand.nextInt(2);
		} else if (monsterType == MonsterType.Goblin) {
			path += rand.nextInt(4);
		} else if (monsterType == MonsterType.Monster) {
			path += rand.nextInt(8);
		} else if (monsterType == MonsterType.Imp) {
			path += rand.nextInt(3);
		} else if (monsterType == MonsterType.Dragon) {
			path += rand.nextInt(10);
		} else if (monsterType == MonsterType.Boss) {
			path += rand.nextInt(4);
		}
		
		monsterBase.setImage(new Image(path + ".png"));
		
		setStatsForMonster(monsterBase, level);
		return new Monster(monsterBase);
//		monsterBase.setLevel(level);
		
	}
	
	private static void initializeMonsters() {
		
		for(MonsterType monsterType : MonsterType.values()) {
			Random rand = new Random();
			Monster tempMonster = new Monster();
			tempMonster.setName(monsterType.name());
			//TODO Set up the system for images
//			String path = "file:Assets/Monsters/Monster Completed/" + monsterType.name() + "/" + monsterType.name();
//			
//			if (monsterType == MonsterType.Skeleton) {
//				path += rand.nextInt(2);
//			} else if (monsterType == MonsterType.Goblin) {
//				path += rand.nextInt(4);
//			} else if (monsterType == MonsterType.Monster) {
//				path += rand.nextInt(8);
//			} else if (monsterType == MonsterType.Imp) {
//				path += rand.nextInt(3);
//			} else if (monsterType == MonsterType.Dragon) {
//				path += rand.nextInt(10);
//			} else if (monsterType == MonsterType.Boss) {
//				path += rand.nextInt(4);
//			}
//			
//			tempMonster.setImage(new Image(path));
			
			baseMonsters.put(monsterType, tempMonster);
		}
	}
	
	private static void setStatsForMonster(Monster monster, int level) {
		monster.setLevel(level);
		
		monster.setMaxHealth(25);
		monster.setStrength(10);
		monster.setDexterity(10);
		monster.setIntelligence(10);
		
		for(int x = 1; x <= level; x++) {
			monster.setMaxHealth((int)(monster.getMaxHealth() * 1.2f));
			monster.adjustHealth(monster.getMaxHealth());
//			if (monster.getEquipment(EquipmentSlot.MAIN_HAND) == null)
//			{
				monster.setStrength((int)(monster.getStrength() * 1.2f));
				monster.setDexterity((int)(monster.getDexterity() * 1.2f));
				monster.setIntelligence((int)(monster.getDexterity() * 1.2f));
//			}
//			else
//			{
//				switch(((Weapon)monster.getEquipment(EquipmentSlot.MAIN_HAND)).attackType)
//				{
//				case STRENGTH:	monster.setStrength((int)(monster.getStrength() * 1.4f));
//								monster.setDexterity((int)(monster.getDexterity() * 1.07f));
//								monster.setIntelligence((int)(monster.getDexterity() * 1.07f));
//					break;
//				case DEXTERITY: monster.setStrength((int)(monster.getStrength() * 1.07f));
//								monster.setDexterity((int)(monster.getDexterity() * 1.4f));
//								monster.setIntelligence((int)(monster.getDexterity() * 1.07f));
//					break;
//				case INTELLIGENCE:	monster.setStrength((int)(monster.getStrength() * 1.07f));
//									monster.setDexterity((int)(monster.getDexterity() * 1.4f));
//									monster.setIntelligence((int)(monster.getDexterity() * 1.07f));
//					break;
//				}
//			}
		}
	}
}
