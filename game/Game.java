package game;

import javax.swing.JFrame;

import model.CurrentDate;
import client.Client;

public class Game extends JFrame implements Runnable{
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static Game instance;
	
	private LoadingScreen loading;
	
	private LevelPanel levelPanel;
	
	private CurrentDate d = new CurrentDate();
	
	private Game() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.loading = new LoadingScreen();
		
		this.add(loading);
		this.setVisible(true);
	}
	
	@Override
	public void run() {
	
		
	}
	
	public void initGame(Monster monster){
		//display("Game init run");
		this.remove(loading);
		this.levelPanel = LevelPanel.getInstance();
		this.add(levelPanel);
		this.levelPanel.resetLevel();
		this.levelPanel.initLevel(monster);
		this.setVisible(true);
	}
	
	private void gameSleep(long time){
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			displayError("Can't put monster to sleep!");
		}
	}
	
	private void display(String s){
		System.out.println(d.now() + " Game - " + s);
	}
	
	private void displayError(String s){
		System.err.println(d.now() + " Game - " + s);
	}
	
	public static void selfDestroy(){
		instance = null;
	}
	
	public static Game getInstance(){
		if(instance == null){
			instance = new Game();
		}
		return instance;
	}
}
