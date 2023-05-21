package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DespawnAction;
import game.behaviours.Behaviour;
import game.items.RemembranceOfTheGrafted;
import game.utils.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * A class that represents Godrick The Grafted.
 * @see Demigod
 * @author Iliyana
 */
public class GodrickTheGrafted extends Demigod{

    private Location lastLocation;

    /**
     * Constructor for GodrickTheGrafted class.
     */
    public GodrickTheGrafted() {
        super("Godrick The Grafted", 'Y', 6080);
        this.setEnemyRuneValue(20000);
        this.addCapability(Status.DEMIGOD);
        this.addWeaponToInventory(new AxeOfGodrick()); // weapon for phase 1
        this.addWeaponToInventory(new GraftedDragon()); // weapon for phase 2
        this.addItemToInventory(new RemembranceOfTheGrafted());
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        this.setLastLocation(map.locationOf(this));
        // Phase 1 - hit points above 50% of max hp - attack with Axe of Godrick
        // Phase 2 - hit points below or equal to 50% of max hp - attack with Grafted Dragon
        System.out.println(this.getWeaponInventory().get(0));

        if (this.hitPoints <= this.maxHitPoints * 0.5){
            if (this.getWeaponInventory().get(0).getClass() == AxeOfGodrick.class){
                this.removeWeaponFromInventory(this.getWeaponInventory().get(0));
            }
        }
        for (Behaviour behaviour : this.getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    /**
     * Method that will be invoked during the reset of the game.
     * @param map the game map the actor is on.
     * @see DespawnAction
     */
    @Override
    public void reset(GameMap map) {
        // Go back to initial position with full health if not DEFEATED
        if (this.hitPoints >0) {
            map.removeActor(this);
            map.moveActor(this, initialLocation); // initial position set in Application
            this.resetMaxHp(this.maxHitPoints);
            System.out.println("Godrick the Grafted's HP reset to: " + this.hitPoints);
        }
    }
}

