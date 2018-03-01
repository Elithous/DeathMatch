package characters.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import characters.enums.AilmentType;
import characters.enums.EquipmentSlot;
import interfaces.IHasStats;
import javafx.scene.image.Image;
import loot.enums.ArmorType;
import loot.models.Armor;
import loot.models.Equipment;
import loot.models.Weapon;

public class Character implements IHasStats, Serializable, Comparable<Character>
{
	protected String name;
	protected int currentHealth;
	protected int maxHealth;
	protected int strength;
	protected int dexterity;
	protected int intelligence;
	protected int armor;
	protected int attack;
	protected float strengthMulti = 1f;
	protected float dexterityMulti = 1f;
	protected float intelligenceMulti = 1f;
	protected float armorMulti = 1f;
	protected float attackMulti = 1f;
	protected float level;
	protected Equipment[] equipment = new Equipment[7];
	protected Image image;
	private ArrayList<Ailment> ailments = new ArrayList<>();

	@Override
	public int getMaxHealth() 
	{
		int result = maxHealth;
		
		for(Equipment e : equipment)
			result += e.getMaxHealth();
		
		return result;
	}
	
	public void addAilment(Ailment a)
	{
		ailments.add(a);
		adjustMulti(a.type, a.multi);
	}
	
	public void updateAilments()
	{
		for (int i = 0; i < ailments.size(); i++) 
		{
			ailments.get(i).decreaseTurns();
			if (ailments.get(i).getTurnsLeft()<=0)
			{
				adjustMulti(ailments.get(i).type, 1f/ailments.get(i).multi);
				ailments.remove(i);
				i--;
			}
		}
	}
	
	private void adjustMulti(AilmentType type, float multi)
	{
		switch(type)
		{
		case STR:	strengthMulti *= multi;
			break;
		case ARM:	armorMulti *= multi;
			break;
		case ATK:	attackMulti *= multi;
			break;
		case DEX:	dexterityMulti *= multi;
			break;
		case INT:	intelligenceMulti *= multi;
			break;
		}
	}

	@Override
	public int getStrength() 
	{
		int result = strength;
		
		for(Equipment e : equipment)
			result += e.getStrength();
		
		return (int)(result * strengthMulti);
	}

	@Override
	public int getDexterity() 
	{
		int result = dexterity;
		
		for(Equipment e : equipment)
			result += e.getDexterity();
		
		return (int)(result * dexterityMulti);
	}

	@Override
	public int getIntelligence() 
	{
		int result = intelligence;
		
		for(Equipment e : equipment)
			result += e.getIntelligence();
		
		return (int)(result * intelligenceMulti);
	}

	@Override
	public int getArmor()
	{
		int result = armor;
		
		for(Equipment e : equipment)
			result += e.getArmor();
		
		return (int)(result * armorMulti);
	}

	@Override
	public void setMaxHealth(int hp) 
	{
		maxHealth = hp;
	}

	@Override
	public void setStrength(int str) 
	{
		strength = str;
	}

	@Override
	public void setDexterity(int dex)
	{
		dexterity = dex;
	}

	@Override
	public void setIntelligence(int intel)
	{
		intelligence = intel;
	}

	@Override
	public void setArmor(int arm) 
	{
		armor = arm;
	}

	public String getName()
	{
		return name;
	}
	
	public void setName(String nameN)
	{
		if (nameN != null && !nameN.trim().equals(""))
		name = nameN;
	}
	
	public int getCurrentHealth()
	{
		return currentHealth;
	}
	
	public void adjustHealth(int delta)
	{
		currentHealth += delta;
		if (currentHealth > getMaxHealth()) currentHealth = getMaxHealth();
		else if (currentHealth < 0) currentHealth = 0;
	}
	
	public Equipment getEquipment(EquipmentSlot slot)
	{
		return equipment[slot.ordinal()];
	}
	
	public boolean equip(EquipmentSlot slot, Equipment item)
	{
		if (item.getRequiredDexterity() > getDexterity()) return false;
		if (item.getRequiredStrength() > getStrength()) return false;
		if (item.getRequiredIntelligence() > getIntelligence()) return false;
		
		if (item instanceof Weapon)
		{
			if (slot == EquipmentSlot.MAIN_HAND) 
			{
				equipment[slot.ordinal()] = item;
				if (((Weapon)item).isTwoHanded) equipment[EquipmentSlot.OFFHAND.ordinal()] = null;
				return true;
			}
			if (slot == EquipmentSlot.OFFHAND)
			{
				if (((Weapon)item).isTwoHanded) return false;
				else equipment[slot.ordinal()] = item;
				return true;
			}
		}
		else
		{
			Armor armor = ((Armor)item);
			if ((slot == EquipmentSlot.HELM && armor.armorType == ArmorType.HELM) ||
				(slot == EquipmentSlot.BODY && armor.armorType == ArmorType.BODY) ||
				(slot == EquipmentSlot.LEGS && armor.armorType == ArmorType.LEGS) ||
				((slot == EquipmentSlot.RING1 || slot == EquipmentSlot.RING2) && armor.armorType == ArmorType.RING))
			{
				equipment[slot.ordinal()] = armor;
				return true;
			}
		}
		return false;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void setImage(Image imageN)
	{
		if (image != null)
			image = imageN;
	}

	@Override
	public int getAttack() 
	{
		int result = attack;
		
		for(Equipment e : equipment)
			result += e.getAttack();
		
		return (int)(result * attackMulti);
	}

	@Override
	public void setAttack(int attack) 
	{
		this.attack = attack;
	}

	@Override
	public int compareTo(Character o) 
	{
		int myDex = this.getDexterity();
		int otherDex = o.getDexterity();
		
		if (myDex>otherDex) return 1;
		else if (myDex<otherDex) return -1;
		else return (new Random().nextInt(2)==0? 1 : -1);
	}
}
