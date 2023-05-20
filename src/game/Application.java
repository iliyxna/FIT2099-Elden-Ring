package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.environments.*;
import game.items.GoldenRunes;
import game.utils.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Iliyana, Damia, Mustafa
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(),
				new Water(), new Wind(), new SiteOfLostGrace(), new Barrack(), new Cage(), new Summon(), new Cliff());

		// Map example from req 1
		List<String> limgraveMap = Arrays.asList(
				"......................#.............#..........................+++.........",
				"......................#.............#.......................+++++..........",
				"......................#..___....____#.........................+++++........",
				"......................#...........__#............................++........",
				"......................#_____........#.............................+++......",
				"......................#............_#..............................+++.....",
				"......................######...######......................................",
				"...........................................................................",
				"...........................=...............................................",
				"........++++......................###___###................................",
				"........+++++++...................________#....___.........................",
				"..........+++.....................#_______=....___.........................",
				"............+++...................#_______#....___.........................",
				".............+....................###___###................................",
				"............++......................#___#..................................",
				"..............+...................=........................................",
				"..............++.................................................=.........",
				"..............................................++...........................",
				"..................++++......................+++...............######..##...",
				"#####___######....++...........................+++............#....____....",
				"_____________#.....++++..........................+..............__.....#...",
				"_____________#.....+....++........................++.........._.....__.#...",
				"_____________#.........+..+.....................+++...........###..__###...",
				"_____________#.............++..............................................");

		List<String> stormveilCastleMap = Arrays.asList(
				"...........................................................................",
				".___..............<...............<........................................",
				".___.......................................................................",
				"##############################################...##########################",
				"............................#................#.......B..............B......",
				".....B...............B......#................#.............................",
				"...............................<.........<.................................",
				".....B...............B......#................#.......B..............B......",
				"............................#................#.............................",
				"#####################..#############...############.####..#########...#####",
				"...............#++++++++++++#................#++++++++++++#................",
				"...............#++++++++++++...<.........<...#++++++++++++#................",
				"...............#++++++++++++..................++++++++++++#................",
				"...............#++++++++++++#................#++++++++++++#................",
				"#####...##########.....#############...#############..#############...#####",
				".._______........................B......B........................B.....B...",
				"_____..._..____....&&........<..............<..............................",
				".........____......&&......................................................",
				"...._______..................<..............<....................<.....<...",
				"#####....##...###..#####...##########___###############......##.....####...",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++....................#+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#....................+++++++++++++++++++++++++++",
				"+++++++++++++++++++++++++++#...................#+++++++++++++++++++++++++++");

		List<String> roundtableHoldMap = Arrays.asList(
				"##################",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"#________________#",
				"########___#######"
		);

		List<String> bossRoomMap = Arrays.asList(
				"+++++++++++++++++++++++++",
				".........................",
				"..=......................",
				".........................",
				".........................",
				".........................",
				".........................",
				".........................",
				"+++++++++++++++++++++++++");

		// Limgrave
		GameMap limgrave = new GameMap(groundFactory, limgraveMap);
		world.addGameMap(limgrave);

		// Stormveil Castle
		GameMap stormveilCastle = new GameMap(groundFactory, stormveilCastleMap);
		world.addGameMap(stormveilCastle);

		// Roundtable Hold
		GameMap roundtableHold  = new GameMap(groundFactory, roundtableHoldMap);
		world.addGameMap(roundtableHold);

		// Boss Room
		GameMap bossRoom = new GameMap(groundFactory, bossRoomMap);
		world.addGameMap(bossRoom);

		// BEHOLD, ELDEN RING
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// HINT: what does it mean to prefer composition to inheritance?
		Player player = new Player();
		world.addPlayer(player, limgrave.at(39, 9));
		limgrave.at(38,8).addActor(new GiantCrab());

		// Activate the first site of lost grace
		Location TheFirstStep = new Location(limgrave,38,9);
		limgrave.at(TheFirstStep.x(), TheFirstStep.y()).setGround(new SiteOfLostGrace());
		player.setLastSiteOfLostGrace(TheFirstStep);
		player.setMapOfLastLostGrace(limgrave);

		// Add doors
		// Limgrave -> Castle
		GoldenFogDoor limgraveCastleDoor = new GoldenFogDoor();
		limgrave.at(35,11).setGround(limgraveCastleDoor);
		limgraveCastleDoor.addDestination("to Stormveil Castle", stormveilCastle.at(2,2));

		// Castle -> Limgrave
		GoldenFogDoor stormveilLimgraveDoor = new GoldenFogDoor();
		stormveilCastle.at(2,2).setGround(stormveilLimgraveDoor);
		stormveilLimgraveDoor.addDestination("to Limgrave", limgrave.at(35,11));

		// Limgrave -> Roundtable Hold
		GoldenFogDoor limgraveRTDoor = new GoldenFogDoor();
		limgrave.at(48,11).setGround(limgraveRTDoor);
		limgraveRTDoor.addDestination("to Roundtable Hold", roundtableHold.at(9,1));

		GoldenFogDoor rtLimgraveDoor = new GoldenFogDoor();
		roundtableHold.at(9,1).setGround(rtLimgraveDoor);
		rtLimgraveDoor.addDestination("to Limgrave", limgrave.at(48,11));

		// Castle -> Boss Room
		GoldenFogDoor stormveilBossDoor = new GoldenFogDoor();
		stormveilCastle.at(5,2).setGround(stormveilBossDoor);
		stormveilBossDoor.addDestination("to Boss Room", bossRoom.at(1,1));	// Bossroom got no door



		world.run();
	}

}