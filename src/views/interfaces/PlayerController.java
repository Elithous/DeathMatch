package views.interfaces;

import controllers.GameApp;
import models.player.PlayerSave;
import models.quests.Quest;

public interface PlayerController
{
	public void init(PlayerSave playerSave, Quest quest, GameApp app);
	public void update();
}
