package loot;

import java.util.ArrayList;
import java.util.Random;

import loot.enums.ArmorType;
import loot.enums.AttackType;
import loot.enums.Rarity;
import loot.enums.WeaponType;
import loot.models.Armor;
import loot.models.Consumable;
import loot.models.Equipment;
import loot.models.Loot;
import loot.models.RevivePotion;
import loot.models.Weapon;
import models.quests.Quest;

public class LootGenerator 
{
	private static String[][] names = new String[][]
			{
				{"Strength of Zeus", "Shortsword", "Sword", "Greatsword", "Thief's Dagger", "Improved Sword", "Reinforced Shortsword", "Reinforced Sword", "Reinforced Greatsword", "Hammer Blade", "Great Blade", "Stabbing Sword", "Spiked Sword", "Sword of Canyons", "Sword of Storms", "Sword of Illness", "Sword of Winter", "Shortsword of Darkness", "Sword of a Thousand Fires", "Poseidons' Gift"},
				{"Lance of Cassius", "Wooden Spear", "Ugandan Spear", "Metal Spear", "Naginata", "Halberd", "Elite Guard Lance", "Darksteel Spear", "Holy Lance"},
				{"Axe of St. Helens", "Axe", "Double-Sided Axe", "Darksteel Axe", "Darksteel Double-Sided Axe", "Ugandan Axe", "Executor Axe", "Saw Axe", "Axe of Harvest", "Axe of the Forest"},
				{"King's Crown", "Wood Shield", "Wood Emblem Shield", "Reinforced Shield", "Reinforced Tower Shield", "Bone Shield", "Dark Bone Shield", "Iron Shield", "Iron Pie Shield", "Iron Emblem Shield", "Iron Tower Shield", "Darksteel Shield", "King's Guard Shield"},
				{"Phoenix Bow", "Wood Bow", "Hardwood Bow", "Darkwood Bow", "Precision Bow", "Gold Bow", "Ugandan Bow", "King's Bow", "Golden Wood Bow", "Bow of Winter", "Bow of Illness", "Bow of a Thousand Fires", "Fallen Angel Bow", "Risen Angel Bow"},
				{"Wand of the Sun", "Spellcasting 101", "Advanced Spellcasting", "Expert Spellcasting", "Udandan Wand", "Corrupted Magic", "Secrets of the Dark Arts", "Wand of the Hero", "Gold Wand", "King's Wand", "Grand Paladin's Book", "Grand Paladin's Wand"}
			};

	private static String[] ringNames = new String[] {"Ring of Death", "Ring of Life","Ring of Water","Ring of Fire","Ring of Grass","Ring of Poison","Ring of Ice","Ring of Lava", "Ring of Darkness","Ring of Light"};

	private static String[][] armorNames = 
		{
				{"Angel's Helm", "Leather Hat", "Iron Helm", "Knight's Helm", "Crusader's Helm", "Cobalt Helm", "Intimidating Steel Mask", "Tribal Steel Helm", "Samurai Helm", "King's Guard Helm", "King's Personal Guard Helm", "Winged Helm", "Waster's Helm", "Champion's Helm", "Legend's Helm", "Ethereal Helm"},
				{"Angel's Chest Plate", "Leather Shirt", "Iron Chest Plate", "Knight's Chest Plate", "Crusader's Chest Plate", "Cobalt Chest Plate", "Intimidating Steel Chest Plate", "Tribal Steel Chest Plate", "Samurai Chest Plate", "King's Guard Chest Plate", "King's Personal Guard Chest Plate", "Winged Chest Plate", "Master's Chest Plate", "Champion's Chest Plate", "Legend's Chest Plate", "Ethereal Plate"},
				{"Angel's Boots", "Leather Boots", "Iron Boots", "Knight's Boots", "Crusader's Boots", "Tundra Explorer's Boots", "Intimidating Boots", "Tribal Steel Boots", "Samurai Boots", "King's Guard Boots", "King's Personal Guard Boots", "Winged Boots"}
		};
	private static String[] paths = new String[] {"file:Assets/Weapons/Swords/Sword","file:Assets/Weapons/Spear/Spear", "file:Assets/Weapons/Axe/Axe", "file:Assets/Weapons/Shields/Shield", "file:Assets/Weapons/Bow/Bow", "file:Assets/Weapons/Magic/Magic"};
	private static String[] armorPaths = {"file:Assets/Armor/Helm/Helm", "file:Assets/Armor/Body/Body", "file:Assets/Armor/Legs/Legs" ,"file:Assets/Armor/Rings/Ring"};
	private static String[][] consumableNames = new String[][] {{"Revive Potion", "Small Health Potion", "Medium Health Potion", "Large Health Potion"},
																{"Throwing Knife", "Shuriken", "Tomahawk", "Einstein's Big Toy"}};		
	private static String consumablePaths[] = new String[] {"file:Assets/Consumables/Potion", "file:Assets/Consumables/Damage"};
	
	
	
