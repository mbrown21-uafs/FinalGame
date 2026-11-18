package game;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Random;

/*
 * Draws the forest background and decorative details.
 * Later, image files can be loaded here instead of drawing simple shapes.
 */
public class AssetManager {

    private int[] particleX;
    private int[] particleY;

    public AssetManager() {

        particleX = new int[80];
        particleY = new int[80];

        Random random = new Random();

        for (int index = 0; index < particleX.length; index++) {

            particleX[index] = random.nextInt(1000);
            particleY[index] = random.nextInt(680);
        }
    }

    public void drawBackground(Graphics2D g, int width, int height) {

        GradientPaint background = new GradientPaint(
            0, 0, new Color(10, 24, 25),
            0, height, new Color(42, 62, 43)
        );

        g.setPaint(background);
        g.fillRect(0, 0, width, height);

        // Dark tree silhouettes in the distance.
        g.setColor(new Color(8, 19, 17, 150));

        for (int index = 0; index < 8; index++) {

            int x = index * 145 - 30;
            int top = 170 + (index % 3) * 35;

            g.fillPolygon(
                new int[]{x, x + 75, x + 150},
                new int[]{height, top, height},
                3
            );
        }

        // Small glowing forest particles.
        for (int index = 0; index < particleX.length; index++) {

            g.setColor(new Color(190, 210, 130, 90));

            g.fillOval(
                particleX[index],
                particleY[index],
                3,
                3
            );
        }

        // Decorative mushroom caps.
        drawMushroom(g, 110, 300, 35);
        drawMushroom(g, 850, 350, 45);
        drawMushroom(g, 740, 250, 25);
    }

    private void drawMushroom(Graphics2D g, int x, int y, int size) {

        g.setColor(new Color(211, 190, 145));

        g.fillRect(
            x + size / 2 - 3,
            y + size / 2,
            6,
            size / 2
        );

        g.setColor(new Color(150, 69, 86));
        g.fillOval(x, y, size, size / 2);

        g.setColor(new Color(240, 220, 180));

        g.fillOval(x + size / 4, y + size / 8, 5, 5);
        g.fillOval(x + size / 2, y + size / 6, 5, 5);
    }
}