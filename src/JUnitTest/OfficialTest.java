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

/**
 * @author Arion
 *
 */
public class OfficialTest {

	String gameID, gameResult;
	ArrayList<Athlete> candidateList;
	Athlete swimmer1, swimmer2, swimmer3, swimmer4; 
	Official official;
	
	
	@Before
	public void setUp() throws Exception {
		swimmer1 = new Athlete("swimmer1 id", "Swimmer", "","swimmer1 name", 20, "swimmer1 state");
		swimmer2 = new Athlete("swimmer2 id", "Swimmer", "","swimmer2 name", 21, "swimmer2 state");
		swimmer3 = new Athlete("swimmer3 id", "Swimmer", "","swimmer3 name", 22, "swimmer3 state");
		swimmer4 = new Athlete("swimmer4 id", "Swimmer", "","swimmer4 name", 23, "swimmer4 state");
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