	private static final int WEAPON_RATIO = 10;
	private static final int ARMOR_RATIO = 10;
	private static final int RING_RATIO = 5;
	private static final int CONSUMABLES_RATIO = 15;
	private static final float POTION_TO_DAMAGE_RATIO = .5f;
	
	
	private static final int VALUE_PER_DIFFICULTY = 10;
	
	private static final int NORMAL_RATIO = 50;
	private static final int RARE_RATIO = 30;
	private static final int EPIC_RATIO = 10;
	private static final int LEGENDARY_RATIO = 1;
	
	private static final int AMOUNT_OF_SPEARS = 9;
	private static final int AMOUNT_OF_SWORDS = names[0].length;
	private static final int AMOUNT_OF_AXES = names[2].length;
	private static final int AMOUNT_OF_SHIELDS = names[3].length;
	private static final int AMOUNT_OF_BOWS = names[4].length;
	private static final int AMOUNT_OF_MAGIC = names[5].length;
	
	private static final int VALUE_PER_ATTACK_POINT = 3;
	private static final int VALUE_PER_ARMOR_POINT = 5;
	private static final int VALUE_PER_HEALTH_POINT = 2;
	private static final float REVIVE_POTION_CHANCE = .2f;

	public static LootGeneratorResult generateLoot(Quest quest)
	{
		final int startingValue = quest.difficulty * VALUE_PER_DIFFICULTY;
		int valueLeft = startingValue;
		ArrayList<Loot> loot = new ArrayList<>();
		Random rand = new Random();
		
		while (((float)valueLeft)/startingValue> .3f)
		{
			Loot item = null;
			int choice = rand.nextInt(WEAPON_RATIO+ARMOR_RATIO+RING_RATIO+CONSUMABLES_RATIO);
			if (choice < WEAPON_RATIO)
				item = generateWeapon(valueLeft, quest.difficulty);
			else if (choice < WEAPON_RATIO + ARMOR_RATIO)
				item = generateArmor(valueLeft, quest.difficulty);
			else if (choice < WEAPON_RATIO + ARMOR_RATIO + RING_RATIO)
				item = generateRing(valueLeft, quest.difficulty);
			else
				item = generateConsumable(valueLeft, quest.difficulty);
			
			if (item==null) break;
			
			valueLeft-=item.getValue();
			loot.add(item);
		}
		
		return new LootGeneratorResult(loot, valueLeft);
	}
	
