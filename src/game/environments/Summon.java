package game.environments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;
import game.actions.UpgradeHPAction;
import game.actors.Ally;
import game.actors.Invader;
import game.utils.Status;

/**
 * A class that represents the Summon sign.
 * @see Ally
 * @see Invader
 * @author Mustafa Khan
 * Modified by: Iliyana
 */
public class Summon extends Ground {

    /**
     * Constructor.
     */
    public Summon() {
        super('=');
    }


    /**
     * Method that deal with what the Player can do to the current enemy.
     * It returns a list of actions that can be performed by actors that can use the
     * console menu. (e.g. player)
     *
     * @param actor the Actor that might be performing attack
     * @param location Location of the summon sign
     * @param direction String representing the direction of the other Actor
     * @return a list of actions that can be performed by actors that can use the console menu. (e.g. player)
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.hasCapability(Status.PLAYER)){
            Location playerLocation = location.map().locationOf(actor);
            if (playerLocation.getGround().getClass() == this.getClass()){    // ensure that player is ON the summon sign
                for (Exit exit : location.getExits()){
                    Location destination = exit.getDestination();

                    if (!destination.containsAnActor()){
                        // 50% chance to summon Ally
                        if (Math.random() <= 0.5){
                            Ally ally = new Ally();
                            if (destination.canActorEnter(ally)){
                                actions.add(new SummonAction(ally, destination));
                                break;
                            }
                        }

                        // if Ally is not summoned, Invader will be summoned
                        else {
                            Invader invader = new Invader();
                            if (destination.canActorEnter(invader)){
                                actions.add(new SummonAction(invader, destination));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return actions;
    }


}

