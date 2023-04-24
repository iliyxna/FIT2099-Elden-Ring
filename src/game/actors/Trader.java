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

//    // Price List for Items (non-weapon)
//    private Map<Item, Rune> buyItemRuneList = new HashMap<>();
//    private Map<Item,Rune> sellItemRuneList = new HashMap<>();
//
//    // Price List for WeaponItems
//    private Map<WeaponItem, Rune> buyWeaponRuneList = new HashMap<>();
//    private Map<WeaponItem,Rune> sellWeaponRuneList = new HashMap<>();
    private Location traderLocation;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        addCapability(Status.UNATTACKABLE);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map){
        ActionList actionList = new ActionList();
        Player player = (Player) otherActor;

        // Player buy weapon from trader
        actionList.add(new BuyWeaponAction(new Club()));
        actionList.add(new BuyWeaponAction(new GreatKnife()));
        actionList.add(new BuyWeaponAction(new Uchigatana()));
        // Player sell weapon to trader
        actionList.add(new SellWeaponAction(new Club()));
        actionList.add(new SellWeaponAction(new GreatKnife()));
        actionList.add(new SellWeaponAction(new Uchigatana()));
        actionList.add(new SellWeaponAction(new Grossmesser()));

        System.out.println("Player's rune value: " + player.getTotalRunes());
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
