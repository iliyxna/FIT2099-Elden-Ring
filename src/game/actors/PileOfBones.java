package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.utils.Status;
import game.weapons.Grossmesser;

public class PileOfBones extends Actor {

    private int counter;

    private Location location;
    /**
     * Constructor.
     */
    public PileOfBones(Location loc) {
        super("Pile of Bones",'X',1);
        this.addWeaponToInventory(new Grossmesser());
        this.location = loc;
        counter = 0;
    }



    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (counter == 3 && this.isConscious()){
            map.removeActor(this);
            map.addActor(new HeavySkeletalSwordsman(), location);
        }

        counter += 1;
        return null;
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(!otherActor.hasCapability(Status.HEAVY_SKELETAL_SWORDSMAN)){
            actions.add(new AttackAction(this, direction));
            // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
        }
        return actions;
    }
}
