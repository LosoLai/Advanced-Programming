package Tute04;

public class Truck extends Vehicle {
	static private double taxRate = 0.2;
	public int quantity;
	public Truck(double price) {
		super(price);
		quantity = 0;
	}
	
	static public void setTaxRate(double newRate) {
		taxRate = newRate;
	}
	public double sell(int quantity){
		this.quantity = quantity;
		return price * quantity * (1 + taxRate);
	}
}
