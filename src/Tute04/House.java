package Tute04;

public class House implements Paint {
	private String address;
	private double price;
	
	public House(String address, double price)
	{
		this.address = address;
		this.price = price;
	}
	
	public void paint()
	{
		System.out.println("House address: " + address);
	}
}
