package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.BuyWeaponAction;
import game.actions.SellWeaponAction;
import game.rune.Rune;
import game.utils.Status;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Grossmesser;
import game.weapons.Uchigatana;

import java.util.HashMap;
import java.util.Map;

public class Trader extends Actor {
    private String name;

    /**
     * Constructor for Trader class.
     */
    public Trader() {
        super("Merchant Kale", 'K', 0);
        addCapability(Status.CAN_ENTER_FLOOR);
    }

    /**
     * Returns a collection of Action that the otherActor can do to the Trader
     * @param otherActor the Actor that may be making transaction with Trader
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionList = new ActionList();
        Player player = (Player) otherActor;

        // Player buy weapon from trader
        actionList.add(new BuyWeaponAction(new Club()));
        actionList.add(new BuyWeaponAction(new GreatKnife()));
        actionList.add(new BuyWeaponAction(new Uchigatana()));
        // Player sell weapon to trader
        for (WeaponItem weapon : otherActor.getWeaponInventory()){
            actionList.add(new SellWeaponAction(weapon));
        }

        return actionList;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