	public static Armor generateArmor(int value, int level) 
	{
		Random rand = new Random();
		int armorType = rand.nextInt(ArmorType.values().length - 1);
		Armor armor;
		
		int armorGrade = level/3 - 1 + rand.nextInt(3);
		armorGrade = armorGrade < 1 ? 1 : armorGrade;
		
		switch (armorType) {
			case 0: //Helm
				armor = new Armor(ArmorType.HELM);
				armorGrade = armorGrade >= armorNames[0].length ? armorNames[0].length - 1 : armorGrade;
				break;
			case 1: //Body
				armor = new Armor(ArmorType.BODY);
				armorGrade = armorGrade >= armorNames[1].length ? armorNames[1].length - 1 : armorGrade;
				break;
			case 2:
				armor = new Armor(ArmorType.LEGS);
				armorGrade = armorGrade >= armorNames[2].length ? armorNames[2].length - 1 : armorGrade;
				break;
			default: armor = null;
		}
		
		armor.setName(armorNames[armorType][armorGrade]);
		
		int armorAmount = getArmorFromLevel(level);
		
		armor.setArmor(armorAmount);
		
		int rarity = rand.nextInt(NORMAL_RATIO+RARE_RATIO+EPIC_RATIO+LEGENDARY_RATIO);
		if (rarity < NORMAL_RATIO)
			armor.setRarity(Rarity.NORMAL);
		else if (rarity < NORMAL_RATIO + RARE_RATIO)
			armor.setRarity(Rarity.RARE);
		else if (rarity < NORMAL_RATIO + RARE_RATIO)
			armor.setRarity(Rarity.EPIC);
		else armor.setRarity(Rarity.LEGENDARY);
		
		int attribute = rand.nextInt(3);
		
		int required = armorAmount * 2 / 5;
		
		switch (attribute) {
			case 0: //Strength
				armor.setRequiredStrength(required);
				break;
			case 1: //Dex
				armor.setRequiredDexterity(required);
			case 2: //Intel
				armor.setRequiredIntelligence(required);
		}
		
		armor.setValue((int)(armor.getArmor() * VALUE_PER_ARMOR_POINT * (1 + .5f*armor.getRarity().ordinal())));
		
		for (int i = 0; i < armor.getRarity().ordinal(); i++)
			addRandomBonus(armor);
		
//		System.out.println(paths[armorType]+0+".png");
		if (armor.getRarity() == Rarity.LEGENDARY)
		{
			armor.setName(armorNames[armorType][0]);
			armor.setImage(armorPaths[armorType]+0+".png");
		}
		else {
			armor.setImage(armorPaths[armorType]+(armorGrade)+".png");
		}
		return armor;
	}
	
	
	// Type 1 is spears, Type 0 is sword, add others as needed
	public static Weapon generateWeapon(int value, int level)
	{
		Random rand = new Random();
		int choice = rand.nextInt(WeaponType.values().length);
		Weapon weapon;
		
		int type = level/3 -1 + rand.nextInt(3);
		if (type < 1) type = 1;

		switch(choice)
		{
			case 0: 
				boolean isTwoHanded = false;
				if (type == 3 || type == 8 || type == 10) {
					isTwoHanded = true;
				}
				weapon = new Weapon(isTwoHanded, AttackType.STRENGTH);
				if (type >= AMOUNT_OF_SWORDS) type = AMOUNT_OF_SWORDS - 1;
				break;
			case 1: weapon = new Weapon(true, AttackType.DEXTERITY);
				if (type>=AMOUNT_OF_SPEARS) type = AMOUNT_OF_SPEARS-1;
				break;
			case 2: 
				weapon = new Weapon(true, AttackType.STRENGTH);
				type = type >= AMOUNT_OF_AXES ? AMOUNT_OF_AXES - 1 : type;
				break;
			case 3: 
				weapon = new Weapon(false, AttackType.STRENGTH);
				type = type >= AMOUNT_OF_SHIELDS ? AMOUNT_OF_SHIELDS - 1 : type;
				break;
			case 4:
				weapon = new Weapon(true, AttackType.DEXTERITY);
				type = type >= AMOUNT_OF_BOWS ? AMOUNT_OF_BOWS - 1 : type;
				break;
			case 5:
				weapon = new Weapon(false, AttackType.INTELLIGENCE);
				type = type >= AMOUNT_OF_MAGIC ? AMOUNT_OF_MAGIC - 1 :type;
				break;
			default: weapon = null;
		}
		
		//SETTING ATTACK
		weapon.setName(names[choice][type]);
		int attack = getAttackFromLevel(level);
		if (choice==3) // if shield?
		{
			weapon.setArmor(attack/2);
			attack/=10;
		}
		weapon.setAttackMin((int)(attack*.8f+rand.nextDouble()*.2f));
		weapon.setAttackMax((int)(attack*1.2f-rand.nextDouble()*.2f));
		
		// SETTING REQUIREMENTS
		int required = attack *2/5;
		switch(weapon.attackType)
		{
		case STRENGTH:	weapon.setRequiredStrength(required);
			break;
		case DEXTERITY: weapon.setRequiredDexterity(required);
			break;
		case INTELLIGENCE: weapon.setRequiredIntelligence(required);
			break;
		}
		
		int rarity = rand.nextInt(NORMAL_RATIO+RARE_RATIO+EPIC_RATIO+LEGENDARY_RATIO);
		if (rarity < NORMAL_RATIO)
			weapon.setRarity(Rarity.NORMAL);
		else if (rarity < NORMAL_RATIO + RARE_RATIO)
			weapon.setRarity(Rarity.RARE);
		else if (rarity < NORMAL_RATIO + RARE_RATIO + EPIC_RATIO)
			weapon.setRarity(Rarity.EPIC);
		else weapon.setRarity(Rarity.LEGENDARY);
		
		weapon.setValue((int)(weapon.getAttack() * VALUE_PER_ATTACK_POINT * (1 + .5f*weapon.getRarity().ordinal())));
		
		for (int i = 0; i < weapon.getRarity().ordinal(); i++)
			addRandomBonus(weapon);
		
		if (weapon.getRarity() == Rarity.LEGENDARY)
		{
			weapon.setName(names[choice][0]);
			weapon.setImage(paths[choice]+0+".png");
		}
		else 
			weapon.setImage(paths[choice]+(type)+".png");
		
		return weapon;
	}
	
