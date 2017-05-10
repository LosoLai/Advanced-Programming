/**
 * 
 */
package JUnitTest;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Controller.Driver;
import Model.Athlete;
import Model.Official;
import Model.Swimmer;

/**
 * @author Arion
 *
 */
public class OfficialTest {

	String gameID, gameResult;
	ArrayList<Athlete> candidateList;
	Athlete swimmer1, swimmer2, swimmer3, swimmer4; 
	Official official;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		Driver driver = new Driver();
		}
	
	@Before
	public void setUp() throws Exception {
		swimmer1 = new Swimmer("swimmer1 id", "swimmer1 name", 20, "swimmer1 state");
		swimmer2 = new Swimmer("swimmer2 id", "swimmer2 name", 21, "swimmer2 state");
		swimmer3 = new Swimmer("swimmer3 id", "swimmer3 name", 22, "swimmer3 state");
		swimmer4 = new Swimmer("swimmer4 id", "swimmer4 name", 23, "swimmer4 state");
		official = new Official("official id", "official name", 24, "official state");
	}

	/**
	 * 
	 * Test setResultTopList method for null candidate list @throws Exception
	 */
	@Test
	public void testSetResultTopList1() throws Exception {
		candidateList = new ArrayList<Athlete>();
		gameID = "SwimmingID";
		gameResult = "\n========= " + gameID + " Results ==========\n";
		gameResult += "\n\nReferee: " + official.getName();
		assertEquals(gameResult,official.setResultTopList(gameID, candidateList));
	}

	/**
	 * 
	 * Test setResultTopList method for acceptable candidate list @throws Exception
	 */
	@Test
	public void testSetResultTopList2() throws Exception {
		Driver driver = new Driver();
		candidateList = new ArrayList<Athlete>();
		candidateList.add(swimmer1);
		candidateList.add(swimmer2);
		candidateList.add(swimmer3);
		candidateList.add(swimmer4);
		swimmer1.setExecuteTime(1);
		swimmer2.setExecuteTime(2);
		swimmer3.setExecuteTime(3);
		swimmer4.setExecuteTime(4);
		gameID = "SwimmingID";
		gameResult = "\n========= " + gameID + " Results ==========\n";
		gameResult += swimmer1.toString() + ", " + 5 + "\n";
		gameResult += swimmer1.toString() + ", " + 3 + "\n";
		gameResult += swimmer1.toString() + ", " + 1 + "\n";
		gameResult += swimmer1.toString() + ", " + 0 + "\n";
		gameResult += "\n\nReferee: " + official.getName();
		assertEquals(gameResult,official.setResultTopList(gameID, candidateList));
	}

}
