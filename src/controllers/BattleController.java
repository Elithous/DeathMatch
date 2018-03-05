package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import characters.enums.AilmentType;
import characters.models.Ailment;
import characters.models.Hero;
import characters.models.Monster;
import events.ChangeScreenEvent;
import events.TurnEvent;
import lib.Event;
import lib.IEventListener;
import models.player.PlayerSave;
import models.quests.Quest;
import views.controllers.BattleScreenController;
import views.enums.ScreenType;

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
		int monstersAlive = 0;
		int heroesAlive = 0;
		for (characters.models.Character c : (characters.models.Character[]) characterOrder.toArray(new characters.models.Character[characterOrder.size()]))
		{
			if (c instanceof Monster && c.getCurrentHealth()>0)
				monstersAlive++;
			else if (c instanceof Hero && c.getCurrentHealth()>0)
				heroesAlive++;
		}
		if (heroesAlive==0) 
		{
			ChangeScreenEvent e = new ChangeScreenEvent(ScreenType.LOSE, quest, playerSave);
			view.switchScreen(e);
		}
		if (monstersAlive==0) 
		{
			ChangeScreenEvent e = new ChangeScreenEvent(ScreenType.WIN, quest, playerSave);
			view.switchScreen(e);
		}
		
		while(characterOrder.peek().getCurrentHealth() <= 0)
		{
			characterOrder.add(characterOrder.poll());
		}
		
		characterOrder.peek().updateAilments();
		if (characterOrder.peek() instanceof Monster)
		{
			System.out.println("monster!");
			applyTurn(((Monster) characterOrder.peek()).decideNextTurn(playerSave.getPlayers()));
		}
		else
		{
			isWaitingForInput = true;
			System.out.println("plauer waiting for input");
		}
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
		System.out.println("applied turn");
		takeTurn();
	}

	@Override
	public void reactToNotification(Event e) 
	{
		if (e instanceof TurnEvent) 
		{
			isWaitingForInput = false;
			applyTurn((TurnEvent) e);
		}
	}
	
}
