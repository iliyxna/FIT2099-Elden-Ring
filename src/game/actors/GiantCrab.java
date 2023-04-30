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

public class GiantCrab extends Enemy {

    /**
     * Constructor.
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407,
                318,4961);
        this.addCapability(Status.GIANT_CRAB);
        this.setSelfType(Status.WATER_TYPE);
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

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(208, "claws",90);
    }
}
