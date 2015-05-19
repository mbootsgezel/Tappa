package server;

import java.awt.KeyboardFocusManager;

import controllers.KeyboardController;
import game.Monster;
import game.Game;
import client.Client;
import server.Server;

public class Starter {
	
	public static void main(String[] args) {
		new Thread(Server.newInstance(1500)).start();
		new Thread(Client.newInstance("localhost", 1500)).start();
		new Thread(Game.getInstance()).start();
		
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(new KeyboardController());
		
	}
}
