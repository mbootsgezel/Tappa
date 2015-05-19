package controllers;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class ActionSound implements ActionListener{
	
	private String url;
	private Clip clip = null;
	
	public ActionSound(String url) {
		this.url = url;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//button.setEnabled(false);
		playSound();
	}
	
	public void playSound(){
		if(1==1){
			try {
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource("/" + url));
				clip = AudioSystem.getClip();
				clip.open(audioIn);
				clip.start();
				//clip.loop(100);
			} catch (Exception exc){
				System.err.println("Can't load sound!");
			}
		} else {
			//clip.stop();
		}
	}
	
	public void stopSound(){
		try{
			clip.stop();
		} catch (Exception exc){
			System.err.println("DA SOUND IS UNSTOPPABLE!");
		}
	}

}
