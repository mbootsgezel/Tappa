package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import client.Client;
import entities.Click;
import entities.User;
import game.LevelPanel;
import game.Splash;


public class MouseController implements MouseListener{
	
	User user = Client.getInstance().getUser();
	
	public MouseController() {
		
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Client.getInstance().sendClick(e.getX(), e.getY());
		LevelPanel.getInstance().damageMonster(new Click(e.getX(), e.getY(), user.getUserID(), user.getDamage()));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
