package Tute03;

public class Car {
	int maxSpeed;
	double regularPrice;
	String color;
	double salePrice; //= getSalePrice(); // 4% off the regular price
	
	public void setSpeed(int maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}
	public int getSpeed()
	{
		return this.maxSpeed;
	}
	public void setRegularPrice(double regularPrice)
	{
		this.regularPrice = regularPrice;
	}
	public double getRegularPrice()
	{
		return this.regularPrice;
	}
	public double getSalePrice()
	{
		salePrice = this.regularPrice * 0.04;
		return salePrice;
	}
	public void setColor(String color)
	{
		this.color = color;
	}
	public String getColor()
	{
		return this.color;
	}
}
