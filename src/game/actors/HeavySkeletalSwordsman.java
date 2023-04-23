package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.utils.Status;
import game.weapons.Grossmesser;

public class HeavySkeletalSwordsman extends Enemy{
    /**
     * Constructor.
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q',153,
                35,892);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(!this.isConscious()){
            Location pos = map.locationOf(this);
            map.addActor(new PileOfBones(), pos);
            map.removeActor(this);
        }

        boolean following = false;

        // added getBehaviour()
        for (Behaviour behaviour : this.getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);

            // check if the current behaviour is following the player
            if(behaviour instanceof FollowBehaviour){
                following = true;
            }

            if(action != null)
                return action;
        }
        // de-spawn if not following player
        if(Math.random() <= 0.1 && !following){
            map.removeActor(this);
            System.out.println("Lone wolf removed");
            return new DoNothingAction();
        }
        return new DoNothingAction();
    }

    /**
     * The Heavy Skeletal Swordsman can be attacked by any actor that does not have the HEAVY_SKELETAL_SWORDSMAN
     * capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(!otherActor.hasCapability(Status.HEAVY_SKELETAL_SWORDSMAN)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }


}
