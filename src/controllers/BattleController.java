package controllers;

import java.util.ArrayList;
import java.util.Queue;

import characters.models.Monster;
import characters.models.TurnChoice;
import events.TurnEvent;
import lib.Event;
import lib.IEventListener;
import models.player.PlayerSave;
import models.quests.Quest;

public class BattleController implements IEventListener
{
	PlayerSave playerSave;
	Quest quest;
	Queue<characters.models.Character> characterOrder;
	private boolean isWaitingForInput = false;
	
	public BattleController(PlayerSave playerSave, Quest quest) 
	{
		this.playerSave = playerSave;
		this.quest = quest;
		
		ArrayList<Character> all = new ArrayList<>();
		//all.addAll(c) (playerSave.getPlayers());
		//TODO create a queue!
		
		takeTurn();
	}
	
	public boolean isWaitingForInput()
	{
		return isWaitingForInput;
	}
	
	private void takeTurn()
	{
		int deadInRow = 0;
		while(characterOrder.peek().getCurrentHealth() <= 0)
		{
			deadInRow++;
			characterOrder.add(characterOrder.poll());
			if (deadInRow > characterOrder.size()) throw new IllegalArgumentException("All characters are dead, wtf??");
		}
		
		if (characterOrder.peek() instanceof Monster)
			applyTurn(((Monster) characterOrder.peek()).decideNextTurn(playerSave.getPlayers()));
		else
			isWaitingForInput = true;
	}
	
	private void applyTurn(TurnChoice turnChoice)
	{
		//TODO actaully apply turn
		
		characterOrder.add(characterOrder.poll());
	}

	@Override
	public void reactToNotification(Event e) 
	{
		if (e instanceof TurnEvent) 
		{
			applyTurn(new TurnChoice( ( (TurnEvent) e).character, ((TurnEvent) e).choice));
		}
	}
	
}
