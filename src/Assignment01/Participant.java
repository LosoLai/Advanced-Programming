package Assignment01;

/**Author: Arion    
 */
public class Participant {
	
	public static final String CYCLIST = "Cyclist ";
	public static final String SPRINTER = "Runner  ";
	public static final String SWIMMER = "Swimmer ";
	public static final String SUPERATHLETE  = "SuperAth";
	public static final String OFFICIAL = "Official";
	
	private String name;
	private int age;
	private String state;
	private String id;
	private String personType;
	
	//static variable for counting the game round number
	private static int secquenceID = 0;
	
	//constructor for initial variables
	public Participant(String name, int age, String state, String personType)
	{
		secquenceID++;
		
		this.name = name;
		this.age = age;
		this.state = state;
		this.id = personType + Integer.toString(secquenceID);
		this.personType = personType;
	}
	
	//getter-setters
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setPersonID(String id)
	{
		this.id = id;
	}
	
	public String getPersonID()
	{
		return this.id;
	}
	public void setPersonType(String personType)
	{
		this.personType = personType;
	}
	
	public String getPersonType()
	{
		return this.personType;
	}
	
}
