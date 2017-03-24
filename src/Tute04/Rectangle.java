package Tute04;

public class Rectangle extends Shape {
	public Rectangle(double width, double height)
	{
		super(width, height);
	}
	
	public double getArea()
	{
		return super.getWidth() * super.getHeight();
	}
	public void paint()
	{
		System.out.println("Retangle Area: " + getArea());
	}
}
