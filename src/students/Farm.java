package students;

import java.ulti.Scanner;

public class Farm {
	
	// initialise Field 
	private Field field;
	// balance
	private int bankBalance;
	Scanner scanner = new Scanner(System.in);
	
	// initialise farm with appropriate size and player money
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.field = new Field(fieldHeight, fieldWidth);
		this.bankBalance = startingFunds;
	}
	// run the farm simulation
	public void run() {
		
		// display field
		System.out.println(field);
		System.out.println("Bank balance: $" + bankBalance);
		System.out.println("Enter your next action: "
				+ "t x y: till"
				+ "h x y: harvest"
				+ "p x y: plant"
				+ "s: field summary"
				+ "w: wait"
				+ "q: quit");
		
		String input = scanner.nextLine();
		if ("q".equals(input)) 
			break; 
		
		//separate inputs as individual items(coordinates) when spaces exist
		String[] cells = input.split(" ");
		int x, y;
		
		
		// actions - try, switch, case for options
		try {
			switch ([0]) {
				// till
				case "t":
				x = ;
				y = ;

					break;
				// harvest
				case "h"
				x = ;
				y = ;
					break;
				
				// plant
				case "p"
					break;
					
				// summary
				case "w"
					System.out.println(field.getSummary());
					break;
				
				// wait
				case "w"
					break;
				
				default:
					System.out.println("Error. Try again.");
					break;
				
			}
			
			
		}
		
		//should add exceptions for invalid field coordinates
	}
	
}
