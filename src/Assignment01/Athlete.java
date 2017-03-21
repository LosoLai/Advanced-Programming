package Assignment01;

public class Athlete extends Participant {
	public static final String ATHLETE = "A"; 
	public static final int SWIMMER      = 0;
	public static final int CYCLIST      = 1;
	public static final int SPRINTER     = 2;
	public static final int SUPERATHLETE = 3;
	
	private int athleteType;
	private double points;
	
	public Athlete(String name, int age, String state, int athleteType)
	{
		super(name, age, state, ATHLETE);
		this.athleteType = athleteType;
		this.setPoints(0.0);
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
	
}
