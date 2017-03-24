package Tute04;

public class Tute4 {
	public static void main(String[] args) {
		Vehicle[] list = new Vehicle[4];
		list[0] = new Car("Corolla", 20);
		list[1] = new Vehicle(50);
		list[2] = new Car("BMW", 40);
		//list[3] = new Track(100);
		list[3] = new Truck(100.0);
		int[] sold = { 5, 4, 3, 1 };
		double total = 0;
		for (int i = 0; i < 4; i++) 
		{
			double soldprice = list[i].sell(sold[i]);
			total += soldprice;
			if (list[i] instanceof Car) {
				Car soldCar = (Car) list[i];
				System.out.println("Cars sold so far:"
									+ soldCar.getNumSold() /*+ 
									"   Price : " + soldCar.price*/);
			}
		}
		
		System.out.println("The total amount is " + total);
	}
		
}
