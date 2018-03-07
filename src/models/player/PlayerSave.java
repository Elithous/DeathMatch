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
	
	public boolean removePlayer(Hero heroN) 
	{
		for (int i = 0; i < players.length; i++) 
		{
			if (players[i] == heroN) 
			{
				players[i] = null;
				
				if (i < players.length)	
				for(int j = i+1; j < players.length; j++)
					players[i] = players[j];
					
				
				
				return true;
			}
		}
		return false;
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
