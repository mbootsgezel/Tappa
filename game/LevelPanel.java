package game;

import java.awt.Color;

import javax.swing.JPanel;

import controllers.MouseController;
import entities.Click;

public class LevelPanel extends JPanel{
	
	private static LevelPanel instance;
	
	private HealthBar healthBar;
	
	private Monster monster;
	
	private LevelPanel() {
		this.addMouseListener(new MouseController());
		this.setSize(800, 500);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void damageMonster(Click click){
		healthBar.damage(click.getDamage());
	}
	
	public void setMonster(Monster monster){
		this.monster = monster;
		this.monster.setBounds(160, 80, 480, 480);
		this.add(monster);
		
		setHealthBar();
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
