package server;

import game.Monster;
import game.Game;
import client.Client;
import server.Server;

public class Starter {
	
	public static void main(String[] args) {
		new Thread(Server.newInstance(1500)).start();
		new Thread(Client.newInstance("localhost", 1500)).start();
		new Thread(Game.getInstance()).start();
		
	}
}
