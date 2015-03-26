package game;

import java.awt.BorderLayout;
import java.awt.Window;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoadingScreen extends JPanel {
	
	private JLabel loadLabel;
	
	public LoadingScreen() {
		this.setSize(Window.WIDTH, Window.HEIGHT);
		this.setLayout(new BorderLayout());
		
		this.loadLabel = new JLabel("Connecting to server..");
		this.loadLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(loadLabel, BorderLayout.CENTER);

	}

}
