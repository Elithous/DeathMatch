package models.player;

import java.io.Serializable;

import character.models.Hero;

public class PlayerSave implements Serializable{

	private Hero[] players;
	private Inventory invintory;
	
	public Hero[] getPlayers() {
		
	}
	
	public void removePlayer(Hero hero) {
		
	}
	
	public void addPlayer(Hero hero) {
		
	}
	
	public Inventory getInventory() {
		return invintory;
	}
}
