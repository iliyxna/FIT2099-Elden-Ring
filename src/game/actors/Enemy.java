package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
//        this.behaviours.put(998, new FollowBehaviour(player));
        this.behaviours.put(999, new WanderBehaviour());
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }
}
