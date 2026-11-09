package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		
		// Grass Background
		g.setColor(new Color(35,75,48));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Dirt Path
		g.setColor(new Color(130,95,60));
		g.fillOval(300, 100, 300, 600);
		
		//Trees on the left
		drawTree(g, 80,100);
		drawTree(g, 180,300);
		drawTree(g, 50,480);
		
		//Rocks
		g.setColor(new Color(100,100,100));
		g.fillRect(260, 250, 35, 25);
		g.fillOval(580, 400, 40, 30);
		
		//Game Title
		g.setColor(Color.WHITE);
		g.drawString("Forgoten Forest", 380,40);
		
	}
	
	private void drawTree(Graphics g, int x, int y) {
		//Tree trunk
		g.setColor(new Color(95,60,35));
		g.fillRect(x + 25, y + 50, 25, 80);
		
		g.setColor(new Color(35,75,48));
		g.fillOval(x, y, 75, 85);
	}
	
}