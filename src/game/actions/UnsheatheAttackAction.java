package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Enemy;
import game.actors.PileOfBones;
import game.actors.HeavySkeletalSwordsman;

import java.util.Random;

public class UnsheatheAttackAction extends AttackAction{
    private Random rand = new Random();

    public UnsheatheAttackAction(Actor target, String direction, WeaponItem weapon){
        super(target,direction,weapon);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(rand.nextInt(100) <= 60)) {
            return actor + " misses " + this.getTarget() + ".";
        }

        // Unsheathe deals 2x damage
        int damage = 2 * this.getWeapon().damage();
        String result = actor + " " + this.getWeapon().verb() + " " + this.getTarget() + " for " + damage + " damage.";
        this.getTarget().hurt(damage);
        // Deals with unconscious actors
        if (!this.getTarget().isConscious()) {
            // Deals with spawning and de-spawning of heavy skeletal swordsman
            PileOfBones pileOfBones = new PileOfBones();
            if (this.getTarget() instanceof HeavySkeletalSwordsman) {
                System.out.println("Heavy Skeletal Swordsman turns into Pile of Bones.");
                pileOfBones.setPreviousEnemy((Enemy)this.getTarget());
                Location pos = map.locationOf(this.getTarget());
                map.removeActor(this.getTarget());
                map.addActor(pileOfBones, pos);
            } else {
                result += new DeathAction(actor).execute(this.getTarget(), map);
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " performs Unsheathe Skill to " + this.getTarget() + " at " + this.getDirection() + " with " + this.getWeapon() ;
    }
}
