package controllers;

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	
		@Override
		public void start(Stage stage) throws IOException 
		{
			Parent root = loadFXML(ScreenType.PARTY);
			stage.setScene(new Scene(root));
			stage.setFullScreen(true);
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
		
		public void save(PlayerSave playerSave)
		{
			FileUtil.serializeToFile("DeathmatchSave.sav", playerSave);
		}
		
		public PlayerSave load()
		{
			PlayerSave ps = (PlayerSave) FileUtil.deserializeFromFile("DeathmatchSave.sav");
			return ps;
		}
		
		public static void main(String[] args) 
		{
			launch(args);
		}
		
		public static Parent loadFXML(ScreenType type) throws IOException
		{
			FXMLLoader loader = new FXMLLoader(GameApp.class.getResource(screenPaths.get(type)));
			Parent root = loader.load();			
			return root;
		}
		
		public static Parent loadFXML(ScreenType type, PlayerSave ps) throws IOException
		{
			FXMLLoader loader = new FXMLLoader(GameApp.class.getResource(screenPaths.get(type)));
			Parent root = loader.load();			
			((PlayerController)loader.getController()).init(ps, null);	
			return root;
		}
		
		public static Parent loadFXML(ScreenType type, PlayerSave ps, Quest quest) throws IOException
		{
			FXMLLoader loader = new FXMLLoader(GameApp.class.getResource(screenPaths.get(type)));
			Parent root = loader.load();			
			((PlayerController)loader.getController()).init(ps, quest);	
			return root;
		}

		@Override
		public void reactToNotification(Event arg0)
		{
			// TODO Auto-generated method stub
		}
}
