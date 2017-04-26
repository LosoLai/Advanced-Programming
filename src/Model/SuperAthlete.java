package Model;

/**Author: Arion
 * inheritance from Athlete
 * initializing the basic information through superclass constructor
 */
public class SuperAthlete extends Athlete {
		
	public SuperAthlete(String name, int age, String state)
	{
		super(name, age, state, SUPERATHLETE);
	}
}
