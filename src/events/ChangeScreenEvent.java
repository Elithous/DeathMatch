package events;

import lib.Event;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;

public class ChangeScreenEvent extends Event
{
	public final ScreenType screenType;
	public final Quest quest;
	public final PlayerSave playerSave;
	
	public ChangeScreenEvent(ScreenType screenType, Quest quest, PlayerSave playerSave) 
	{
		this.screenType = screenType;
		this.quest = quest;
		this.playerSave = playerSave;
	}
	
}
