package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Skill extends JPanel implements Runnable{

	public static final int BLAZEIT = 0, FLAME = 1;
	public static final int WIDTH = 50, HEIGHT = 50;

	private int type;
	private int cooldown;

	private volatile boolean onCooldown;

	private ImageIcon activeImage;
	private ImageIcon cooldownImage;

	private JLabel imageLabel;
	private JLabel cooldownLabel;
	private JLabel cooldownImageLabel;

	public Skill(int type, int cooldown) {

		this.setLayout(new BorderLayout());
		this.setSize(WIDTH, HEIGHT);

		this.type = type;
		this.cooldown = cooldown;
		this.onCooldown = false;

		this.activeImage = getResizedImage("/res/skills/nocturne.png");
		this.cooldownImage = getResizedImage("/res/skills/cooldown.png");

		this.imageLabel = new JLabel();
		this.imageLabel.setBounds(0, 0, WIDTH, HEIGHT);
		this.imageLabel.setIcon(activeImage);

		this.cooldownLabel = new JLabel();
		this.cooldownLabel.setSize(WIDTH, HEIGHT);
		this.cooldownLabel.setForeground(Color.WHITE);
		this.cooldownLabel.setFont(new Font("TimesRoman", Font.BOLD, 24));
		this.cooldownLabel.setHorizontalAlignment(JLabel.CENTER);
		
		this.cooldownImageLabel = new JLabel();
		this.cooldownImageLabel.setBounds(0, 0, WIDTH, HEIGHT);
		this.cooldownImageLabel.setIcon(cooldownImage);

		this.add(cooldownLabel, SwingConstants.CENTER);
		this.add(cooldownImageLabel, BorderLayout.CENTER);
		this.add(imageLabel, BorderLayout.CENTER);
		

		//System.out.println("imagelabel = " + this.getComponentZOrder(imageLabel));
		//System.out.println("cooldown = " + this.getComponentZOrder(cooldownLabel));

		this.setVisible(true);

	}

	@Override
	public void run() {
		System.out.println("Skill started!");
		try {
			while(true){
				if(!onCooldown){
					this.imageLabel.setIcon(activeImage);
					this.cooldownImageLabel.setVisible(false);
				}else{
					this.cooldownImageLabel.setVisible(true);
					cooldownCounter();

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void activateSkill(){

		if(!this.onCooldown){
			this.onCooldown = true;
		}else{
			System.out.println("Skill is on cooldown");
		}
	}

	public void resetSkill(){
		this.onCooldown = false;
		this.cooldownLabel.setText("");
	}

	public void cooldownCounter(){
		try {

			for (int i = this.cooldown; i > 0; i--) {
				System.out.println(i);
				this.cooldownLabel.setText(Integer.toString(i));
				//this.cooldownLabel = new JLabel(Integer.toString(i), SwingConstants.CENTER);
				Thread.sleep(1000);
			}

			resetSkill();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	public ImageIcon getResizedImage(String url){
		try {
			BufferedImage img = ImageIO.read(new File(getClass().getResource(url).toURI()));
			BufferedImage newimage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
			AffineTransform transform = new AffineTransform();
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



}


