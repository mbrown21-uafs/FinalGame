package game;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame window = new JFrame("The Forgotten Forest");

                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.add(new GamePanel());
                window.pack();
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            }
        });
    }
}