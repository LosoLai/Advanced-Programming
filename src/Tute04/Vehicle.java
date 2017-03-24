package Tute04;

public class Vehicle {
	public double price;
	public Vehicle(double price) { // price in K
		this.price = price * 1000;
	}
	public double sell(int quantity)
	{
		return this.price * quantity;
	}
}
