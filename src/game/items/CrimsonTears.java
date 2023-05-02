package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Consumable;
import game.reset.Resettable;
import game.utils.Status;

public class CrimsonTears extends Item implements Consumable {
    private final int HEAL_AMOUNT = 250;

    public CrimsonTears() {
        super("Flask of Crimson Tears", 'f', false); // portable: false because cannot be dropped.
        this.addCapability(Status.CONSUMABLE);
    }

    @Override
    public int getHealAmount() {
        return this.HEAL_AMOUNT;
    }


}


//package game.items;
//
//import edu.monash.fit2099.engine.items.Item;
//import game.Consumable;
//import game.reset.Resettable;
//import game.utils.Status;
//
//public class CrimsonTears extends Item implements Consumable {
//    private final int HEAL_AMOUNT = 250;
//
//    public CrimsonTears() {
//        super("Flask of Crimson Tears", 'f', false); // portable: false because cannot be dropped.
//        this.addCapability(Status.CONSUMABLE);
//    }
//
//    @Override
//    public int getHealAmount() {
//        return this.HEAL_AMOUNT;
//    }
//
//
//}



//package game.items;
//
//public class CrimsonTears {
//    private int usesLeft;
//    private final int MAX_USES = 2;
//    private final int HEAL_AMOUNT = 250;
//
//    public CrimsonTears() {
//        usesLeft = MAX_USES;
//    }
//
//    public boolean use() {
//        if (usesLeft > 0) {
//            usesLeft--;
//            // restore health by HEAL_AMOUNT
//            return true;
//        }
//        return false; // no uses left
//    }
//
//    public int getUsesLeft() {
//        return usesLeft;
//    }
//}
