package game.actors;

import java.util.Scanner;

public class RoleManager {
    private static RoleManager roleManagerIns =  null;

    private RoleManager(){}

    public static RoleManager getInstance(){
        if (roleManagerIns == null ){
            roleManagerIns = new RoleManager();
        }
        return roleManagerIns;
    }
    public int menuItem() {
        System.out.println("Please choose your starting class \uD83E\uDD3A️");
        Scanner sel = new Scanner(System.in);
        System.out.println("1. Samurai Class (Starting weapon: Uchigatana, HP: 455)");
        System.out.println("2. Bandit Class (Starting weapon: Great Knife, HP: 414)");
        System.out.println("3. Wretch Class (Starting weapon: Club, HP: 414)");
        int choice = Integer.parseInt(sel.nextLine());
        return choice;
    }
}
