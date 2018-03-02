package controllers;

import java.util.Random;

import characters.enums.EquipmentSlot;
import characters.models.Hero;
import javafx.scene.image.Image;
import loot.enums.AttackType;
import loot.models.Weapon;

public class HeroGenerator
{
	public static float MAIN_MULTI = 1.3f;
	public static float OTHER_MULTI = 1.1f;
	public static float AVERAGE_MULTI = 1.2f;
	public static float SUPERSTAT_CHANCE = .2f;
	public static int HERO_AMOUNT;
	
	public static String[] names = new String[] {"Bob", "Tias", "Guppy", "Asssasin", "Elithous", "Mike", "Aniki", "Jon", "Tyrion", "Bilbo", "Aragorn"};
	public static String path = "file:Assets/Heros/HeroComplete/Hero";
	
	public static Hero generateHero(int level)
	{
		Hero hired = new Hero();
		Random rand = new Random();
		hired.setName(names[rand.nextInt(names.length)]);
		AttackType mainStat = AttackType.values()[rand.nextInt(AttackType.values().length)];
		
		hired.setStrength((int)(10+rand.nextInt(10*((rand.nextFloat() < SUPERSTAT_CHANCE)? 2 : 1)) * (mainStat==AttackType.STRENGTH? 1.5f : 1) ));
		hired.setDexterity((int)(10+rand.nextInt(10*((rand.nextFloat() < SUPERSTAT_CHANCE)? 2 : 1)) * (mainStat==AttackType.DEXTERITY? 1.5f : 1) ));
		hired.setIntelligence((int)(10+rand.nextInt(10*((rand.nextFloat() < SUPERSTAT_CHANCE)? 2 : 1)) * (mainStat==AttackType.INTELLIGENCE? 1.5f : 1) ));
		
		while(hired.getLevel() < level)
		{
			hired.addExp(hired.getRequiredExp());
		}
		
		hired.setImage(new Image(path+rand.nextInt(HERO_AMOUNT) +".png"));
		
		return hired;
	}
	
	public static void levelUpHero(Hero hero)
	{
		if (hero.getEquipment(EquipmentSlot.MAIN_HAND) == null)
		{
			hero.setStrength((int)(hero.getStrength() * AVERAGE_MULTI));
			hero.setDexterity((int)(hero.getDexterity() * AVERAGE_MULTI));
			hero.setIntelligence((int)(hero.getDexterity() * AVERAGE_MULTI));
		}
		else
		{
			switch(((Weapon)hero.getEquipment(EquipmentSlot.MAIN_HAND)).attackType)
			{
			case STRENGTH:	hero.setStrength((int)(hero.getStrength() * MAIN_MULTI));
							hero.setDexterity((int)(hero.getDexterity() * OTHER_MULTI));
							hero.setIntelligence((int)(hero.getDexterity() * OTHER_MULTI));
				break;
			case DEXTERITY: hero.setStrength((int)(hero.getStrength() * OTHER_MULTI));
							hero.setDexterity((int)(hero.getDexterity() * MAIN_MULTI));
							hero.setIntelligence((int)(hero.getDexterity() * OTHER_MULTI));
				break;
			case INTELLIGENCE:	hero.setStrength((int)(hero.getStrength() * OTHER_MULTI));
								hero.setDexterity((int)(hero.getDexterity() * OTHER_MULTI));
								hero.setIntelligence((int)(hero.getDexterity() * MAIN_MULTI));
				break;
			}
		}
	}
}
