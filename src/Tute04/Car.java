package Tute04;

public class Car extends Vehicle {
	static private int numSold;
	private String name;
	public Car(String name, double price) {
		super(price);
		this.name = name;
	}
	
	public double sell(int quantity) {
		numSold += quantity;
		return super.sell(quantity);
	}
	public int getNumSold(){
		System.out.println("The sold car is " + name);
		return numSold;
	}
}
