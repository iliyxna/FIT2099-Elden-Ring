package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a Golden Fog Door.
 * @see MoveActorAction
 * @author Mustafa Khan
 */
public class GoldenFogDoor extends Ground{

    /**
     * A hashmap that stores the destination after going into the door.
     */
    private Map<String, Location> destinationMap = new HashMap<>();

    /**
     * Constructor for golden fog door.
     *
     */
    public GoldenFogDoor() {
        super('D');
    }

    /**
     * To check which actor can enter Site of Lost grace
     * @param actor the Actor to check
     * @return true if the actor can enter site of lost grace, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        // Only players can enter door
        return actor.hasCapability(Status.PLAYER);
    }

    /**
     * Method that deals with what players can do around the golden fog door.
     * It returns a list of actions that can be performed by actors that can use the
     * console menu. (e.g. player)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of actions that can be executed by the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actionList = new ActionList();
        if (actor.hasCapability(Status.PLAYER)){
            for (String key:destinationMap.keySet()){
                actionList.add(new MoveActorAction(destinationMap.get(key), key));
            }
        }
        return actionList;
    }

    /**
     * A method to add the destinations to the destinationMap list.
     * @param destinationName Name of the destination (e.g. Stormveil Castle, Limgrave, etc.)
     * @param destination Location of the destination
     */
    public void addDestination(String destinationName, Location destination) {
        destinationMap.put(destinationName, destination);
    }
}
