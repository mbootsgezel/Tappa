package game;

import java.awt.Color;

import javax.swing.JPanel;

import controllers.MouseController;
import entities.Click;

public class LevelPanel extends JPanel{
	
	private static LevelPanel instance;
	
	private HealthBar healthBar;
	
	private Monster monster;
	
	private Thread monsterThread;
	
	private LevelPanel() {
		this.addMouseListener(new MouseController());
		this.setSize(800, 500);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void damageMonster(Click click){
		healthBar.damage(click.getDamage());
		if(healthBar.getCurrentHealth() > 0){
			monster.hitMonster();
		} else {
			monster.killMonster();
		}
	}

	public void initLevel(Monster monster){
		this.setMonster(monster);
		this.setHealthBar();
		this.runMonster();
	}
	
	public void resetLevel(){
		this.healthBar = null;
		this.monster = null;
	}
	/*
	 * initLevel step 1
	 */
	private void setMonster(Monster monster){
		this.monster = monster;
		this.monster.setBounds(160, 30, 480, 480);
		this.monster.reviveMonster();
		this.add(monster);
	}
	
	/*
	 *  initLevel step 2
	 */
	private void setHealthBar(){
		this.healthBar = new HealthBar(monster.getCurrentHealth(), monster.getMaxHealth());
		this.healthBar.setBounds(25, 0, 750, 30);
		this.add(healthBar);
	}
	
	/*
	 *  initLevel step 3
	 */
	private void runMonster(){
		this.monsterThread = new Thread(monster);
		this.monsterThread.start();
	}
	
	public Monster getMonster(){
		return monster;
	}
	
	public static LevelPanel getInstance(){
		if(instance == null){
			instance = new LevelPanel();
		}
		return instance;
	}
	
}
