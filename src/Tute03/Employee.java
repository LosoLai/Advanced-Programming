package Tute03;

public class Employee {
	private String name;
	private int    age;
	private String id;
	
	public Employee()
	{
		
	}
	public Employee(String name, int age, String id)
	{
		this.name = name;
		this.age  = age;
		this.id   = id;
	}
	
	public String getName()
	{
		return this.name;
	}
	public String getId()
	{
		return this.id;
	}
	public int getAge()
	{
		return this.age;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public void setId(String id)
	{
		this.id = id;
	}
}
