package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/*
 * Describes one card. A card stores its name, cost, effect and type.
 */
public class Card {

    public static final int ATTACK = 0;
    public static final int SKILL = 1;

    private String name;
    private String description;
    private int cost;
    private int value;
    private int type;

    public Card(String name, String description,
            int cost, int value, int type) {

        this.name = name;
        this.description = description;
        this.cost = cost;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    public void draw(Graphics2D g, int x, int y,
            int width, int height) {

        if (type == ATTACK) {
            g.setColor(new Color(105, 54, 48));

        } else {
            g.setColor(new Color(48, 83, 76));
        }

        g.fillRoundRect(x, y, width, height, 16, 16);

        g.setColor(new Color(230, 207, 154));
        g.drawRoundRect(x, y, width, height, 16, 16);

        g.setFont(new Font("Serif", Font.BOLD, 16));
        g.drawString(name, x + 10, y + 25);

        g.setFont(new Font("SansSerif", Font.BOLD, 13));
        g.drawString("Energy: " + cost, x + 10, y + 48);

        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        g.drawString(description, x + 10, y + 82);

        g.setFont(new Font("Serif", Font.BOLD, 12));

        if (type == ATTACK) {
            g.drawString("ATTACK", x + 10, y + height - 15);

        } else {
            g.drawString("SKILL", x + 10, y + height - 15);
        }
    }
}
