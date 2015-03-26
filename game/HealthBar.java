package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class HealthBar extends JPanel{

	private JProgressBar health;
	private int maxHealth;
	private int currentHealth;

	public HealthBar(int currentHealth, int maxHealth) {
		this.health = new JProgressBar(0, maxHealth);
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;

		health.setStringPainted(true);
		health.setForeground(Color.GREEN);
		health.setString(currentHealth + "/" + maxHealth);
		health.setValue(currentHealth);
		health.setPreferredSize(new Dimension(750, 20));

		this.setSize(Game.WIDTH, 30);
		this.add(health);
	}

	public void damage(int dmg){
		if(currentHealth - dmg < 0){
			currentHealth = 0;
		} else {
			currentHealth = currentHealth - dmg;
		}
		health.setValue(currentHealth);
		health.setString(currentHealth + "/" + maxHealth);
		
	}

	public void setHealth(int newHealth){
		this.currentHealth = newHealth;
		health.setString(currentHealth + "/" + maxHealth);
		health.setValue(currentHealth);
	}
	
	public void setHealth(int currentHealth, int maxHealth){
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		health.setString(currentHealth + "/" + this.maxHealth);
		health.setValue(currentHealth);
	}

	public int getCurrentHealth(){
		return currentHealth;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}

}
