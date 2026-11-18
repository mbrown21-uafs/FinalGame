package game;

/*
 * Holds the player's health and defensive block.
 */
public class Player {

    private int health;
    private int maxHealth;
    private int block;

    public Player() {
        maxHealth = 70;
        health = 70;
        block = 0;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getBlock() {
        return block;
    }

    public void gainBlock(int amount) {

        if (amount > 0) {
            block += amount;
        }
    }

    public void takeDamage(int damage) {

        if (damage <= block) {
            block -= damage;
            damage = 0;

        } else {
            damage -= block;
            block = 0;
        }

        health -= damage;

        if (health < 0) {
            health = 0;
        }
    }

    public void heal(int amount) {

        health += amount;

        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void resetBlock() {
        block = 0;
    }

    public boolean isDefeated() {
        return health <= 0;
    }
}