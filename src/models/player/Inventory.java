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
		//TODO
	}
	
	public void removeEquipment(Equipment equipment) {
		//TODO
	}
	
	public ArrayList<Consumable> getConsumables() {
		return consumables;
	}
	
	public void addConsumeable(Consumable consumable) {
		//TODO
	}
	
	public void removeConsumeable(Consumable consumable) {
		//TODO
	}
	
	public ArrayList<Loot> getLoot() {
		return loot;
		//TODO
	}
	
	public void addLoot(Loot loot) {
		//TODO
	}
	
	public void removeLoot(Loot loot) 
	{
		//TODO
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
