package Model;

/**Author: Arion
 * inheritance from Athlete
 * initializes the basic information through superclass constructor
 */
public class Cyclist extends Athlete {
	
	public Cyclist(String id, String name, int age, String state)
	{
		super(id, CYCLIST, name, age, state);
	}
}
