package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.utils.Status;

import java.util.ArrayList;
import java.util.List;

public class GiantCrab extends Enemy{
    /**
     * Constructor.
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407,
                318,4961);
        this.addCapability(Status.GIANT_CRAB);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
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
            return new DoNothingAction();
        }
        return new DoNothingAction();

    }

    /**
     * The Giant Crab  can be attacked by any actor that does not have the GIANT_CRAB capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        List<Actor> targets = new ArrayList<>();
        if(!otherActor.hasCapability(Status.GIANT_CRAB) && Math.random() <= 0.5){

            // get actor location
            Location actorLoc = map.locationOf(this);
            for(Exit exit : actorLoc.getExits()){
                Location destination = exit.getDestination();
                if (destination.containsAnActor()) {
                    targets.add(destination.getActor());
                }
            }

//            // attack each target
//            for(Actor target : targets){
//                target;
//            }

        }else{
            actions.add(new AttackAction(this, direction));
            // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
        }
        return actions;
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "claws");
    }
}
