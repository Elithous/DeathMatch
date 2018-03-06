package views.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.GameApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.player.PlayerSave;
import models.quests.Quest;
import views.interfaces.PlayerController;

public class LossController implements PlayerController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void mainMenuButtonClicked(ActionEvent event)
    {

    }

    @FXML
    void initialize() 
    {
    	
    }

	@Override
	public void init(PlayerSave playerSave, Quest quest, GameApp app) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
}
