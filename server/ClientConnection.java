package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import entities.Click;
import entities.Entity;
import entities.MonsterEntity;
import entities.User;
import model.CurrentDate;

public class ClientConnection extends Thread {

	private Socket socket;
	private int id;
	
	private CurrentDate d = new CurrentDate();

	private boolean running;

	private ObjectOutputStream toClient;
	private ObjectInputStream fromClient;
	
	private User user;

	public ClientConnection(Socket socket, int id) {
		this.socket = socket;
		this.id = id;
		
		/*
		 * Connection succesfully created, trying to display the connectee
		 */
		try {
			display("Connection from: " + socket.getInetAddress().getHostAddress() + ", ID = " + id);
		} catch (Exception e) {
			displayError("Connected from unknown host.");
		}

		/*
		 * Try to create IO for the client connection
		 */
		try {
			toClient = new ObjectOutputStream(socket.getOutputStream());
			fromClient = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			displayError("Failed to load IO: " + e);
		}
		
		this.start();
	}

	@Override
	public void run() {
		running = true;
		
		try {
			
			sendClientMonster(Server.getInstance().getServerMonster());
			
			while(running){
				
				Entity ent = (Entity) fromClient.readObject();
				
				switch(ent.getType()){
				case Entity.USER:
					//display("Received user from client");
					this.user = (User) ent.getObject();
					this.user.setUserID(id);
					Server.getInstance().addUser(this.user);
					sendClientUser();
					break;
				case Entity.CLICK:
					display("Received click from client: " + this.user.getUserID());
					Server.getInstance().broadcastClick((Click) ent.getObject(), this.user);
					break;
				}
			}
			
		} catch (Exception e){
			displayError("Client: " + id + " has disconnected from server");
			Server.getInstance().remove(id, user);
			
		}

	}
	
	public void sendClientUser(){
		//display("Sending new user back to client");
		Entity user = new Entity(Entity.USER, this.user);
		try { 
			toClient.writeObject(user);
		} catch (Exception e){
			displayError("Can't send user to client. Exception caught.");
		}
	}
	
	public void sendClientClick(Click click){
		//display("Sending new click back to client");
		Entity newClick = new Entity(Entity.CLICK, click);
		try { 
			toClient.writeObject(newClick);
		} catch (Exception e){
			displayError("Can't send user to client. Exception caught.");
		}
	}
	
	public void sendClientMonster(MonsterEntity monster){
		//display("Sending new serverMonster to client");
		Entity newMonster = new Entity(Entity.MONSTER, monster);
		try { 
			toClient.writeObject(newMonster);
		} catch (Exception e){
			displayError("Can't send monster to client. Exception caught.");
		}
	}
	
	/*
	 * Getters
	 */
	public int getConnectionID(){
		return id;
	}
	
	public String getUsername(){
		return user.getUsername();
	}

	/*
	 * Displays
	 */
	private void display(String s){
		System.out.println(d.now() + " Connection - " + s);
	}
	private void display(boolean b){
		System.out.println(d.now() + " Connection - " + b);
	}
	private void display(int i){
		System.out.println(d.now() + " Connection - " + i);
	}
	
	private void displayError(String s){
		System.err.println(d.now() + " Connection - " + s);
	}
	private void displayError(boolean b){
		System.err.println(d.now() + " Connection - " + b);
	}
	private void displayError(int i){
		System.err.println(d.now() + " Connection - " + i);
	}

}
