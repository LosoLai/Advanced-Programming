package Tute03;

public class Calculate {
	private final double number1;
	private final double number2;
	
	public Calculate(double num1, double num2)
	{
		this.number1 = num1;
		this.number2 = num2;
	}
	
	public double Sum()
	{
		return number1 + number2;
	}
	public double Substract()
	{
		return number1 - number2;
	}
	public double Multiply()
	{
		return number1 * number2;
	}
	public double Divide()
	{
		if(number2 == 0)
			return 0.0;
		return number1 / number2;
	}
	public double Exponent()
	{
		return Math.pow(number1, number2);
	}
}
