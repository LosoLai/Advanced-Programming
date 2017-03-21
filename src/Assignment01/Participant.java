package Assignment01;

public abstract class Participant {
	private String name;
	private int    age;
	private String state;
	private String id;
	private String personType;
	
	protected static int secquenceID = 0;
	
	public Participant(String name, int age, String state, String personType)
	{
		secquenceID++;
		
		this.name       = name;
		this.age        = age;
		this.state      = state;
		this.id         = Integer.toString(secquenceID);
		this.personType = personType;
	}
}
