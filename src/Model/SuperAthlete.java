package Model;

/**Author: Arion
 * inheritance from Athlete
 * initializing the basic information through superclass constructor
 */
public class SuperAthlete extends Athlete {
	//Modified by Loso 14/05/17----------------------------------------
	public SuperAthlete(String id, String athleteType, String extraType, String name, int age, String state)
	{
		//assume the table schema should be read/write personType at first
		super(id, SUPERATHLETE, "", name, age, state);
	}
	//-----------------------------------------------------------------
}
