package controllers;

import java.io.IOException;
import java.util.HashMap;

import events.ChangeScreenEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import lib.Event;
import lib.FileUtil;
import lib.IEventListener;
import models.player.PlayerSave;
import models.quests.Quest;
import views.enums.ScreenType;
import views.interfaces.PlayerController;

public class GameApp extends Application implements IEventListener
{
		public static HashMap<ScreenType, String> screenPaths;
		
		public Stage stage;
		private Scene scene;
		
		@Override
		public void start(Stage stage) throws IOException 
		{

			this.stage = stage;
			Parent root = loadFXML(ScreenType.MAIN, null, null);
			scene = new Scene(root);
			stage.setScene(scene);
			stage.setFullScreen(true);
			stage.setFullScreenExitHint("");
			stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
			stage.show();

		}
		
		@Override
		public void init()
		{
			screenPaths = new HashMap<>();
			screenPaths.put(ScreenType.BATTLE, "battle.fxml");
			screenPaths.put(ScreenType.LOSE, "loss.fxml");
			screenPaths.put(ScreenType.MAIN, "main.fxml");
			screenPaths.put(ScreenType.MANAGEMENT, "management.fxml");
			screenPaths.put(ScreenType.PARTY, "party.fxml");
			screenPaths.put(ScreenType.QUEST, "quest.fxml");
			screenPaths.put(ScreenType.WIN, "win.fxml");
		}
		
		public static void save(PlayerSave playerSave)
		{
			FileUtil.serializeToFile("DeathmatchSave.sav", playerSave);
		}
		
		public static PlayerSave load()
		{
			PlayerSave ps = (PlayerSave) FileUtil.deserializeFromFile("DeathmatchSave.sav");
			return ps;
		}
		
		public static void main(String[] args) 
		{
			launch(args);
		}
		
		public Parent loadFXML(ScreenType type, PlayerSave ps, Quest quest) throws IOException
		{
			FXMLLoader loader = new FXMLLoader(GameApp.class.getResource(screenPaths.get(type)));
			Parent root = loader.load();
			((PlayerController)loader.getController()).init(ps, quest, this);
			return root;
		}

		@Override
		public void reactToNotification(Event arg0)
		{
			if(arg0 instanceof ChangeScreenEvent)
			{
				ChangeScreenEvent screenEvent = (ChangeScreenEvent) arg0;
				try {
					Parent node = loadFXML(screenEvent.screenType, screenEvent.playerSave, screenEvent.quest);
					scene.setRoot(node);
					stage.setFullScreen(true);
				} catch (IOException e) {
					
				}
			}
		}
}
