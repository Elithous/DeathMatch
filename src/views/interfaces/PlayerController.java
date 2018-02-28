package views.interfaces;

import models.player.PlayerSave;
import models.quests.Quest;

public interface PlayerController{

	public void init(PlayerSave playerSave, Quest quest);
	public void update();
}
