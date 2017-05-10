/**
 * 
 */
package JUnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Model.Athlete;
import Model.Game;
import Model.Official;
import Model.Swimmer;

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
		swimmer1 = new Swimmer("swimmer1 id", "swimmer1 name", 20, "swimmer1 state");
		swimmer2 = new Swimmer("swimmer2 id", "swimmer2 name", 21, "swimmer2 state");
		swimmer3 = new Swimmer("swimmer3 id", "swimmer3 name", 22, "swimmer3 state");
		swimmer4 = new Swimmer("swimmer4 id", "swimmer4 name", 23, "swimmer4 state");
		official = new Official("official id", "official name", 24, "official state");
		game = new Game("Swimming");
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
		game.addCandidate(swimmer1);
		game.addCandidate(swimmer2);
		game.addCandidate(swimmer3);
		game.addCandidate(swimmer4);
		assertEquals(candidateList,game.getCandidate());
	}

	/**
	 * Test setReferee method
	 */
	@Test
	public void testSetReferee() {
		game.setReferee(official);
		assertEquals(official,game.getReferee());
	}
	
	/**
	 * Test executeGame method with null candidateList and null referee
	 */
	@Test
	public void testExecuteGame1() {
		assertFalse(game.executeGame());
	}
	
	/**
	 * Test executeGame method with null candidateList
	 */
	@Test
	public void testExecuteGame2() {
		game.setReferee(official);
		assertFalse(game.executeGame());
	}
	
	/**
	 * Test executeGame method with null referee
	 */
	@Test
	public void testExecuteGame3() {
		game.addCandidate(swimmer1);
		game.addCandidate(swimmer2);
		game.addCandidate(swimmer3);
		game.addCandidate(swimmer4);
		assertFalse(game.executeGame());
	}
	
	/**
	 * Test executeGame method with correct candidateList and referee
	 */
	@Test
	public void testExecuteGame4() {
		game.addCandidate(swimmer1);
		game.addCandidate(swimmer2);
		game.addCandidate(swimmer3);
		game.addCandidate(swimmer4);
		game.setReferee(official);
		assertTrue(game.executeGame());
	}

}