	public static Armor generateRing(int value, int level) {
		
		Random rand = new Random();
		int choice = -1;
		Armor ring = new Armor(ArmorType.RING);
	
		int rarity = rand.nextInt(NORMAL_RATIO+RARE_RATIO+EPIC_RATIO+LEGENDARY_RATIO);
		if (rarity < NORMAL_RATIO)
			ring.setRarity(Rarity.NORMAL);
		else if (rarity < NORMAL_RATIO + RARE_RATIO)
			ring.setRarity(Rarity.RARE);
		else if (rarity < NORMAL_RATIO + RARE_RATIO+ EPIC_RATIO)
			ring.setRarity(Rarity.EPIC);
		else ring.setRarity(Rarity.LEGENDARY);
		
		 
		switch(ring.getRarity()) {
		case EPIC: choice = rand.nextInt(2) +8;
			break;
		case LEGENDARY: choice = rand.nextInt(2);
			break;
		case NORMAL: choice = rand.nextInt(5) +2;
			break;
		case RARE: choice = rand.nextInt(3) +6;
			break;
		
		}
		
		ring.setName(ringNames[choice]);
		ring.setImage(armorPaths[3]+choice+".png");

		if(ring.getRarity()==Rarity.NORMAL) {

			int plus = 	rand.nextInt(6) +1;
			int plus1 = 	rand.nextInt(6) +1;
			int plus2 = 	rand.nextInt(6) +1;
			
				ring.setDexterity(plus);
				ring.setStrength(plus2);	
				ring.setIntelligence(plus1);
			}
			
			if(ring.getRarity()==Rarity.RARE) {
				int plus = 	rand.nextInt(11) +5;
				int plus1 = rand.nextInt(11) +5;
				int plus2 = rand.nextInt(11) +5;
				int plus3 = rand.nextInt(11) +5;
 
					ring.setIntelligence(plus2);
					ring.setDexterity(plus3);
					ring.setArmor(plus1);
					ring.setStrength(plus);	
	
		}
			
			if(ring.getRarity()==Rarity.EPIC) {
				int plus = rand.nextInt(16) +5;
				int plus1 = rand.nextInt(16) +5;
				int plus2 = rand.nextInt(16) +5;
				int plus3 = rand.nextInt(16) +5;
				int plus4 = rand.nextInt(16) +5;
				int plus5 = rand.nextInt(16) +5;

					ring.setIntelligence(plus);
					ring.setAttack(plus1);
					ring.setDexterity(plus2);
					ring.setMaxHealth(plus);
					ring.setStrength(plus1);
					ring.setArmor(plus2);
			}
			
			if(ring.getRarity()==Rarity.LEGENDARY) {
				int plus = rand.nextInt(21) +10;
				int plus1 = rand.nextInt(21) +10;
				int plus2 = rand.nextInt(21) +10;
				int plus3 = rand.nextInt(21) +10;
				int plus4 = rand.nextInt(21) +10;
				int plus5 = rand.nextInt(21) +10;

					ring.setIntelligence(plus);
					ring.setAttack(plus1);
					ring.setDexterity(plus2);
					ring.setMaxHealth(plus3);
					ring.setStrength(plus4);
					ring.setArmor(plus5);
			}
			
			ring.setValue((ring.getIntelligence() + ring.getDexterity() + ring.getStrength()) *25);

		return ring;
	}	
	
	
	private static void addRandomBonus(Equipment item) 
	{
		int choice = 1337;
		do 
		{
			choice = new Random().nextInt(6);
			if (choice==0 && item.getMaxHealth() != 0) choice = 1337;
			else if (choice==1 && item.getStrength() != 0) choice = 1337;
			else if (choice==2 && item.getDexterity() != 0) choice = 1337;
			else if (choice==3 && item.getIntelligence() != 0) choice = 1337;
			else if (choice==4 && item.getArmor() != 0) choice = 1337;
		}
		while (choice == 1337);
		
		switch (choice)
		{
			case 0: item.setMaxHealth(item.getValue()/5);
			item.setName("Organic " + item.getName()); break;
			case 1: item.setStrength(item.getValue()/5);
			item.setName("Powerful " + item.getName()); break;
			case 2: item.setDexterity(item.getValue()/5);
			item.setName("Speedy " + item.getName()); break;
			case 3: item.setIntelligence(item.getValue()/5);
			item.setName("Educational " + item.getName()); break;
			case 4: item.setArmor(item.getValue()/5);
			item.setName("Protective " + item.getName()); break;
			case 5: item.setAttack(item.getValue()/5);
			item.setName("Sharpened " + item.getName()); break;
		}
	}

	
	public static Consumable generateConsumable(int value, int level) 
	{
		if (new Random().nextFloat() < POTION_TO_DAMAGE_RATIO)
			return generatePotion(value, level);
		else 
			return generateDamage(value, level);
	}

