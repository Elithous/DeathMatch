package models.player;

import java.io.Serializable;

import characters.models.Hero;

public class PlayerSave implements Serializable{

	private Hero[] players = new Hero[4];
	private Inventory inventory = new Inventory();
	
	public Hero[] getPlayers() 
	{
		return players;
	}
	
	public int getNumOfHeros() {
		int num=0;
		
		for(int i=0;i<4;i++) {
			if(players[i] != null) {
				num++;
			}
		}
		return num;
		
	}
	
	public void removePlayer(Hero heroN) 
	{
		Hero[] h = new Hero[4];
		int count = 0;
	
		for (int i = 0; i < players.length; i++) 
		{
			if(players[i] != null && players[i]!=heroN) {
				h[count] = players[i];
				count++;
			}
		}
			players = h.clone();

	}
	
	public boolean addPlayer(Hero heroN) 
	{
		for (int i = 0; i < players.length; i++) 
		{
			if (players[i] == null) 
			{
				players[i] = heroN;
				return true;
			}
		}
		return false;
	}
	
	public Inventory getInventory()
	{
		return inventory;
	}
}
