package students;

import java.util.Scanner;

import students.items.Apples;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;
import students.Field;

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
	
	public void harvest(int x, int y) {
		// return item or null
		Item item = field.get(x, y);
		// check if food item is mature
		if (item != null && item.getAge() >= item.getMaturAge()) {
			// add item value to bankBalance
			bankBalance += item.getValue();
			//adds Soil item to this cell
			field.plant(x, y, new Soil());
			System.out.println("Item has been harvested!\n");
		} else {
			System.out.println("Cannot be harvested.\n");
		}
	}
	
	public void toWait() {
		 field.tick();
		 System.out.println("And so you wait...\n");
	}
	
	public void farmingOptions() {
		while (true) {
			System.out.println("\n Enter action then"
					+ "\n enter the location on field."
					+ "\n eg. p 1(row) 2(column)\n"
					+ "Farm\n"
					+ "| []t x y: till\n"
					+ "| [h ] harvest\n"
					+ "| [p ] plant\n"
					+ "| [pw] pull weed\n"
					+ "| [fs] field summary\n"
					+ "| [b ] Return to main menu");
			
			String input = scanner.nextLine();
			String[] cells = input.split("");
			int x, y;
			
			switch (cells[0]) {
				// till untilled soil
				case "t":
					// convert str into int and correctly access the right element by -1
					// correctly map zero-based indexing system
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;
					// till this location
					field.till(x, y); 
					break;
				//harvest
				case "h":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;
					harvest(x, y);
					break;
					 //wait
				case "w":
					toWait();
					break;
					// pull weed
				case "pw":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;
						field.pull(x, y);
					break;
				//plant
				case "p":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;					
				
						Item isSoil = field.get(x, y);
						// checking soil instance
						if (isSoil instanceof Soil) {
							System.out.print("Enter\n"
									+ "- 'a' to buy an apple for $" + Apples.cost + "\n"
									+ "- 'g' to buy grain for $" + Grain.cost + "\n");
							// listens for next line of string
							String option = scanner.nextLine();
							
							switch(option) {
							//plant apple
							case "a":
								if(bankBalance >= Apples.cost) {
									field.plant(x, y, new Apples());
									bankBalance -= Apples.cost;
									System.out.println("Apple has been planted!\n");
									break;
								} else {
									System.out.println("Sorry, not enough funds :(\n");
								}
							break;
						// plant grain
						case "g":
							if(bankBalance >= Grain.cost) {
								field.plant(x, y, new Grain());
								bankBalance -= Grain.cost;
								System.out.println("Grain has been planted!\n");
								break;
							} else {
								System.out.println("Sorry, not enough funds :(\n");
							}
							break;
							}
						}
					}
				}
			}
	// run the farm simulation
	public void run() {
		System.out.println("Congratulations!\n"
				+ "You've inherited your\n"
				+ "grandfather's farm.\n"
				+ "\nGive your farm a name:");
		
		String farmName = scanner.nextLine();
		
		System.out.print(farmName + ", what a wonderful\n"
				+ "name!\n");
		
		while(true) {
		
			// display field
			System.out.println("\n--------------------------------\n"
					+ "| " + farmName + " Farm"
					+ "\n--------------------------------\n"
					+ "\n" + field
					+"\n--------------------------------\n"
					+ "| Bank balance: $" + bankBalance
					+ "\n--------------------------------\n"
					+ "\nEnter action [...]\n"

					+ "\n--> [f ] Farm"
					+ "\n--> [s ] Shop "
					+ "\n--> [r ] Rest "
					+ "\n--> [fs] Field Summary"
					+ "\n--> [q ] Quit");
		
			String input = scanner.nextLine();
			String[] cells = input.split(" ");
			if ("q".equals(input)) break; 

			// actions - try, switch, case for options
			try {
				switch (cells[0]) {
					case "f":
						farmingOptions();
						break;
					case "fs":
						System.out.println(field.getSummary());
					case "s":
						enterShop();
						break;
					case "r":
						rest();
						break;
					case "q":
						System.out.println("Quiting...");
						return;
					default:
						System.out.println("Invalid action. Please try again.\n");		
				}
			} catch (Exception e) {
				System.out.print("Error occured. Please try again.");
			}
			
		}
	}
}
	