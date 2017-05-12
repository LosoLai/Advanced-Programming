package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Controller.Driver;
import Model.Athlete;
import Model.Game;
import Model.Swimmer;
import Model.Cyclist;
import Model.Sprinter;
import Model.SuperAthlete;

/**
 * @author Arion
 *
 */
public class AthleteTest_compete {

	Athlete swimmer;
	Athlete cyclist;
	Athlete sprinter;
	Athlete superath;
	Game game;
	String type;
	double time;
	
	
	@Before
	public void setUp() throws Exception {
		swimmer = new Swimmer("swimmer id", "swimmer name", 20, "swimmer state");
		cyclist = new Cyclist("cyclist id", "cyclist name", 20, "cyclist state");
		sprinter = new Sprinter("sprinter id", "sprinter name", 20, "sprinter state");
		superath = new SuperAthlete("super id", "super name", 20, "super state");
	}

	/**
	 * Test method for swimmer object type setting.
	 */
	@Test
	public void testType_Swimmer() {
		type = swimmer.SWIMMER;
		assertEquals("Swimmer",type);
	}
	
	/**
	 * Test method for cyclist object type setting.
	 */
	@Test
	public void testType_Cyclist() {
		type = cyclist.CYCLIST;
		assertEquals("Cyclist",type);
	}
	
	/**
	 * Test method for sprinter object type setting.
	 */
	@Test
	public void testType_Sprinter() {
		type = sprinter.SPRINTER;
		assertEquals("Runner",type);
	}
	
	/**
	 * Test method for superathlete object type setting.
	 */
	@Test
	public void testType_Superath() {
		type = superath.SUPERATHLETE;
		assertEquals("SuperAth",type);
	}
	
	/**
	 * Test compete method for swimmer.
	 */
	@Test
	public void testCompete_Swimmer() {
		Driver.currentGame = new Game("Swimming");
		time = swimmer.Compete();
		System.out.println("Swimmer time: " + time);
		assertTrue(time > 100);
		assertTrue(time < 200);
	}
	
	/**
	 * Test compete method for cyclist.
	 */
	@Test
	public void testCompete_Cyclist() {
		Driver.currentGame = new Game("Cycling");
		time = cyclist.Compete();
		System.out.println("Cyclist time: " + time);
		assertTrue(time > 500);
		assertTrue(time < 800);
	}
	
	/**
	 * Test compete method for sprinter.
	 */
	@Test
	public void testCompete_Sprinter() {
		Driver.currentGame = new Game("Running");
		time = sprinter.Compete();
		System.out.println("Sprinter time: " + time);
		assertTrue(time > 10);
		assertTrue(time < 20);
	}
	
	

}
