package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.utils.Status;

public class Grossmesser extends WeaponItem {
    /**
     * Constructor.
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "swish",85 );
        this.addCapability(Status.GROSSMESSER_SKILL);
    }





}
