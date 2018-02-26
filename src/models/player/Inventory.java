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
		
	}
	
	public void removeEquipment(Equipment equipment) {
		
	}
	
	public ArrayList<Consumable> getConsumables() {
		return consumables;
	}
	
	public void addConsumeable(Consumable consumable) {
		
	}
	
	public void removeConsumeable(Consumable consumable) {
		
	}
	
	public ArrayList<Loot> getLoot() {
		
	}
	
	public void addLoot(Loot loot) {
		
	}
	
	public void removeLoot(Loot loot) {
		
	}
	
	public int getGold() {
		return gold;
	}
	
	public void setGold() {
		
	}
}
