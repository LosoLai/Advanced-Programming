package Tute04;

public class Child extends Person {
	private String parantName;
	public Child(String name, int age, String parantName)
	{
		super(name, age);
		this.parantName = parantName;
	}
	
	public void paint()
	{
		System.out.println("Child info. " + super.getName() +
						 "     parantName : " + parantName);
	}
}
