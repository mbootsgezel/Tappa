package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.CurrentDate;

public class Splash extends JPanel{

	public static final int SPLASH1 = 1, SPLASH2 = 2, SPLASH3 = 3; 

	private static int SPLASHWIDTH = 200;
	private static int SPLASHHEIGHT = 200;

	private ImageIcon splash1, splash2, splash3;
	private JLabel splashLabel;

	private Random r;
	
	private Timer splashShowTimer;
	
	private CurrentDate d = new CurrentDate();

	public Splash(){

		this.setSize(100, 100);
		this.setLayout(null);
		
		this.splashLabel = new JLabel();
		
		this.splashLabel.setBounds(0, 0, 100, 100);
		
		this.r = new Random();
		
		this.setImages();
		
		this.splashLabel.setIcon(splash1);
		
		//this.setBackground(Color.BLACK);
		
		this.add(splashLabel, BorderLayout.CENTER);
		
		this.setVisible(true);
		
		
	}

	public void Show(int x, int y){
		displayError("Setting splash @ X" + x + ", Y" + y);
		switch (r.nextInt(2)+1){
		case 1 :
			setSplash1();
			break;
		case 2 : 
			setSplash2();
			break;
		case 3 : 
			setSplash3();
			break;
		}
		
		this.setVisible(true);
		
		
		
	}

	private void setSplash1(){
		this.splashLabel.setIcon(splash1);
	}

	private void setSplash2(){
		this.splashLabel.setIcon(splash2);
	}

	private void setSplash3(){
		this.splashLabel.setIcon(splash3);
	}

	private void setImages(){
		try {
			splash1 = getResizedImage("/res/splash/splash1.png" );
		} catch (Exception e) {
			displayError("Can't load splash1.png");
		}
		try {
			splash2 = getResizedImage("/res/splash/splash2.png" );
		} catch (Exception e) {
			displayError("Can't load splash2.png");
		}
		try {
			splash3 = getResizedImage("/res/splash/splash3.png" );
		} catch (Exception e) {
			displayError("Can't load splash3.png");
		}
	}
	
	public void startSplashShowTimer(){
		
		this.splashShowTimer = new Timer();
		//this.splashShowTimer.schedule(new ShowTimer(), 100);
		
	}

	private ImageIcon getResizedImage(String url){
		try {
			BufferedImage img = ImageIO.read(new File(getClass().getResource(url).toURI()));
			BufferedImage newimage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			AffineTransform transform = new AffineTransform();
			//transform.translate(480, 480);
			transform.scale(0.5, 0.5);
			AffineTransformOp scaleOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			img = scaleOp.filter(img, newimage);
			return new ImageIcon(img);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private void displayError(String s){
		System.err.println(d.now() + " Splash - " + s);
	}

}
