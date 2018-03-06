package models.player;

import java.io.Serializable;
import java.util.ArrayList;

import loot.models.Consumable;
import loot.models.Equipment;
import loot.models.Loot;

public class Inventory implements Serializable{

	private ArrayList<Equipment> equipment = new ArrayList<>();
	private ArrayList<Consumable> consumables = new ArrayList<>();
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
		this.consumables.remove(consumable);
	}
	
	public ArrayList<Loot> getLoot() {
		ArrayList<Loot> loot = new ArrayList<>();
		loot.addAll(equipment);
		loot.addAll(consumables);
		return loot;
	}
	
	public void addLoot(Loot loot) {
		if(loot instanceof Equipment) {
			equipment.add((Equipment) loot);
		} else {
			consumables.add((Consumable) loot);
		}
	}
	
	public void removeLoot(Loot loot) 
	{
		if(loot instanceof Equipment) {
			equipment.remove((Equipment) loot);
		} else {
			consumables.remove((Consumable) loot);
		}
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
