package entities;

import java.io.Serializable;

public class Click implements Serializable{

	private static final long serialVersionUID = 4786247447059075524L;
	
	private int x;
	private int y;
	private int userid;
	private int damage;
	
	public Click(int x, int y, int userid, int damage){
		this.x = x;
		this.y = y;
		this.userid = userid;
		this.damage = damage;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getUserID(){
		return userid;
	}
	
	public int getDamage(){
		return damage;
	}

	public String toString(){
		return "Client: " + userid + ", damage: " + damage + ", X= " + x + ", Y= " + y;
	}

}
