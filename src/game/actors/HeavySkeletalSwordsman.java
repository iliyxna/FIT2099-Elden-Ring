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

public class HeavySkeletalSwordsman extends Enemy {

    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q',153,
                35,892);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(Status.HEAVY_SKELETAL_SWORDSMAN);
        this.setSelfType(Status.SKELETAL_TYPE);
    }

}