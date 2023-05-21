package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class ResetManager {
    /**
     * List of resettables
     */
    private List<Resettable> resettables;

    /**
     * Reset Manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * HINT 1: where have we seen a private constructor before?
     * HINT 2: see the instance attribute above.
     */
    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the resettable objects in the list.
     */
    public void run(GameMap map) {

        for (Resettable resettable : resettables){
            resettable.reset(map);
        }
        resettables.clear();
    }

    /**
     * A method to add a resettable object to the list of resettable objects.
     * @param resettable resettable object to be added
     */
    public void registerResettable(Resettable resettable) {
        this.resettables.add(resettable);
    }

    /**
     * Remove a resettable object from the list of resettable objects.
     * @param resettable resettable object to be removed
     */
    public void removeResettable(Resettable resettable) {
        this.resettables.remove(resettable);
    }

    /**
     * Getter method to get resettables
     * @return list of resettables
     */

    public List<Resettable> getResettables(){return this.resettables;}
}
