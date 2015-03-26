package game;

import javax.swing.JPanel;

public class GameContainer extends JPanel{
	
	private static GameContainer instance;
	
	private GameContainer() {
		this.setSize(800, 600);
		this.setLayout(null);
		//this.add(huele);
		//huele.setBounds(160, 80, huele.WIDTH, huele.HEIGHT);
	}
	
	public static GameContainer getInstance(){
		if(instance == null){
			instance = new GameContainer();
		}
		return instance;
	}

}
