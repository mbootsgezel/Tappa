package entities;

import java.io.Serializable;

public class Entity implements Serializable{

	private static final long serialVersionUID = 7945147474269998569L;
	
	public final static int USER = 0, MONSTER = 1, SCORE = 2, CLICK = 3;
	
	private Object o;
	
	private int type;
	
	public Entity(int type, User user) {
		this.type = type;
		this.o = user;
	}
	
	public Entity(int type, MonsterEntity monster) {
		this.type = type;
		this.o = monster;
	}
	
	public Entity(int type, Score score) {
		this.type = type;
		this.o = score;
	}
	
	public Entity(int type, Click click) {
		this.type = type;
		this.o = click;
	}
	
	public int getType(){
		return type;
	}
	
	public Object getObject(){
		return o;
	}

}
