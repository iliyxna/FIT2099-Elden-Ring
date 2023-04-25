package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.SpinningAttackAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemy extends Actor {

    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private final int MAX_RUNE_VALUE;
    private final int MIN_RUNE_VALUE;
    private int enemyRuneValue;
    private boolean isFollowingPlayer = false;

    /**
     * Constructor.
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     * @param minRuneValue  the minimum amount of runes an enemy could drop
     * @param maxRuneValue  the maximum amount of runes an enemy could drop
     */
    public Enemy(String name, char displayChar, int hitPoints, int minRuneValue, int maxRuneValue) {
        super(name, displayChar, hitPoints);
        MIN_RUNE_VALUE = minRuneValue;
        MAX_RUNE_VALUE = maxRuneValue;
        enemyRuneValue = RandomNumberGenerator.getRandomInt(MIN_RUNE_VALUE,MAX_RUNE_VALUE);
        addBehaviour(999, new WanderBehaviour());
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (isFollowingPlayer){
                addBehaviour(998, new FollowBehaviour(otherActor));
                isFollowingPlayer = true;
            }
            // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
            // Check if enemy weapon inventory have any weapons, if not then use intrinsic weapon
            if (this.getWeaponInventory() == null){
                actions.add(new AttackAction(this, direction));
            } else {
                // Use first weapon in the weapon inventory to attack
                WeaponItem attackWeapon = this.getWeaponInventory().get(0);
                actions.add(new AttackAction(this, direction, attackWeapon));
            }
        }
        return actions;
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    public void addBehaviour(int number, Behaviour behaviour){
        behaviours.put(number,behaviour);
    }

    public int getEnemyRuneValue() {
        return enemyRuneValue;
    }


}
