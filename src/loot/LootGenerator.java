package loot;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import loot.enums.AttackType;
import loot.enums.Rarity;
import loot.enums.WeaponType;
import loot.models.Armor;
import loot.models.Consumable;
import loot.models.Equipment;
import loot.models.Loot;
import loot.models.Weapon;
import models.quests.Quest;

public class LootGenerator 
{
	private static String[][] names = new String[][]
			{
				{},
				{"Lance of Cassius", "Wooden Spear", "Ugandan Spear", "Metal Spear", "Naginata", "Halberd", "Elite Guard Lance", "Darksteel Spear", "Holy Lance"}
			};
	private static String[] paths = new String[] {"","../../Assets/Weapons/Spear"};
	
	private static final int WEAPON_RATIO = 10;
	private static final int ARMOR_RATIO = 10;
	private static final int RING_RATIO = 5;
	private static final int CONSUMABLES_RATIO = 15;
	
	private static final int VALUE_PER_DIFFICULTY = 10;
	
	private static final int NORMAL_RATIO = 50;
	private static final int RARE_RATIO = 30;
	private static final int EPIC_RATIO = 10;
	private static final int LEGENDARY_RATIO = 1;
	
	private static final int AMOUNT_OF_SPEARS = 9;
	
	private static final int VALUE_PER_ATTACK_POINT = 10;	

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
		//TODO
		return null;
	}
	
	
	// Type 1 is spears, Type 0 is sword, add others as needed
	public static Weapon generateWeapon(int value, int level)
	{
		Random rand = new Random();
		int choice = rand.nextInt(WeaponType.values().length);
		Weapon weapon;
		
		int type = level/3 -1 + rand.nextInt(3);
		if (type < 0) type = 0;

		switch(choice)
		{
			// Add others
			case 1: weapon = new Weapon(true, AttackType.DEXTERITY);
					if (type>=AMOUNT_OF_SPEARS) type = AMOUNT_OF_SPEARS-1;
				break;
			default: weapon = null;
		}
		
		weapon.setName(names[choice][type+1]);
		int attack = getAttackFromType(type);
		weapon.setAttackMin((int)(attack*.8f+rand.nextDouble()*.2f));
		weapon.setAttackMin((int)(attack*1.2f-rand.nextDouble()*.2f));
		
		int rarity = rand.nextInt(NORMAL_RATIO+RARE_RATIO+EPIC_RATIO+LEGENDARY_RATIO);
		if (choice < NORMAL_RATIO)
			weapon.setRarity(Rarity.NORMAL);
		else if (choice < NORMAL_RATIO + RARE_RATIO)
			weapon.setRarity(Rarity.RARE);
		else if (choice < NORMAL_RATIO + RARE_RATIO)
			weapon.setRarity(Rarity.EPIC);
		else weapon.setRarity(Rarity.LEGENDARY);
		
		weapon.setValue((int)(weapon.getAttack() * VALUE_PER_ATTACK_POINT * (1 + .5f*weapon.getRarity().ordinal())));
		
		for (int i = 0; i < weapon.getRarity().ordinal(); i++)
			addRandomBonus(weapon);
		
		if (weapon.getRarity() == Rarity.LEGENDARY)
		{
			weapon.setName(names[choice][0]);
			weapon.setImage(new Image(paths[choice]+0+".png"));
		}
		else 
			weapon.setImage(new Image(paths[choice]+(type+1)+".png"));
		
		return weapon;
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
			case 5: item.setDexterity(item.getValue()/5);
			item.setName("Sharpened " + item.getName()); break;
		}
	}

	public static Weapon generateRing(int value, int level) {
		//TODO
		return null;
	}
	
	public static Consumable generateConsumable(int value, int level) {
		//TODO
		return null;
	}
	
	private static int getAttackFromType(int type)
	{
		return 10 * (int)Math.pow(2, ((float)type)/5);
	}
}
