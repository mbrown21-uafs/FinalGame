package game;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Displays the game screens and responds to mouse and keyboard input.
 * The drawings use simple Java graphics so the code stays understandable.
 */
public class GamePanel extends JPanel {

    private GameState gameState;
    private CombatManager combatManager;
    private MapManager mapManager;
    private RewardManager rewardManager;
    private AssetManager assetManager;
    private String message;

    public GamePanel() {
        gameState = new GameState();
        combatManager = new CombatManager(gameState);
        mapManager = new MapManager(gameState, combatManager);
        rewardManager = new RewardManager(gameState, mapManager);
        assetManager = new AssetManager();
        message = "The forest is quiet... for now.";

        setPreferredSize(new Dimension(1000, 680));
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                handleMouseClick(event.getX(), event.getY());
                requestFocusInWindow();
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gameState.setScreen(GameState.MAP);
                    message = "Choose your next path.";
                    repaint();
                }
            }
        });

        Timer timer = new Timer(40, event -> repaint());
        timer.start();
    }

    private void handleMouseClick(int x, int y) {

        if (gameState.getScreen() == GameState.MAP) {

            if (y > 250 && y < 500) {
                mapManager.startEncounter();
                message = "Something moves between the mushrooms.";
            }

        } else if (gameState.getScreen() == GameState.COMBAT) {

            if (y >= 500 && y <= 660) {

                int cardIndex = (x - 135) / 140;

                if (cardIndex >= 0 && cardIndex < gameState.getHand().size()) {

                    Card card = gameState.getHand().get(cardIndex);

                    if (combatManager.playCard(card)) {
                        message = card.getName() + " played.";
                    } else {
                        message = "You do not have enough energy.";
                    }
                }

            } else if (x >= 820 && y >= 420 && y <= 485) {

                combatManager.endTurn();
                message = "The enemy takes its turn.";
            }

        } else if (gameState.getScreen() == GameState.REWARD) {

            rewardManager.collectReward();
            message = "You collect your reward and continue.";

        } else if (gameState.getScreen() == GameState.GAME_OVER) {

            gameState.startNewRun();
            message = "A new journey begins.";
        }

        repaint();
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        assetManager.drawBackground(g, getWidth(), getHeight());
        drawHeader(g);

        if (gameState.getScreen() == GameState.MAP) {
            drawMap(g);

        } else if (gameState.getScreen() == GameState.COMBAT) {
            drawCombat(g);

        } else if (gameState.getScreen() == GameState.REWARD) {
            drawReward(g);

        } else if (gameState.getScreen() == GameState.GAME_OVER) {
            drawGameOver(g);
        }

        drawMessage(g);
    }

    private void drawHeader(Graphics2D g) {

        g.setColor(new Color(8, 16, 17, 220));
        g.fillRoundRect(25, 18, 950, 65, 18, 18);

        g.setColor(new Color(235, 220, 180));
        g.setFont(new Font("Serif", Font.BOLD, 26));
        g.drawString("THE FORGOTTEN FOREST", 48, 58);

        g.setFont(new Font("SansSerif", Font.PLAIN, 15));
        g.drawString("HP: " + gameState.getPlayer().getHealth()
                + "/" + gameState.getPlayer().getMaxHealth()
                + "     Gold: " + gameState.getGold()
                + "     Floor: " + gameState.getFloor(), 680, 57);
    }

    private void drawMap(Graphics2D g) {

        drawTitle(g, "THE OVERGROWN PATH", "Choose a place to explore");

        String[] places = {
            "Mossbound Trail",
            "Whispering Hollow",
            "Old Shrine"
        };

        for (int index = 0; index < places.length; index++) {

            int x = 170 + index * 300;

            g.setColor(new Color(53, 91, 65));
            g.fillOval(x, 280, 120, 120);

            g.setColor(new Color(222, 195, 133));
            g.drawOval(x, 280, 120, 120);

            g.setColor(new Color(239, 225, 190));
            g.setFont(new Font("Serif", Font.BOLD, 16));
            g.drawString(places[index], x - 12, 430);
        }

        g.setFont(new Font("SansSerif", Font.PLAIN, 16));
        g.drawString(
            "Click one of the forest locations to begin an encounter.",
            285, 500
        );
    }

    private void drawCombat(Graphics2D g) {

        drawTitle(g, "A CREATURE IN THE FERNS",
                "Choose your cards carefully");

        Enemy enemy = gameState.getEnemy();

        if (enemy != null) {
            enemy.draw(g, 500, 190);
        }

        g.setColor(new Color(239, 222, 180));
        g.setFont(new Font("SansSerif", Font.BOLD, 17));

        g.drawString("Energy: " + gameState.getEnergy(), 45, 535);
        g.drawString("Block: " + gameState.getPlayer().getBlock(), 45, 565);

        g.setColor(new Color(12, 25, 24, 220));
        g.fillRoundRect(25, 585, 950, 75, 18, 18);

        for (int index = 0; index < gameState.getHand().size(); index++) {

            int x = 135 + index * 140;

            gameState.getHand().get(index).draw(g, x, 485, 120, 165);
        }

        drawButton(g, 825, 420, 140, 50, "END TURN");

        g.setColor(new Color(240, 225, 190));
        g.setFont(new Font("SansSerif", Font.PLAIN, 13));

        g.drawString(
            "Click a card to play it. Press ESC to return to the map.",
            45, 470
        );
    }

    private void drawReward(Graphics2D g) {

        drawTitle(g, "A MOMENT OF QUIET",
                "The forest leaves something behind");

        drawButton(g, 350, 310, 300, 65, "COLLECT REWARD");

        g.setColor(new Color(235, 220, 180));
        g.drawString("Gain gold and recover a little health.", 390, 410);
    }

    private void drawGameOver(Graphics2D g) {

        drawTitle(g, "LOST TO THE FOREST",
                "Your journey ends, but the forest remains.");

        drawButton(g, 350, 330, 300, 65, "START A NEW RUN");
    }

    private void drawTitle(Graphics2D g, String title, String subtitle) {

        g.setColor(new Color(239, 222, 177));
        g.setFont(new Font("Serif", Font.BOLD, 34));
        g.drawString(title, 285, 165);

        g.setColor(new Color(180, 196, 164));
        g.setFont(new Font("Serif", Font.PLAIN, 18));
        g.drawString(subtitle, 350, 200);
    }

    private void drawButton(Graphics2D g, int x, int y,
            int width, int height, String text) {

        g.setColor(new Color(83, 111, 75));
        g.fillRoundRect(x, y, width, height, 18, 18);

        g.setColor(new Color(230, 207, 154));
        g.drawRoundRect(x, y, width, height, 18, 18);

        g.setFont(new Font("Serif", Font.BOLD, 17));
        g.drawString(text, x + 18, y + height / 2 + 6);
    }

    private void drawMessage(Graphics2D g) {

        g.setColor(new Color(8, 15, 16, 220));
        g.fillRoundRect(250, 95, 500, 38, 12, 12);

        g.setColor(new Color(230, 220, 190));
        g.setFont(new Font("Serif", Font.ITALIC, 15));

        g.drawString(message, 270, 119);
    }
}