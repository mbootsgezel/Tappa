package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import entities.Click;
import entities.Entity;
import entities.MonsterEntity;
import entities.Score;
import entities.User;
import game.Game;
import game.LevelPanel;
import game.Monster;
import model.CurrentDate;

public class Client implements Runnable{
	
	private static Client instance;
	
	private String host;
	private int port;

	private Socket client;
	private ObjectOutputStream toServer;
	private ObjectInputStream fromServer;
	
	private boolean running;
	private boolean newClient;
	
	private CurrentDate d = new CurrentDate();
	
	private User user;
	
	private Client(String host, int port) {
		this.host = host;
		this.port = port;
		this.user = new User();
		
		user.setUsername(System.getProperty("user.name"));
		user.setLevel(1);
		user.setDamage(17);
	}

	@Override
	public void run() {
		running = true;
		newClient = true;
		/*
		 * Try connecting to the server with given ip/host & port
		 */
		try {
			client = new Socket(host, port);
			toServer = new ObjectOutputStream(client.getOutputStream());
			fromServer = new ObjectInputStream(client.getInputStream());
			running = true;
			display("Succesfully connected to: " + host);
		} catch (Exception e){
			displayError("Can't connect to server at " + host);
		}
		
		/*
		 * Try to send startup data to the server
		 */
		try {
			sendUser();
		}catch (Exception e){
			display(e.toString());
		}
		
		/*
		 * Try to run
		 */
		try {
			while(running){
				Entity ent = (Entity) fromServer.readObject();
				
				switch(ent.getType()){
				case Entity.USER:
					display(this.user.toString());
					this.user = (User) ent.getObject();
					break;
				case Entity.CLICK:
					//display(((Click) ent.getObject()).toString());
					LevelPanel.getInstance().damageMonster((Click) ent.getObject());
					break;
				case Entity.MONSTER:
					MonsterEntity m = (MonsterEntity) ent.getObject();
					display("New monster received: " + m.toString());
					Monster monster = new Monster(m.getType(), m.getLevel(), m.getCurrentHealth(), m.getMaxHealth());
					Game.getInstance().initGame(monster);
					break;
				}
			}
		} catch (Exception e){
			displayError("Connection to server has been lost.");
		}
		
	}
	
	/*
	 *       TO SERVER
	 */
	public void sendUser(){
		//display("Sending user to server");
		Entity user = new Entity(Entity.USER, this.user);
		try { 
			toServer.writeObject(user);
		} catch (Exception e){
			displayError("Can't send user to server. Exception caught.");
		}
	}
	
	public void sendClick(int x, int y){
		//display("Sending click to server");
		Entity click = new Entity(Entity.CLICK, new Click(x, y, user.getUserID(), user.getDamage()));
		try { 
			toServer.writeObject(click);
		} catch (Exception e){
			displayError("Can't send click to server. Exception caught.");
		}
	}
	
	public void sendScore(){
		//Entity score = new Entity(Entity.SCORE, new Score(score));
	}
	
	
	/*
	 *       TO GAME
	 */
	public User getUser(){
		return user;
	}
	
	private void display(String s){
		System.out.println(d.now() + " Client - " + s);
	}
	
	private void displayError(String s){
		System.err.println(d.now() + " Client - " + s);
	}
	
	public static Client newInstance(String host, int port) {
		if (instance == null) {
			instance = new Client(host, port);
		}
		return instance;
	}

	public static Client getInstance() {
		return instance;
	}


}
