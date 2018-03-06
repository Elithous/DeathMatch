package characters.models;

import java.io.Serializable;
import java.util.ArrayList;

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
	protected int level = 1;
	protected float strengthMulti = 1f;
	protected float dexterityMulti = 1f;
	protected float intelligenceMulti = 1f;
	protected float armorMulti = 1f;
	protected float attackMulti = 1f;
	protected Equipment[] equipment = new Equipment[7];
	protected transient Image image;
	protected String imageUrl;
	private ArrayList<Ailment> ailments = new ArrayList<>();

	public Character() {
		
	}
	
	public Character(Character clone) {
		this.name = clone.name;
		this.currentHealth = clone.currentHealth;
		this.maxHealth = clone.maxHealth;
		this.strength = clone.strength;
		this.dexterity = clone.dexterity;
		this.intelligence = clone.intelligence;
		this.armor = clone.armor;
		this.attack = clone.attack;
		this.level = clone.level;
		this.strengthMulti = clone.strengthMulti;
		this.dexterityMulti = clone.dexterityMulti;
		this.intelligenceMulti = clone.intelligenceMulti;
		this.armorMulti = clone.armorMulti;
		this.attackMulti = clone.attackMulti;
		this.equipment = clone.equipment.clone();
		this.image = clone.image;
		this.ailments = (ArrayList<Ailment>) clone.ailments.clone();
		this.imageUrl = clone.imageUrl;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void fullHeal()
	{
		currentHealth = getMaxHealth();
	}
	
	@Override
	public int getMaxHealth() 
	{
		int result = maxHealth;
		
		for(Equipment e : equipment)
			if (e!=null)result += e.getMaxHealth();
		
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
			if (e!=null)result += e.getStrength();
		
		return (int)(result * strengthMulti);
	}

	@Override
	public int getDexterity() 
	{
		int result = dexterity;
		
		for(Equipment e : equipment)
			if (e!=null)result += e.getDexterity();
		
		return (int)(result * dexterityMulti);
	}

	@Override
	public int getIntelligence() 
	{
		int result = intelligence;
		
		for(Equipment e : equipment)
			if (e!=null)result += e.getIntelligence();
		
		return (int)(result * intelligenceMulti);
	}

	@Override
	public int getArmor()
	{
		int result = armor;
		
		for(Equipment e : equipment)
			if (e!=null)result += e.getArmor();
		
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
		if (item == null)
		{
			equipment[slot.ordinal()].setEquipped(false);
			equipment[slot.ordinal()] = null;		
			return true;	
		}
		
		if (item.isEquipped()) return false;
		if (item.getRequiredDexterity() > getDexterity()) return false;
		if (item.getRequiredStrength() > getStrength()) return false;
		if (item.getRequiredIntelligence() > getIntelligence()) return false;
		
		if (item instanceof Weapon)
		{
			if (slot == EquipmentSlot.MAIN_HAND) 
			{
				if (equipment[slot.ordinal()]!=null) equipment[slot.ordinal()].setEquipped(false);
				equipment[slot.ordinal()] = item;
				equipment[slot.ordinal()].setEquipped(true);
				if (((Weapon)item).isTwoHanded) 
				{
					if (equipment[EquipmentSlot.OFFHAND.ordinal()]!=null)equipment[EquipmentSlot.OFFHAND.ordinal()].setEquipped(false);
					equipment[EquipmentSlot.OFFHAND.ordinal()] = null;
				}
				return true;
			}
			if (slot == EquipmentSlot.OFFHAND)
			{
				if (((Weapon)item).isTwoHanded || (equipment[EquipmentSlot.MAIN_HAND.ordinal()]!=null && ((Weapon)equipment[EquipmentSlot.MAIN_HAND.ordinal()]).isTwoHanded)) return false;
				else 
				{
					if (equipment[slot.ordinal()]!=null) equipment[slot.ordinal()].setEquipped(false);
					equipment[slot.ordinal()] = item;
					equipment[slot.ordinal()].setEquipped(true);
				}
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
				if (equipment[slot.ordinal()]!=null) equipment[slot.ordinal()].setEquipped(false);
				equipment[slot.ordinal()] = item;
				equipment[slot.ordinal()].setEquipped(true);
				return true;
			}
		}
		return false;
	}
	
	public Image getImage() 
	{
		if (image == null) image = new Image(imageUrl);
		return image;
	}
	
	public void setImage(String url) 
	{
		this.image = new Image(url);
		if (image != null) imageUrl = url;
	}

	@Override
	public int getAttack() 
	{
		int result = attack;
		
		for(Equipment e : equipment)
			if (e!=null)result += e.getAttack();
		
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
		if (myDex > otherDex) return 1;
		else if (myDex < otherDex) return -1;
		else return this.getName().compareTo(o.getName());
	}

	public String loadStats()
	{
		StringBuilder sb = new StringBuilder("Stats:\n");
		sb.append("Level ").append(this.getLevel()).append("\n");
		if (this instanceof Hero)
		{
			sb.append("Exp: ").append(((Hero)this).getCurrentExp()).append("/").append(((Hero)this).getRequiredExp()).append("\n");
		}
		sb.append("HP: ").append(this.getMaxHealth()).append("\n");
		sb.append("Strength: ").append(this.getStrength()).append("\n");
		sb.append("Dexterity: ").append(this.getDexterity()).append("\n");
		sb.append("Intelligence: ").append(this.getIntelligence()).append("\n");
		sb.append("Armor: ").append(this.getArmor()).append("\n");
		sb.append("Attack: ").append(this.getAttack()).append("\n");
		
		return sb.toString();
	}
}
