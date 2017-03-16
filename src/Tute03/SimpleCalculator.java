package Tute03;

import java.util.Scanner;

public class SimpleCalculator {
	private int uerInput;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		double number1 = 0.0;
		double number2 = 0.0;
		System.out.print("Please enter a number for number1:");
		number1 = reader.nextDouble();
		System.out.print("Please enter a number for number2:");
		number2 = reader.nextDouble();
		
		boolean bGameEnd = false;
		do{
			//display menu
			/*menu();		
			
			// deal with exception
			boolean bException = false;
			String sInput = reader.next();
			try
			{
				//get user input
				userInput = Integer.parseInt(sInput);
			}
			catch(Exception e){
				bException = showUserChoiceWarning(sInput);
			}
			
			if(bException)
				continue;
			
			boolean bValidate = inputValifation(sInput, userInput);
			if(!bValidate)
				continue;*/
			
		
		}while(!bGameEnd);
	}
}
