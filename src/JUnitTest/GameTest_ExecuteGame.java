/**
 * 
 */
package JUnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Controller.Driver;
import Model.Athlete;
import Model.Game;
import Model.Official;

/**
 * @author Arion
 *
 */
public class GameTest_ExecuteGame {

	Athlete swimmer1, swimmer2, swimmer3, swimmer4;
	Official official;
	ArrayList<Athlete> candidateList;
	
	Game game;
	
	@Before
	public void setUp() throws Exception {
		swimmer1 = new Athlete("swimmer1 id", "Swimmer", "","swimmer1 name", 20, "swimmer1 state");
		swimmer2 = new Athlete("swimmer2 id", "Swimmer", "","swimmer2 name", 21, "swimmer2 state");
		swimmer3 = new Athlete("swimmer3 id", "Swimmer", "","swimmer3 name", 22, "swimmer3 state");
		swimmer4 = new Athlete("swimmer4 id", "Swimmer", "","swimmer4 name", 23, "swimmer4 state");
		official = new Official("official id", "official name", 24, "official state");
		Driver.currentGame = new Game("Swimming");
	}

	/**
	 * Test addCandidate method
	 */
	@Test
	public void testAddCandidate() {
		candidateList = new ArrayList<Athlete>();
		
		candidateList.add(swimmer1);
		candidateList.add(swimmer2);
		candidateList.add(swimmer3);
		candidateList.add(swimmer4);
		Driver.currentGame.addCandidate(swimmer1);
		Driver.currentGame.addCandidate(swimmer2);
		Driver.currentGame.addCandidate(swimmer3);
		Driver.currentGame.addCandidate(swimmer4);
		assertEquals(candidateList,Driver.currentGame.getCandidate());
	}

	/**
	 * Test setReferee method
	 */
	@Test
	public void testSetReferee() {
		Driver.currentGame.setReferee(official);
		assertEquals(official,Driver.currentGame.getReferee());
	}
	
	/**
	 * Test executeGame method with null candidateList and null referee
	 */
	@Test
	public void testExecuteGame1() {
		assertFalse(Driver.currentGame.executeGame());
	}
	
	/**
	 * Test executeGame method with null candidateList
	 */
	@Test
	public void testExecuteGame2() {
		Driver.currentGame.setReferee(official);
		assertFalse(Driver.currentGame.executeGame());
	}
	
	/**
	 * Test executeGame method with null referee
	 */
	@Test
	public void testExecuteGame3() {
		Driver.currentGame.addCandidate(swimmer1);
		Driver.currentGame.addCandidate(swimmer2);
		Driver.currentGame.addCandidate(swimmer3);
		Driver.currentGame.addCandidate(swimmer4);
		assertFalse(Driver.currentGame.executeGame());
	}
	
	/**
	 * Test executeGame method with correct candidateList and referee
	 */
	@Test
	public void testExecuteGame4() {
		Driver.currentGame.addCandidate(swimmer1);
		Driver.currentGame.addCandidate(swimmer2);
		Driver.currentGame.addCandidate(swimmer3);
		Driver.currentGame.addCandidate(swimmer4);
		Driver.currentGame.setReferee(official);
		assertTrue(Driver.currentGame.executeGame());
	}

}
