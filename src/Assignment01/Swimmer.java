package Assignment01;

/**Author: Arion
 * inheritance from Athlete
 * initializing the basic information through superclass constructor
 */
public class Swimmer extends Athlete {
	
	public Swimmer(String name, int age, String state)
	{
		super(name, age, state, SWIMMER);
	}
}
