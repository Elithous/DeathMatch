package models.player;

import java.io.Serializable;
import java.util.ArrayList;

import loot.models.Consumable;
import loot.models.Equipment;
import loot.models.Loot;

public class Inventory implements Serializable{

	private ArrayList<Equipment> equipment;
	private ArrayList<Consumable> consumables;
	private ArrayList<Loot> loot;
	private int gold;
	
	public ArrayList<Equipment> getEquipment() {
		return equipment;
	}
	
	public void addEquipment(Equipment equipment) {
		this.equipment.add(equipment);
	}
	
	public void removeEquipment(Equipment equipment) {
		this.equipment.remove(equipment);
	}
	
	public ArrayList<Consumable> getConsumables() {
		return consumables;
	}
	
	public void addConsumeable(Consumable consumable) {
		this.consumables.add(consumable);
	}
	
	public void removeConsumeable(Consumable consumable) {
		this.consumables.add(consumable);
	}
	
	public ArrayList<Loot> getLoot() {
		return loot;
	}
	
	public void addLoot(Loot loot) {
		this.loot.add(loot);
	}
	
	public void removeLoot(Loot loot) 
	{
		this.loot.remove(loot);
	}
	
	public int getGold() {
		return gold;
	}
	
	public boolean goldTransaction(int amount) 
	{
		gold += amount;
		if (gold<0) 
		{
			gold -= amount;
			return false;
		}
		else return true;
	}
}
