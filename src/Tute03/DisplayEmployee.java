package Tute03;

public class DisplayEmployee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee e1 = new Employee("AAA", 22, "S1234");
		System.out.println("Employee detail:" + e1.getName() + " "
											  + e1.getAge() + " "
											  + e1.getId());
		e1.setName("BBB");
		e1.setAge(19);
		e1.setId("S2345");
		
		System.out.println("Employee detail:" + e1.getName() + " "
				  + e1.getAge() + " "
				  + e1.getId());
		
		Calculate cal = new Calculate(2.0, 3.0);
		System.out.println("Method Sum: " + cal.Sum() + "\n" +
						   "Method Sub: " + cal.Substract() + "\n" +
						   "Method Mul: " + cal.Multiply() + "\n" +
						   "Method Div: " + cal.Divide() + "\n" +
						   "Method Exp: " + cal.Exponent() );
	}
}
