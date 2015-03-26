package entities;

import java.io.Serializable;

public class MonsterEntity implements Serializable{
	
	private static final long serialVersionUID = -8514788909396421042L;

	private int currentHealth;
	private int maxHealth;
	private int level;
	private int type;
	
	public MonsterEntity(int type, int level, int currentHealth, int maxHealth) {
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		this.level = level;
		this.type = type;
	}
	
	public void setCurrentHealth(int currentHealth){
		this.currentHealth = currentHealth;
	}
	
	public int getCurrentHealth(){
		return currentHealth;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getType(){
		return type;
	}
	
	public String toString(){
		return "Health: " + currentHealth + "/" + maxHealth;
	}

}
