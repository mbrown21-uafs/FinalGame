package game;

/*
 * Starts encounters and keeps track of the player's map progress.
 * This is intentionally simple and can be expanded into a branching map later.
 */
public class MapManager {

    private GameState gameState;
    private CombatManager combatManager;

    public MapManager(GameState gameState, CombatManager combatManager) {

        this.gameState = gameState;
        this.combatManager = combatManager;
    }

    public void startEncounter() {
        combatManager.startBattle();
    }

    public void moveToNextFloor() {

        gameState.increaseFloor();
        gameState.setScreen(GameState.MAP);
    }
}