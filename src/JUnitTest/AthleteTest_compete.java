package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Controller.Driver;
import Model.Athlete;
import Model.Game;
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
	
	
	@Before
	public void setUp() throws Exception {
		swimmer = new Athlete("swimmer id", "Swimmer", "","swimmer name", 20, "swimmer state");
		cyclist = new Athlete("cyclist id", "Cyclist", "","cyclist name", 20, "cyclist state");
		sprinter = new Athlete("sprinter id", "Sprinter", "","sprinter name", 20, "sprinter state");
		superath = new SuperAthlete("super id", "Super", "","super name", 20, "super state");
	}

	/**
	 * Test method for swimmer object type setting.
	 */
	@Test
	public void testType_Swimmer() {
		assertEquals("Swimmer", swimmer.getPersonType());
	}
	
	/**
	 * Test method for cyclist object type setting.
	 */
	@Test
	public void testType_Cyclist() {
		assertEquals("Cyclist", cyclist.getPersonType());
	}
	
	/**
	 * Test method for sprinter object type setting.
	 */
	@Test
	public void testType_Sprinter() {
		assertEquals("Runner", sprinter.getPersonType());
	}
	
	/**
	 * Test method for superathlete object type setting.
	 */
	@Test
	public void testType_Superath() {
		assertEquals("SuperAth", superath.getPersonType());
	}
	
	/**
	 * Test compete method for swimmer.
	 */
	@Test
	public void testCompete_Swimmer() {
		Driver.currentGame = new Game("Swimming");
		System.out.println("Swimmer time: " + swimmer.Compete());
		assertTrue(swimmer.Compete() > 100);
		assertTrue(swimmer.Compete() < 200);
	}
	
	/**
	 * Test compete method for cyclist.
	 */
	@Test
	public void testCompete_Cyclist() {
		Driver.currentGame = new Game("Cycling");
		System.out.println("Cyclist time: " + cyclist.getExecuteTime());
		assertTrue(cyclist.getExecuteTime() > 500);
		assertTrue(cyclist.getExecuteTime() < 800);
	}
	
	/**
	 * Test compete method for sprinter.
	 */
	@Test
	public void testCompete_Sprinter() {
		Driver.currentGame = new Game("Running");
		System.out.println("Sprinter time: " + sprinter.Compete());
		assertTrue(sprinter.Compete() > 10);
		assertTrue(sprinter.Compete() < 20);
	}
	
	

}
