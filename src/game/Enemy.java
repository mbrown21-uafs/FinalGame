package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/*
 * Represents one enemy in a battle.
 */
public class Enemy {

    private String name;
    private int health;
    private int maxHealth;
    private int attackDamage;

    public Enemy(String name, int health, int attackDamage) {

        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.attackDamage = attackDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void takeDamage(int damage) {

        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }

    public boolean isDefeated() {
        return health <= 0;
    }

    public void draw(Graphics2D g, int x, int y) {

        // Shadow under the creature
        g.setColor(new Color(10, 18, 15, 150));
        g.fillOval(x - 85, y + 105, 170, 30);

        // Body and mushroom cap
        g.setColor(new Color(72, 104, 67));
        g.fillOval(x - 55, y + 30, 110, 105);

        g.setColor(new Color(125, 57, 65));
        g.fillOval(x - 70, y, 140, 65);

        // Mushroom spots
        g.setColor(new Color(235, 211, 166));
        g.fillOval(x - 38, y + 15, 14, 10);
        g.fillOval(x + 20, y + 22, 12, 9);

        // Eyes
        g.setColor(new Color(255, 90, 65));
        g.fillOval(x - 28, y + 65, 15, 15);
        g.fillOval(x + 13, y + 65, 15, 15);

        // Enemy name
        g.setColor(new Color(239, 222, 180));
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString(name, x - 75, y - 18);

        // Enemy health and attack intent
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.drawString("HP: " + health + "/" + maxHealth, x - 40, y + 160);
        g.drawString("Intent: " + attackDamage + " damage", x - 70, y + 185);
    }
}