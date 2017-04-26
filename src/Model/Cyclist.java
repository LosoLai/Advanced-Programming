package Model;

/**Author: Arion
 * inheritance from Athlete
 * initializes the basic information through superclass constructor
 */
public class Cyclist extends Athlete {
	
	public Cyclist(String name, int age, String state)
	{
		super(name, age, state, CYCLIST);
	}
}
