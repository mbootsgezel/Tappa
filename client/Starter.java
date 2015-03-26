package client;

public class Starter {
	
	public static void main(String[] args) {
		//new Thread(new Window(800, 600)).start();
		new Thread(Client.newInstance("localhost", 1500)).start();
	}

}
