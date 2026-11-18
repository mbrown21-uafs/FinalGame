package game;

/*
 * Gives the player a simple reward after winning a battle.
 */
public class RewardManager {

    private GameState gameState;
    private MapManager mapManager;

    public RewardManager(GameState gameState, MapManager mapManager) {

        this.gameState = gameState;
        this.mapManager = mapManager;
    }

    public void collectReward() {

        gameState.addGold(15);
        gameState.getPlayer().heal(5);

        mapManager.moveToNextFloor();
    }
}
