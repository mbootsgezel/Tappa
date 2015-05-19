package controllers;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class KeyboardController implements KeyEventDispatcher{

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if(e.getID() == KeyEvent.KEY_RELEASED){
			switch(e.getKeyCode()){
			case KeyEvent.VK_1: case KeyEvent.VK_Q:
				System.out.println("Skill 1/Q activate");
				break;
			case KeyEvent.VK_2: case KeyEvent.VK_W:
				System.out.println("Skill 2/W activate");
				break;
			case KeyEvent.VK_3: case KeyEvent.VK_E:
				System.out.println("Skill 3/E activate");
				break;
			case KeyEvent.VK_4: case KeyEvent.VK_R:
				System.out.println("Skill 4/R activate");
				break;
			case KeyEvent.VK_5: case KeyEvent.VK_T:
				System.out.println("Skill 5/T activate");
				break;
			case KeyEvent.VK_6: case KeyEvent.VK_Y:
				System.out.println("Skill 6/Y activate");
				break;
			}
		}

		return false;
	}

}
