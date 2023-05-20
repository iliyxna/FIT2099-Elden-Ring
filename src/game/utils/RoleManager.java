package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import game.weapons.AstrologerStaff;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Uchigatana;

import java.util.Scanner;

/**
 * A singleton role manager class that prints a menu item to the console
 * for players to choose their player classes. (e.g. Samurai, Bandit, Wretch)
 */
public class RoleManager {
    /**
     * A singleton role manager instance
     */
    private static RoleManager roleManagerIns =  null;

    /**
     * Private constructor
     */
    private RoleManager(){}

    /**
     * Get the singleton instance of the role manager
     * @return RoleManager singleton instance
     */
    public static RoleManager getInstance(){
        if (roleManagerIns == null ){
            roleManagerIns = new RoleManager();
        }
        return roleManagerIns;
    }

    /**
     * A method that prints a menu item to the console for players
     * to choose their starting classes.
     * @return the choice of the player
     */
    public int menuItem() {
        System.out.println("Please choose your starting class \uD83E\uDD3A️");
        Scanner sel = new Scanner(System.in);
        System.out.println("1. Samurai Class (Starting weapon: Uchigatana, HP: 455)");
        System.out.println("2. Bandit Class (Starting weapon: Great Knife, HP: 414)");
        System.out.println("3. Wretch Class (Starting weapon: Club, HP: 414)");
        System.out.println("4. Astrologer Class (Starting weapon: Astrologer's Staff, HP: 396)");
        int choice = 0;

        boolean isValidChoice = false;
        while (!isValidChoice) {
            try {
                choice = Integer.parseInt(sel.nextLine());
                if (choice < 1 || choice > 4) {
                    throw new IllegalArgumentException("Invalid choice. Please enter a number between 1 and 4.");
                }
                isValidChoice = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return choice;
    }

    /**
     * A class that grants player with different starting HP and starting weapon depending on the
     * choice of players.
     * @param actor player
     * @param selection selection of player
     */
    public void selectedClass(Actor actor, int selection){

        switch (selection) {
            case 1 -> {
                actor.addWeaponToInventory(new Uchigatana());
                actor.resetMaxHp(455);
            }
            case 2 -> {
                actor.addWeaponToInventory(new GreatKnife());
                actor.resetMaxHp(414);
            }
            case 3 -> {
                actor.addWeaponToInventory(new Club());
                actor.resetMaxHp(414);
            }
            case 4 -> {
                actor.addWeaponToInventory(new AstrologerStaff());
                actor.resetMaxHp(396);
            }
        }
    }
}
