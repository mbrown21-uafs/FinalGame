package game;

import java.util.ArrayList;

/*
 * Stores information that needs to be shared by different parts of the game.
 */
public class GameState {

    public static final int MAP = 0;
    public static final int COMBAT = 1;
    public static final int REWARD = 2;
    public static final int GAME_OVER = 3;

    private int screen;
    private Player player;
    private Enemy enemy;
    private Deck deck;
    private ArrayList<Card> hand;
    private int energy;
    private int maxEnergy;
    private int gold;
    private int floor;

    public GameState() {
        startNewRun();
    }

    public void startNewRun() {

        player = new Player();
        deck = new Deck();
        hand = new ArrayList<Card>();
        enemy = null;

        maxEnergy = 3;
        energy = maxEnergy;

        gold = 30;
        floor = 1;

        screen = MAP;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public int getFloor() {
        return floor;
    }

    public void increaseFloor() {
        floor++;
    }
}