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
import javafx.animation.AnimationTimer;
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
	private boolean isDead = false;
	
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
//		takeTurn();
	}
	
	public Queue<characters.models.Character> getOrder()
	{
		return characterOrder;
	}
	
	public boolean isWaitingForInput()
	{
		return isWaitingForInput;
	}
	
	public void takeTurn()
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
			isDead = true;
			view.switchScreen(e);
		}
		if (monstersAlive==0) 
		{
			ChangeScreenEvent e = new ChangeScreenEvent(ScreenType.WIN, quest, playerSave);
			isDead = true;
			view.switchScreen(e);
		}
		
		while(characterOrder.peek().getCurrentHealth() <= 0)
		{
			characterOrder.add(characterOrder.poll());
			view.update();
		}
		
		characterOrder.peek().updateAilments();
		if (characterOrder.peek() instanceof Monster)
		{
			applyTurn(((Monster) characterOrder.peek()).decideNextTurn(playerSave.getPlayers()));
		}
		else
		{
			isWaitingForInput = true;
		}
	}
	
	private void applyTurn(TurnEvent te)
	{
		switch (te.choice)
		{
		case ATTACK:	int damage = -characterOrder.peek().getAttack();
						te.character.adjustHealth(damage);
						view.addLog(characterOrder.peek().getName()+" attacked "+te.character.getName()+" for "+(damage*-1) + " damage!");
			break;
		case DEFEND: 	view.addLog(characterOrder.peek().getName()+" defends himself!");
						te.character.addAilment(new Ailment(AilmentType.ARM, 1.5f, 1));
			break;
		case USE_ITEM:	view.addLog(te.item.use(te.character, characterOrder.peek()));
						playerSave.getInventory().removeConsumeable(te.item);
						break;
		case SKIP:		view.addLog(characterOrder.peek().getName()+" has skipped his turn!");
						break;
		}
		
		characterOrder.add(characterOrder.poll());
		view.update();
		
		/*AnimationTimer timer = new AnimationTimer()
		{
			@Override
			public void handle(long now)
			{
				
			}
		}.start();*/
		
		if (!isDead) takeTurn();
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
