package views.models;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import models.quests.Quest;

public class QuestListItem extends Parent{

	private Quest quest;
	private ImageView questIcon;
	private Button playButton;
	private Label questName;
	
	public QuestListItem(Quest quest) {
		this.setQuest(quest);
	}

	public Quest getQuest() {
		return quest;
	}

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	public ImageView getQuestIcon() {
		return questIcon;
	}

	public void setQuestIcon(ImageView questIcon) {
		this.questIcon = questIcon;
	}

	public Button getPlayButton() {
		return playButton;
	}

	public void setPlayButton(Button playButton) {
		this.playButton = playButton;
	}

	public Label getQuestName() {
		return questName;
	}

	public void setQuestName(Label questName) {
		this.questName = questName;
	}
}
