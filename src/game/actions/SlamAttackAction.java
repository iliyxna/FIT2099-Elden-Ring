package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

import java.util.ArrayList;
import java.util.List;

public class SlamAttackAction extends Action {

    List<Actor> targets = new ArrayList<>();
    @Override
    public String execute(Actor actor, GameMap map) {
        String res ="";

        // get player location
        Location playerLoc = map.locationOf(actor);
        for(Exit exit : playerLoc.getExits()){
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                targets.add(destination.getActor());
            }
        }
        // attack all creatures

//        for(Actor target : targets){
//            if(target.hasCapability(Status.AREA_ATTACK)){
//
//            }
//        }
        return res;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
