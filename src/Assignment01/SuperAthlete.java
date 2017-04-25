package Assignment01;

/**Author: Arion
 * inheritance from Athlete
 * initializing the basic information through superclass constructor
 */
public class SuperAthlete extends Athlete {
		
	public SuperAthlete(String id, String name, int age, String state)
	{
		super(id, SUPERATHLETE, name, age, state);
	}
}
