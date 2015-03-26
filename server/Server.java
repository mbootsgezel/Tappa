package server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import entities.Click;
import entities.MonsterEntity;
import entities.User;
import game.Monster;
import model.CurrentDate;


public class Server implements Runnable {

	private static Server instance;

	private MonsterEntity currentMonster;

	private boolean running;
	
	private int monsterLevel = 1;

	private int uniqueid;
	private int port;
	private ServerSocket serverSocket;

	private ArrayList <ClientConnection> clients;
	private ArrayList <User> users;

	private CurrentDate d = new CurrentDate();
	
	private Random random = new Random();

	private Server(int port) {
		this.port = port;
		this.clients = new ArrayList <ClientConnection>();
		this.users = new ArrayList <User>();
		this.running = true;
		this.currentMonster = newMonster(Monster.SCHIPPER, 40, monsterLevel);
	}

	@Override
	public void run() {
		/*
		 * Trying to create the server socket for incoming connections.
		 */
		try{
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			displayError("Port is already in use.");
		}

		/*
		 * Trying to loop to continue waiting for clients to connect.
		 */
		try {
			display("Waiting for connections on port " + port + ".");
			
			

			while(running){

				clients.add(new ClientConnection(serverSocket.accept(), ++uniqueid));
				
				if(!running){
					break;
				}
			}
		} catch (IOException e) {
			displayError(e.getMessage());
		}
	}

	/*
	 * Broadcasting a click to all clients except for the sender
	 */
	public void broadcastClick(Click click, User user){
		try {
			updateMonsterHealth(user.getDamage());
			for(int i = 0;i < clients.size();i++){
				if(user.getUserID() != clients.get(i).getConnectionID()){
					clients.get(i).sendClientClick(click);
				}	
			}
		} catch (Exception e){

		}
	}
	
	/*
	 * Broadcasting a new monster to all clients
	 */
	public void broadcastMonster(MonsterEntity monster){
		display("Sending a new monster to all clients");
		try {
			for(int i = 0;i < clients.size();i++){
				clients.get(i).sendClientMonster(monster);;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/*
	 * Adding a user to the userlist
	 */
	public void addUser(User user){
		//display("User added to server.userlist");
		users.add(user);
	}
	
	/*
	 * Generate monster based on level
	 */
	public MonsterEntity newMonster(int type, int health, int level){
		int i = random.nextInt(level*50);
		int newHealth = health + ((health * (level * level)) + i);
		this.currentMonster = new MonsterEntity(type, level, newHealth, newHealth);
		
		return this.currentMonster;
	}
	
	/*
	 * When a player damages the monster, we update the serverMonsterHealth
	 */
	public void updateMonsterHealth(int dmg){
		if(this.currentMonster.getCurrentHealth() - dmg < 0){
			this.currentMonster.setCurrentHealth(0);
			this.newServerMonster();
		} else {
			this.currentMonster.setCurrentHealth(this.currentMonster.getCurrentHealth() - dmg);
		}
	}
	
	/*
	 * Monster was killed so we're making a new one
	 */
	public void newServerMonster(){
		display("Creating a new monster.");
		this.currentMonster = newMonster(Monster.SCHIPPER, 40, ++monsterLevel);
		this.broadcastMonster(currentMonster);
	}
	
	/*
	 * Get serverMonster
	 */
	public MonsterEntity getServerMonster(){
		return this.currentMonster;
	}

	/*
	 * Stopping the server
	 */
	protected void stop() {
		running = false;
		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {

		}
	}

	/*
	 * Client connection will be removed from the list, should be called on client disconnect.
	 */
	public void remove(int id, User user){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getConnectionID() == id){
				clients.remove(i);
			}
		}
	}

	/*
	 * Displays
	 */
	private void display(String s){
		System.out.println(d.now() + " Server - " + s);
	}

	private void displayError(String s){
		System.err.println(d.now() + " Server - " + s);
	}

	/*
	 * Instances
	 */
	public static Server newInstance(int port){
		if(instance == null){
			instance = new Server(port);
		}
		return instance;
	}

	public static Server getInstance(){
		return instance;
	}

}
