package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

public class Enemy extends Actor {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    private int enemyRuneValue;
    private boolean isFollowingPlayer = false;

    private Status selfType;

    /**
     * Constructor.
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     * @param maxRuneValue
     */
    public Enemy(String name, char displayChar, int hitPoints, int minRuneValue, int maxRuneValue) {
        super(name, displayChar, hitPoints);
        enemyRuneValue = RandomNumberGenerator.getRandomInt(minRuneValue,maxRuneValue);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(997, new AttackBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(Math.random() <= 0.1 && !isFollowingPlayer){
            return new DespawnAction();
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null){
                return action;
            }
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            if (!isFollowingPlayer){
                this.addBehaviour(998, new FollowBehaviour(otherActor));
                isFollowingPlayer = true;
            }
            if (otherActor.getWeaponInventory().size() != 0){
                // Use first weapon in inventory to attack
                WeaponItem attackWeapon = otherActor.getWeaponInventory().get(0);
                actions.add(new AttackAction(this, direction, attackWeapon));
                // Some weapons have unique skills
                if (attackWeapon.hasCapability(Status.UNIQUE_SKILL)){
                    actions.add(attackWeapon.getSkill(this, direction));
                }
            }else{
                actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
            }
            // Player can choose to attack with intrinsic weapon
//            actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
        }
        return actions;
    }


    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }


    public Status getSelfType() {
        return selfType;
    }

    public void setSelfType(Status selfType) {
        this.selfType = selfType;
    }

    public boolean getIsFollowingPlayer() {
        return this.isFollowingPlayer; }

    public void addBehaviour(int number, Behaviour behaviour){
        behaviours.put(number,behaviour);
    }

}