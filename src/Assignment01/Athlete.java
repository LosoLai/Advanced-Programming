package Assignment01;

/**Author: Arion
 * Athlete inheritance from Participant class 
 * multi-inheritance interface : Competable & Comparable
 */
public class Athlete extends Participant implements Competable, Comparable<Athlete>{
	
	//Extra variables for recording compete time and points
	private double executeTime;
	private int points;
	
	public Athlete(String id, String athleteType, String name, int age, String state)
	{
		super(id, athleteType, name, age, state);
		executeTime = 0;
		points = 0;
	}

	public double getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(double executeTime) {
		this.executeTime = executeTime;
	}
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points += points;
	}
	
	/* Overridden method from Competable
	 * Gets the compete time by current game type
	 */
	public double Compete()
	{
		double competeSec = Driver.currentGame.generateTime(); 
		return competeSec;
	}
	
	/* Overridden method from Comparable
	 * For sorting purposes
	 */
	public int compareTo(Athlete comparePerson)
	{
		double compareTime = comparePerson.getExecuteTime();
		return Double.compare(this.executeTime, compareTime);
	}
	
	@Override
    public String toString() 
	{
        return String.format("\n" + super.getName() +
        					 "-> " + this.getExecuteTime());
    }
}
