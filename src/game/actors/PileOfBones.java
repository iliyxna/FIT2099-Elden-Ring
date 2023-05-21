package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.utils.Status;
import game.weapons.Grossmesser;
import game.weapons.Scimitar;

/**
 * Class representing the pile of bones. Any enemy with Skeletal type
 * will turn into pile of bones after the hit point of the enemy is 0.
 * After 3 turns, if the pile of bones is not destroyed, it turns back
 * into the previous enemy.
 * @see Actor
 * @see Resettable
 * @author Damia
 */
public class PileOfBones extends Actor implements Resettable {
    /**
     * To count the number of turns.
     */
    private int counter;

    /**
     * To store the previous enemy which is done in the Attack action classes.
     * @see AttackAction
     * @see game.actions.AreaAttackAction
     * @see game.actions.UnsheatheAttackAction
     * @see game.actions.QuickstepAttackAction
     */
    private Enemy previousEnemy;

    /**
     * Constructor for the pile of bones class.
     */
    public PileOfBones() {
        super("Pile of Bones",'X',1);
        // add to list of resettables for game reset
        ResetManager.getInstance().registerResettable(this);
        this.addCapability(Status.SKELETAL_TYPE);
        this.addCapability(Status.PILE_OF_BONES);
        this.addCapability(Status.CAN_DESPAWN_WHEN_RESET);
        counter = 1;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Location pos = map.locationOf(this);

        if(!this.isConscious()){
            map.removeActor(this);

        }else if (counter == 3 && this.isConscious()){
            map.removeActor(this);
            if(this.previousEnemy.getClass() == HeavySkeletalSwordsman.class){
                map.addActor(new HeavySkeletalSwordsman(), pos);
                System.out.println("Heavy Skeletal Swordsman re-spawned.");
            }else{
                map.addActor(new SkeletalBandit(), pos);
                System.out.println("Skeletal Bandit re-spawned.");
            }

        }

        counter += 1;
        return new DoNothingAction();
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        // attack with intrinsic weapon
        actions.add(new AttackAction(this,direction));

        // If player has weapon, can choose to fight with weaponItem
        if (otherActor.getWeaponInventory().size() != 0){
            // Use first weapon in inventory to attack
            WeaponItem attackWeapon = otherActor.getWeaponInventory().get(0);
            actions.add(new AttackAction(this, direction, attackWeapon));
            // Some weapons have unique skills
            if (attackWeapon.hasCapability(Status.UNIQUE_SKILL)){
                actions.add(attackWeapon.getSkill(this, direction));
            }
        }
        return actions;
    }

    /**
     * Setter method to set the enemy before turning into pile of bones.
     * @param previousEnemy the enemy before turning into pile of bones.
     */
    public void setPreviousEnemy(Enemy previousEnemy) {
        this.previousEnemy = previousEnemy;
    }

    /**
     * Getter method to set the enemy before turning into pile of bones.
     * @return the enemy before turning into pile of bones.
     */
    public Enemy getPreviousEnemy() {
        return this.previousEnemy;
    }

    /**
     * Method that will be invoked during the reset of the game.
     * @param map the game map the actor is on.
     * @see DespawnAction
     */
    @Override
    public void reset(GameMap map){
        DespawnAction despawnAction = new DespawnAction();
        despawnAction.execute(this, map);
    }
}
