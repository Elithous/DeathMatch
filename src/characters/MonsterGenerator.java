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
		if (levelTotal <= 0) {
			levelTotal = 1;
		}
		if(baseMonsters.isEmpty()) {
			initializeMonsters();
		}
		int monsterLvlBase = levelTotal / 4;
		if (monsterLvlBase <= 0) {
			monsterLvlBase = 1;
		}
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
		System.out.println("Level : " + levelTotal);
		System.out.println("Monsters made.");
		
		return result;
	}
	
	private static Monster generateMonster(int level) {
		//TODO
		System.out.println("Making a monster");
		int levelRange = 30 / MonsterType.values().length;
		Random rand = new Random();
		MonsterType monsterType = MonsterType.values()[(level / levelRange >= MonsterType.values().length ? MonsterType.values().length - 1 : level / levelRange)];
		Monster monsterBase = new Monster(baseMonsters.get(monsterType));
		
		if (monsterType == MonsterType.GOBLIN || monsterType == MonsterType.SKELETON || monsterType == MonsterType.IMP || monsterType == MonsterType.MAGE) {
			
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
		
		switch(monsterType) {
		case SKELETON:
			path += rand.nextInt(2);
			break;
		case GOBLIN:
			path += rand.nextInt(4);
			break;
		case MONSTER:
			path += rand.nextInt(8);
			break;
		case IMP:
			path += rand.nextInt(3);
			break;
		case DRAGON:
			path += rand.nextInt(10);
			break;
		case BOSS:
			path += rand.nextInt(4);
			break;
		}
		monsterBase.setImage(path + ".png");
		
		setStatsForMonster(monsterBase, level);
		return monsterBase;
//		monsterBase.setLevel(level);
		
	}
	
	private static void initializeMonsters() {
		
		for(MonsterType monsterType : MonsterType.values()) {
			Random rand = new Random();
			Monster tempMonster = new Monster();
			//Setting Image
			tempMonster.setName(monsterType.name());
			
			baseMonsters.put(monsterType, tempMonster);
		}
		Monster slime = new Monster();
		slime.setName("Slime");
		slime.setAttack(1);
		slime.setArmor(1);
		slime.setStrength(3);
		slime.setDexterity(3);
		slime.setIntelligence(3);
		slime.setMaxHealth(15);
		baseMonsters.put(MonsterType.SLIME, slime);
		
		Monster skeleton = new Monster();
		skeleton.setName("Skeleton");
		skeleton.setAttack(5);
		skeleton.setArmor(3);
		skeleton.setStrength(7);
		skeleton.setDexterity(7);
		skeleton.setIntelligence(7);
		skeleton.setMaxHealth(25);
		baseMonsters.put(MonsterType.SKELETON, skeleton);
		
		Monster goblin = new Monster();
		goblin.setName("Goblin");
		goblin.setAttack(8);
		goblin.setArmor(6);
		goblin.setStrength(10);
		goblin.setDexterity(10);
		goblin.setIntelligence(10);
		goblin.setMaxHealth(35);
		baseMonsters.put(MonsterType.GOBLIN, goblin);
		
		Monster mage = new Monster();
		mage.setName("Mage");
		mage.setAttack(12);
		mage.setArmor(9);
		mage.setStrength(13);
		mage.setDexterity(13);
		mage.setIntelligence(13);
		mage.setMaxHealth(45);
		baseMonsters.put(MonsterType.MAGE, mage);
		
		Monster monster = new Monster();
		monster.setName("Monster");
		monster.setAttack(15);
		monster.setArmor(12);
		monster.setStrength(16);
		monster.setDexterity(16);
		monster.setIntelligence(16);
		monster.setMaxHealth(55);
		baseMonsters.put(MonsterType.MONSTER, monster);

		Monster imp = new Monster();
		imp.setName("Monster");
		imp.setAttack(15);
		imp.setArmor(12);
		imp.setStrength(16);
		imp.setDexterity(16);
		imp.setIntelligence(16);
		imp.setMaxHealth(55);
		baseMonsters.put(MonsterType.IMP, imp);
		
		Monster ratking = new Monster();
		ratking.setName("Monster");
		ratking.setAttack(15);
		ratking.setArmor(12);
		ratking.setStrength(16);
		ratking.setDexterity(16);
		ratking.setIntelligence(16);
		ratking.setMaxHealth(55);
		baseMonsters.put(MonsterType.RATKING, ratking);
		
		Monster dragon = new Monster();
		dragon.setName("Monster");
		dragon.setAttack(15);
		dragon.setArmor(12);
		dragon.setStrength(16);
		dragon.setDexterity(16);
		dragon.setIntelligence(16);
		dragon.setMaxHealth(55);
		baseMonsters.put(MonsterType.DRAGON, dragon);

		Monster boss = new Monster();
		boss.setName("Monster");
		boss.setAttack(15);
		boss.setArmor(12);
		boss.setStrength(16);
		boss.setDexterity(16);
		boss.setIntelligence(16);
		boss.setMaxHealth(55);
		baseMonsters.put(MonsterType.BOSS, boss);
	}
	
	private static void setStatsForMonster(Monster monster, int level) {
		monster.setLevel(level);
		if (monster.getEquipment(EquipmentSlot.MAIN_HAND) == null) {
			monster.setAttack(monster.getStrength());
		}
//		monster.setMaxHealth(25);
//		monster.setStrength(10);
//		monster.setDexterity(10);
//		monster.setIntelligence(10);
		
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
