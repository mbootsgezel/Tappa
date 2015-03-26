package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CurrentDate;

public class Monster extends JPanel implements Runnable{
	
	public static final int HUELE = 0, ZOIDBERG = 1, SCHIPPER = 2;
	
	private static final int IDLE = 0, HURT = 1, DEAD = 2, MAD = 3, MADHURT = 4;
	
	private static int MONSTERWIDTH = 0;
	private static int MONSTERHEIGHT = 0;
	
	private ImageIcon idle, hurt, dead, mad, madhurt;
	private JLabel imgLabel;
	
	private int currentHealth;
	private int maxHealth;
	private int type;
	private int level;
	
	private volatile boolean alive;
	private volatile boolean hit;
	
	private boolean enraged;
	private boolean enrageTriggered;
	
	private Timer enrage;
	
	private CurrentDate d = new CurrentDate();
	
	public Monster(int type, int level, int currentHealth, int maxHealth) {
		//display("Type: " + type + ", level: " + level + ", currentHealth: " + currentHealth + ", maxHealth: " + maxHealth);
		this.setSize(MONSTERWIDTH, MONSTERHEIGHT);
		this.setLayout(null);
		
		this.type = type;
		this.level = level;
		this.currentHealth = currentHealth;
		this.maxHealth = maxHealth;
		
		this.alive = true;
		this.MONSTERWIDTH = 480;
		this.MONSTERHEIGHT = 480;
		this.imgLabel = new JLabel();
		
		switch(type){
		case HUELE:
			setHealth(60);
			setImages("huele");
			break;
		case SCHIPPER:
			setHealth(40);
			setImages("schipper");
			break;
		case ZOIDBERG:
			setHealth(80);
			setImages("zoidberg");
			break;
		}
		
		this.imgLabel.setBounds(0, 0, MONSTERWIDTH, MONSTERHEIGHT);
		
		this.setIdle();
	
		this.add(imgLabel, BorderLayout.CENTER);
	
		//display("New monster created - currentHealth: " + this.getCurrentHealth() + ", maxHealth: " + this.getMaxHealth() +  ", level: " + this.getLevel());
		
		//this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	

	@Override
	public void run() {
		try {
			
			while(alive){
				if(hit){
					if(!enraged){
						if(!enrageTriggered && type == SCHIPPER){
							this.startEnrageTimer();
						}
						this.setHurt();
						Thread.sleep(100);
						this.setIdle();
					} else {
						this.setMadHurt();
						Thread.sleep(100);
						this.setMad();
					}
					this.hit = false;
				
				} else if (!alive){
					this.setDead();
				}
			}
			this.setDead();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	/*
	 * State setters
	 */
	private void setIdle(){
		this.imgLabel.setIcon(idle);
	}
	
	private void setHurt(){
		this.imgLabel.setIcon(hurt);
	}
	
	private void setDead(){
		this.imgLabel.setIcon(dead);
	}
	
	private void setMad(){
		this.enraged = true;
		this.imgLabel.setIcon(mad);
	}
	
	private void setMadHurt(){
		this.enraged = true;
		this.imgLabel.setIcon(madhurt);
	}
	
	/*
	 * Damage monster
	 */
	public void hitMonster(){
		this.hit = true;
	}
	
	public void killMonster(){
		this.alive = false;
	}
	
	/*
	 * Enrage timer start
	 */
	public void startEnrageTimer(){
		this.enrageTriggered = true;
		this.enrage = new Timer();
		this.enrage.schedule(new EnrageTimer(), 2*1000);
		
	}
	
	class EnrageTimer extends TimerTask{
		@Override
		public void run() {
			setMad();
		}
	}
	
	/*
	 * Setters
	 */
	private void setHealth(int health){
		//int i = random.nextInt(level*50);
		//this.health = health + ((health * (level * level)) + i);
	}
	
	private void setImages(String type){
		try {
			idle = getResizedImage("/res/" + type +"/idle.png");
		} catch (Exception e) {
			displayError("Can't load idle.png");
		}
		try {
			hurt = getResizedImage("/res/" + type +"/hurt.png");
		} catch (Exception e) {
			displayError("Can't load hurt.png");
		}
		try {
			dead = getResizedImage("/res/" + type +"/dead.png");
		} catch (Exception e) {
			displayError("Can't load dead.png");
		}
		try {
			mad = getResizedImage("/res/" + type +"/mad.png");
		} catch (Exception e) {
			displayError("Can't load mad.png");
		}
		try {
			madhurt = getResizedImage("/res/" + type +"/madhurt.png");
		} catch (Exception e) {
			displayError("Can't load madhurt.png");
		}
	}
	
	/*
	 * Getters
	 */
	public int getCurrentHealth(){
		return currentHealth;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getLevel(){
		return level;
	}
	
	private ImageIcon getResizedImage(String url){
		try {
			BufferedImage img = ImageIO.read(new File(getClass().getResource(url).toURI()));
			BufferedImage newimage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			AffineTransform transform = new AffineTransform();
			//transform.translate(480, 480);
			transform.scale(1.0, 1.0);
			AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			img = scaleOp.filter(img, newimage);
			return new ImageIcon(img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void display(String s){
		System.out.println(d.now() + " Monster - " + s);
	}
	
	private void displayError(String s){
		System.err.println(d.now() + " Monster - " + s);
	}

}
