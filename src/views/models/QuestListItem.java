package views.models;

import controllers.GameApp;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import models.quests.Quest;

public class QuestListItem extends HBox {

	private Quest quest;
	private ImageView questIcon = new ImageView();
	private Button playButton = new Button();
	private Label questName = new Label();

	public QuestListItem(Quest quest) 
	{
		this.spacingProperty().bind(this.widthProperty().divide(20));
		this.setAlignment(Pos.CENTER);
		this.setQuest(quest);
		this.questIcon.setImage(quest.getStrongestMonster().getImage() == null ? new Image("file:../../Assets/emptyChar.png") : quest.getStrongestMonster().getImage());
		this.questIcon.fitHeightProperty().bind(this.widthProperty().divide(20));
		this.questName.setText(quest.name + ": Quest level: " + quest.difficulty);
//		this.questName.setText(quest.difficulty + " " + quest.monsters[0].getName());
		this.playButton.setText("Play");
		this.playButton.setBackground(GameApp.buttonBack);
		this.playButton.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		this.getChildren().addAll(questIcon, questName, playButton);
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
