package game;

/*
 * Controls the basic rules for a combat encounter.
 */
public class CombatManager {

    private GameState gameState;

    public CombatManager(GameState gameState) {
        this.gameState = gameState;
    }

    public void startBattle() {

        int enemyHealth = 35 + gameState.getFloor() * 5;
        int enemyDamage = 6 + gameState.getFloor();

        gameState.setEnemy(new Enemy(
            "Mossling Brute",
            enemyHealth,
            enemyDamage
        ));

        gameState.getHand().clear();
        gameState.setEnergy(gameState.getMaxEnergy());

        drawCards();

        gameState.getPlayer().resetBlock();
        gameState.setScreen(GameState.COMBAT);
    }

    public boolean playCard(Card card) {

        if (card == null) {
            return false;
        }

        if (!gameState.getHand().contains(card)) {
            return false;
        }

        if (gameState.getEnergy() < card.getCost()) {
            return false;
        }

        gameState.setEnergy(
            gameState.getEnergy() - card.getCost()
        );

        if (card.getType() == Card.ATTACK) {

            gameState.getEnemy().takeDamage(card.getValue());

        } else {

            gameState.getPlayer().gainBlock(card.getValue());
        }

        gameState.getHand().remove(card);
        gameState.getDeck().discardCard(card);

        if (gameState.getEnemy().isDefeated()) {
            gameState.setScreen(GameState.REWARD);
        }

        return true;
    }

    public void endTurn() {

        if (gameState.getScreen() != GameState.COMBAT) {
            return;
        }

        Enemy enemy = gameState.getEnemy();

        gameState.getPlayer().takeDamage(enemy.getAttackDamage());

        if (gameState.getPlayer().isDefeated()) {
            gameState.setScreen(GameState.GAME_OVER);
            return;
        }

        gameState.getPlayer().resetBlock();

        gameState.getDeck().discardHand(gameState.getHand());

        gameState.setEnergy(gameState.getMaxEnergy());

        drawCards();
    }

    private void drawCards() {

        for (int index = 0; index < 5; index++) {

            Card card = gameState.getDeck().drawCard();

            if (card != null) {
                gameState.getHand().add(card);
            }
        }
    }
}