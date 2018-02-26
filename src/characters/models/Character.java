package characters.models;

import character.enums.EquipmentSlot;
import interfaces.IHasStats;
import javafx.scene.image.Image;
import loot.enums.ArmorType;
import loot.models.Armor;
import loot.models.Equipment;
import loot.models.Weapon;

public abstract class Character implements IHasStats
{
	protected String name;
	protected int currentHealth;
	protected int maxHealth;
	protected int strength;
	protected int dexterity;
	protected int intelligence;
	protected int armor;
	protected int level;
	protected Equipment[] equipment = new Equipment[7];
	protected Image image;

	@Override
	public int getMaxHealth() 
	{
		int result = maxHealth;
		
		for(Equipment e : equipment)
			result += e.getMaxHealth();
		
		return result;
	}

	@Override
	public int getStrength() 
	{
		int result = strength;
		
		for(Equipment e : equipment)
			result += e.getStrength();
		
		return result;
	}

	@Override
	public int getDexterity() 
	{
		int result = dexterity;
		
		for(Equipment e : equipment)
			result += e.getDexterity();
		
		return result;
	}

	@Override
	public int getIntelligence() 
	{
		int result = intelligence;
		
		for(Equipment e : equipment)
			result += e.getIntelligence();
		
		return result;
	}

	@Override
	public int getArmor()
	{
		int result = armor;
		
		for(Equipment e : equipment)
			result += e.getArmor();
		
		return result;
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
}
