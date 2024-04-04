package students;

import java.util.Scanner;
import students.items.Apples;
import students.items.Grain;
import students.items.Item;
import students.items.Soil;
import students.items.Weed;
import students.Field;
import students.tools.WeedKiller;

public class Farm {
	
	// initialise
	private Scanner scanner; 
	private Field field;
	private Shop shop;
	private int bankBalance;
	private int totalProfits = 0;
	private int totalLoss = 0;
	private boolean hasWeedKiller = false;

	
	// initialise farm size and player money
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.field = new Field(fieldHeight, fieldWidth);
		this.bankBalance = startingFunds;
		this.scanner = new Scanner(System.in);
		this.shop = new Shop(this);
	}
	
	// getter for Shop class to access
	public int getBankBalance() {
		return bankBalance;
	}
	
	// setter for Shop class to access
	public void setBankBalanance(int bankBalance) {
		this.bankBalance = bankBalance;
	}
	
	// kills weeds in a 3x3
	public void applyWeedKiller(int cenX, int cenY) {
		field.applyWeedKiller(cenX, cenY);
		System.out.println("Weeds Killer was applied! It's super effective!");
	}
	
	// farming
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
	// 1 tick passes
	public void toWait() {
		 field.tick();
		 System.out.println("And so you wait...\n");
	}
	// 3 tick passes
	public void rest() {
		field.tick();
		System.out.print("And so you rest...");
	}
	
	public void shopOptions() {
		while (true) {
			System.out.println("\nWelome customer!"
					+ "-----------------------\n"
					+ "--> [wk] $50 Weed Killer\n"
					+ "--> [b ] Return to main menu\n");
					
			String input = scanner.nextLine();
			
			if ("b".equals(input)) {
				return;
			}

			switch (input) {
			case "wk":
				if ()
				System.out.print("Weed Killer purchased!");
				break;
			default:
				System.out.print("Invalid action. Try again.");
			// till untilled soil
//			case "Sell Farm":
//				System.out.print("If you sell your farm,"
//						+ "you will end the game."
//						+ "Do you wish to proceed?");
//				//???
//				// if user input Y
//				sellFarm();
//				// else
//				break;
				

			}
		}
	}
	
	public void farmingOptions() {
		while (true) {
			System.out.println("Farm\n");
			System.out.println("| [t ] Till\n");
			System.out.println( "| [h ] Harvest\n");
			System.out.println("| [p ] Plant\n");
			System.out.println( "| [pw] Pull weed\n");
			if (hasWeedKiller) {
				System.out.println("| [wk] Apply Weed Killer");
			}
			System.out.println("| [fs] Field Summary\n");
			System.out.println( "| [b ] Return to main menu\n");
			
			String input = scanner.nextLine();
			if ("b".equals(input)) {
				return;
			}
			
			String[] cells = input.split(" ");
			int x, y;
			
			switch (cells[0]) {
				case "wk":
				 if (!hasWeedKiller) {
					 System.out.println("You do not have Weed Killer.");
					 break;
				 }
				 
				System.out.println("enter wk and select cells (x,y)");
				input = scanner.nextLine();
				String[] parts = input.split("");
				Item isSoil = field.get(x, y);
				// check if field location is soil or weed
				if (isSoil instanceof Soil|| isSoil instanceof Weed) {
					if(parts.length >= 2) {
						// correctly map zero-based indexing system
						x = Integer.parseInt(cells[1]) - 1;
						y = Integer.parseInt(cells[2]) - 1;
						applyWeedKiller(x, y); 
						System.out.println("Weed Killer successfully applied!");
					} else {
						System.out.print("Tool can only be applied to weeds");
					}
				}
				break;
				// till UntilledSoil
				case "t":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;
					field.till(x, y); 
					break;
				//harvest planted item
				case "h":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;
					harvest(x, y);
					break;
				//wait = 1 tick()
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
						default:
							System.out.println("Invalid action. Try again.");
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

					+ "\n> [f ] Farm"
					+ "\n> [s ] Shop "
					+ "\n> [r ] Rest "
					+ "\n> [fs] Field Summary"
					+ "\n> [q ] Quit");
		
			String input = scanner.nextLine();
			String[] cells = input.split(" ");
			if ("q".equals(input)) break; 
			int x, y;

			// actions - try, switch, case for options
			try {
				switch (cells[0]) {
					case "f":
						farmingOptions();
						break;
					case "fs":
						System.out.println(field.getSummary());
					case "s":
						shopOptions();
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
	