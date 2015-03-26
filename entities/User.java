package entities;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 6751066885125472057L;
	
	private String username;
	private int userid;
	private int level;
	private int damage;
	
	public User(String username) {
		this.username = username;
	}
	
	public User(){
		
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setUserID(int userid){
		this.userid = userid;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public String getUsername(){
		return username;
	}
	
	public int getUserID(){
		return userid;
	}
	
	public int getLevel(){
		return level;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public String toString(){
		return "Username: " + username + ", userid: " + userid + ", level: " + level + ", damage: " + damage;
	}

}