	private static Consumable generateDamage(int value, int level)
	{
		Random rand = new Random();
		Consumable item;
		
		int type = level/8;
		if (type < 0) type = 0;
		if (type > names[1].length-1) type = names[1].length-1;
		
		item = new Consumable();
		item.setName(consumableNames[1][type]);
		item.setEffectOnHealth(-1 * getHealthForPotions(level));
		
		item.setValue((int)(item.getEffectOnHealth() * VALUE_PER_HEALTH_POINT ));
		
		item.setImage(consumablePaths[1]+type+".png");
		
		return item;
	}

	public static Consumable generatePotion(int value, int level)
	{
		Random rand = new Random();
		Consumable item;
		
		int type = level/8 + 1;
		if (type < 1) type = 1;
		if (type > names[0].length) type = names[0].length;
		if (rand.nextFloat() < REVIVE_POTION_CHANCE) type = 0;
		else type--;
		
		if (type == 0) item = new RevivePotion();
		else item = new Consumable();

		item.setName(consumableNames[0][type]);
		item.setEffectOnHealth(getHealthForPotions(level));
		
		item.setValue((int)(item.getEffectOnHealth() * VALUE_PER_HEALTH_POINT ));
		
		item.setImage(consumablePaths[0]+type+".png");
		
		return item;
	}

	private static int getHealthForPotions(int type) 
	{
		int result = 5 * (int)Math.pow(2, ((float)type)/5);
		result = (int) (result * .8f + new Random().nextFloat() * result * .4f);
		return result;
	}

	private static int getAttackFromLevel(int type)
	{
		return 10 * (int)Math.pow(2, ((float)type)/5);
	}
	
	private static int getArmorFromLevel(int level) 
	{
		int armor = (int)Math.pow(2, ((double) level) / 5) * 2;
		armor = (int)(armor * .8f + armor * .4f * new Random().nextFloat());
		return armor;
	}
}
