package Model;

/**Author: Arion    
 */
public class Participant {
	
	public static final String CYCLIST = "Cyclist";
	public static final String SPRINTER = "Sprinter";
	public static final String SWIMMER = "Swimmer";
	public static final String SUPERATHLETE  = "Super";
	public static final String OFFICIAL = "Official";
	public static final String ATHLETE  = "Athlete";
	
	private String name;
	private int age;
	private String state;
	private String id;
	private String personType;
	
	
	//constructor for initial variables
	public Participant(String id, String personType, String name, int age, String state)
	{
		setPersonID(id);
		setName(name);
		setAge(age);
		setState(state);
		setPersonType(personType);
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
