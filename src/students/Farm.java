package students;

import java.ulti.Scanner;

public class Farm {
	
	// initialise Field somehow..
	private Field field;
	// balance
	private int bankBalance;
	
	// initialise farm with appropriate size and player money
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.field = new Field(fieldHeight, fieldWidth);
		this.bankBalance = startingFunds;
	}
	
	// run the farm simulation
	public void run()
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println(field);
		System.out.println("Bank balance: $" + bankBalance);
		System.out.println("Enter your next action: ";
		
		
	}
	
}
