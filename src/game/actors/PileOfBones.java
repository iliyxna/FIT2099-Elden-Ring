package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.utils.Status;
import game.weapons.Grossmesser;

public class PileOfBones extends Actor {

    private int counter;

    /**
     * Constructor.
     */
    public PileOfBones() {
        super("Pile of Bones",'X',1);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
        counter = 1;
    }



    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        Location pos = map.locationOf(this);

        if(!this.isConscious()){
            map.removeActor(this);
            return new DoNothingAction();
        }else if (counter == 3 && this.isConscious()){
            map.removeActor(this);
            map.addActor(new HeavySkeletalSwordsman(), pos);
            return new DoNothingAction();
        }

        counter += 1;
        return new DoNothingAction();
    }

    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(!otherActor.hasCapability(Status.HEAVY_SKELETAL_SWORDSMAN)) {
            if (otherActor.getWeaponInventory().size() != 0) {
                // Use first weapon in inventory to attack
                WeaponItem attackWeapon = otherActor.getWeaponInventory().get(0);
                actions.add(new AttackAction(this, direction, attackWeapon));
                // Some weapons have unique skills
                if (attackWeapon.hasCapability(Status.UNIQUE_SKILL)) {
                    actions.add(attackWeapon.getSkill(this, direction));
                }
            } else {
                actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
            }
        }
        return actions;
    }
}
