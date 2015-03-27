package game;

import java.awt.Color;

import javax.swing.JPanel;

import controllers.MouseController;
import entities.Click;

public class LevelPanel extends JPanel{
	
	private static LevelPanel instance;
	
	private HealthBar healthBar;
	
	private Monster monster;
	
	//private Splash splash;
	
	private LevelPanel() {
		this.addMouseListener(new MouseController());
		this.setSize(800, 500);
		this.setLayout(null);
//		this.splash = new Splash();
//		this.splash.setBounds(0, 200, 100, 100);
//		this.add(splash);
		
		this.setVisible(true);
	}
	
	public void damageMonster(Click click){
		healthBar.damage(click.getDamage());
//		this.add(splash = new Splash());
//		splash.Show(click.getX(), click.getY());
//		splash.setBounds(click.getX(), click.getY(), 100, 100);
		if(healthBar.getCurrentHealth() > 0){
			monster.hitMonster();
			
		} else {
			monster.killMonster();
		}
	}
	
	public void setMonster(Monster monster){
		this.monster = monster;
		this.monster.setBounds(160, 80, 480, 480);
		this.add(monster);	
		setHealthBar();
	}
	
	public void runMonster(){
		new Thread(monster).start();
	}
	
	private void setHealthBar(){
		this.healthBar = new HealthBar(monster.getCurrentHealth(), monster.getMaxHealth());
		this.healthBar.setBounds(25, 0, 750, 30);
		this.add(healthBar);
	}
	
	public static LevelPanel getInstance(){
		if(instance == null){
			instance = new LevelPanel();
		}
		return instance;
	}
	
}
