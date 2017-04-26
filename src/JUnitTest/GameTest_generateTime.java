/**
 * 
 */
package JUnitTest;

import static org.junit.Assert.*;
import Model.Game;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Loso
 *
 */
public class GameTest_generateTime {
	Game swimming;
	Game cycling;
	Game running;
	
	int min;
	int max;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		swimming = new Game("Swimming");
		cycling = new Game("Cycling");
		running = new Game("Running");
	}
	
	/**
	 * Test method for swimming game time limitation.
	 */
	@Test
	public void testTimeLimitation_Swimming() {
		min = swimming.S_TIMELIMIT_MIN;
		max = swimming.S_TIMELIMIT_MAX;
		assertEquals(100, min);
		assertEquals(200, max);
	}
	
	/**
	 * Test method for {@link Model.Game#generateTime()}.
	 */
	@Test
	public void testGenerateTime_Swimming() {
		double time = swimming.generateTime();
		System.out.println("Swimming Time: " + time);
		assertTrue(time > swimming.S_TIMELIMIT_MIN);
		assertTrue(time < swimming.S_TIMELIMIT_MAX);
	}
	
	/**
	 * Test method for cycling game time limitation.
	 */
	@Test
	public void testTimeLimitation_Cycing() {
		min = cycling.C_TIMELIMIT_MIN;
		max = cycling.C_TIMELIMIT_MAX;
		assertEquals(500, min);
		assertEquals(800, max);
	}
	
	/**
	 * Test method for {@link Model.Game#generateTime()}.
	 */
	@Test
	public void testGenerateTime_Cycling() {
		double time = cycling.generateTime();
		System.out.println("Cycling Time: " + time);
		assertTrue(time > cycling.C_TIMELIMIT_MIN);
		assertTrue(time < cycling.C_TIMELIMIT_MAX);
	}
	
	/**
	 * Test method for running game time limitation.
	 */
	@Test
	public void testTimeLimitation_Running() {
		min = running.R_TIMELIMIT_MIN;
		max = running.R_TIMELIMIT_MAX;
		assertEquals(10, min);
		assertEquals(20, max);
	}
	
	/**
	 * Test method for {@link Model.Game#generateTime()}.
	 */
	@Test
	public void testGenerateTime_Running() {
		double time = running.generateTime();
		System.out.println("Running Time: " + time);
		assertTrue(time > running.R_TIMELIMIT_MIN);
		assertTrue(time < running.R_TIMELIMIT_MAX);
	}
}
