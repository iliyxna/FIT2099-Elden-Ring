package game.utils;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * MapSize class is used to obtain the size of the game map that the player is currently in and to be used
 * to spawn enemies based on their location
 * @see game.environments.Graveyard
 * @see game.environments.Wind
 * @see game.environments.Water
 * @author Damia
 */
public class MapSize {

    /**
     * Static attribute to store the map player is in
     */
    private static GameMap playerMap;

    /**
     * Static attribute to store the length of x-axis of the map
     */
    private static int x;

    /**
     * Static method to get the map that the player is on
     */
    public static GameMap getPlayerMap() {
        return playerMap;
    }

    /**
     * Static method to set the map that the player is on
     */
    public static void setPlayerMap(GameMap playerMap) {
        MapSize.playerMap = playerMap;
    }

    /**
     * Static method to get the length of x-axis for the map that player is on
     */
    public static int getHorizontalSize(){
        if(playerMap != null){
            x =  playerMap.getXRange().min() + playerMap.getXRange().max();
        }
        return x;
    }
}
