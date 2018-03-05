package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import characters.enums.AilmentType;
import characters.models.Ailment;
import characters.models.Monster;
import events.TurnEvent;
import lib.Event;
import lib.IEventListener;
import models.player.PlayerSave;
import models.quests.Quest;
import views.controllers.BattleScreenController;

public class BattleController implements IEventListener
{
	PlayerSave playerSave;
	Quest quest;
	BattleScreenController view;
	Queue<characters.models.Character> characterOrder = new LinkedList<>();
	private boolean isWaitingForInput = false;
	
	public BattleController(PlayerSave playerSave, Quest quest, BattleScreenController view) 
	{
		this.playerSave = playerSave;
		this.quest = quest;
		this.view = view;
		
		ArrayList<characters.models.Character> all = new ArrayList<>();
		all.addAll(Arrays.asList(playerSave.getPlayers()));
		all.addAll(Arrays.asList(quest.monsters));
		
		all.removeAll(Collections.singleton(null));
		Collections.sort(all);
		Collections.reverse(all);
		for(characters.models.Character c : all)
			c.fullHeal();
		characterOrder.addAll(all);
		takeTurn();
	}
	
	public Queue<characters.models.Character> getOrder()
	{
		return characterOrder;
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
		
		characterOrder.peek().updateAilments();
		if (characterOrder.peek() instanceof Monster)
			applyTurn(((Monster) characterOrder.peek()).decideNextTurn(playerSave.getPlayers()));
		else
			isWaitingForInput = true;
	}
	
	private void applyTurn(TurnEvent te)
	{
		switch (te.choice)
		{
		case ATTACK:	System.out.println("before" + te.character.getCurrentHealth());
						System.out.println(characterOrder.peek().getName()+"'s attack is "+(-characterOrder.peek().getAttack()));
						te.character.adjustHealth(-characterOrder.peek().getAttack());
						System.out.println("after" + te.character.getCurrentHealth());
			break;
		case DEFEND: 	te.character.addAilment(new Ailment(AilmentType.ARM, 1.5f, 1));
			break;
		case USE_ITEM:	te.item.use(te.character);
						playerSave.getInventory().removeConsumeable(te.item);
						break;
		}

		characterOrder.add(characterOrder.poll());
		view.update();
	}

	@Override
	public void reactToNotification(Event e) 
	{
		if (e instanceof TurnEvent) 
		{
			applyTurn((TurnEvent) e);
		}
	}
	
}
