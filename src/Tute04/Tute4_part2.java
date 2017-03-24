package Tute04;

public class Tute4_part2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] list = new Object[4];
		list[0] = new Rectangle(5.0, 4.0);
		list[1] = new Child("Loso", 33, "AA+BB");
		list[2] = new Child("OOOO", 22, "CC+DD");
		list[3] = new House("88 slamon street", 6789.00);
		
		
		for(int i=0 ; i<list.length; i++)
		{
			if(list[i] instanceof Rectangle)
			{
				
				Rectangle rec = (Rectangle)list[i];
				rec.paint();
			}
			if(list[i] instanceof Child)
			{
				
				Child ch = (Child)list[i];
				ch.paint();
			}
			if(list[i] instanceof House)
			{
				
				House house = (House)list[i];
				house.paint();
			}
		}	
	}

}
