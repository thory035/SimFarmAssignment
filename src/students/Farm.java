package students;

import java.util.Scanner;
import students.items.Apples;
import students.items.Grain;
import students.items.Carrots;
import students.items.Item;
import students.items.Soil;
import students.items.Weed;

public class Farm {
	
	// initialise
	private Scanner scanner; 
	private Field field;
	private int bankBalance;
	private static int totalProfit = 0;
	private boolean hasWeedKiller = false;
	private boolean hasCarrots = false;

	// initialise farm size and player money
	public Farm(int fieldWidth, int fieldHeight, int startingFunds)
	{
		this.field = new Field(fieldHeight, fieldWidth);
		this.bankBalance = startingFunds;
		this.scanner = new Scanner(System.in);
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
	public void applyWeedKiller(int x, int y) {
		field.applyWeedKiller(x, y);
		System.out.println("|----------------------|\n"
				+ "Weeds Killer was applied!\n"
				+ "It's super effective!\n"
				+ "|----------------------|\n");
	}
	// farming
	public void harvest(int x, int y) {
		// return item or null
		Item item = field.get(x, y);
		// check if food item is mature
		if (item != null && item.getAge() >= item.getMaturAge()) {
			// add item value to bankBalance
			bankBalance += item.getValue();
			totalProfit += item.getValue();
			//adds Soil item to this cell
			field.plant(x, y, new Soil());
			System.out.println("|----------------------|\n"
					+ "Item has been harvested\n"
					+ "|----------------------|");
		} else {
			System.out.println("|----------------------|\n"
					+ "Cannot be harvested.\n"
					+ "|----------------------|\n");
		}
	// returns total profit of harvest
	}
	// 1 tick passes
	public void toWait() {
		 field.tick();
		 // chance occurrence
		 field.checkRabbitRaid();
		 System.out.println("|----------------------|\n"
		 		+ "And so you wait...\n"
		 		+ "|----------------------|\n");
	}
	
	public void shopOptions() {
		while (true) {
			System.out.println("_________________________________\n"
					+ "| Bank balance: $" + bankBalance+ "\n"
					+ "________________________________\n"
					+ "\n--------------------------------\n"
					+ "Shopping..."
					+ "\n--------------------------------\n"
					+ ">> [wk] $10 Weed Killer\n"
					+ ">> [c ] $2  Carrots\n"
					+ ">> [ss] Sell farm\n"
					+ "<< [b ] Return to main menu\n");
			System.out.print("> ");
					
			String input = scanner.nextLine();
			
			if ("b".equals(input)) {
				return;
			}

			switch (input.toLowerCase()) {
			case "wk":
				if (getBankBalance() >= 10) {
					setBankBalanance(getBankBalance() - 10);
					hasWeedKiller = true;
				System.out.print("|----------------------|\n"
						+ "Weed Killer purchased!\n"
						+ "Item was added to farming\n"
						+ "|----------------------|\n");
			} else {
				System.out.println("|----------------------|\n"
						+ "Not enough funds.\n"
						+ "|----------------------|\n");
			}
				break;
			case "c":
				if (getBankBalance() >= 2) {
					setBankBalanance(getBankBalance() - 2);
					hasCarrots = true;
				System.out.print("|----------------------|\n"
						+ "Carrot seeds purchased!\n"
						+ "You can now plant carrots.\n"
						+ "|----------------------|\n");
			} else {
				System.out.println("|----------------------|\n"
						+ "Not enough funds.\n"
						+ "|----------------------|\n");
			}
				break;
			case "ss":
				System.out.print("|----------------------|\n"
						+ "| Field Summary\n");
				System.out.print(field.getSummary());
				System.out.print("| Total Profit: $ " + totalProfit + "\n");
				System.out.print("| Thank you for playing!\n"
						+ "|----------------------|\n");
				System.exit(0);
				break;
				
			case "b":
				return;
			default:
				return;
			}
		}
	}
	
	public void farmingOptions() {
	
		while (true) {
			System.out.println("_________________________________\n"
					+ "| Bank balance: $" + bankBalance
					+ "\n________________________________\n"
					+ field
					+ "\n--------------------------------\n"
					+ "Farming..."
					+ "\n--------------------------------\n"
					+ "\nEnter action [..], "
					+ "\nthen location [x,y]\n"
					+ "\nFarm Menu\n"
					+ ">> [t ] Till\n"
					+ ">> [h ] Harvest\n"
					+ ">> [p ] Plant\n"
					+ ">> [pw] Pull weed");
			if (hasWeedKiller) {
				System.out.println(">> [wk] Apply Weed Killer");
			}
			System.out.print(">> [w ] Wait\n"
					+ ">> [fs] Field Summary\n"
					+ "<< [b ] Return to main menu\n");
			System.out.print("> ");
			
			String input = scanner.nextLine();
			
			if ("b".equals(input)) {
				return;
			}
			
			String[] cells = input.split(" ");
			int x = 0; 
			int y = 0;
			
			switch (cells[0]) {
				case "wk":
				 if (!hasWeedKiller) {
					 System.out.println("|----------------------|\n"
					 		+ "You do not have "
					 		+ "Weed Killer.\n"
					 		+ "|----------------------|\n");
					 break;
				 }
				String[] parts = input.split("");
				Item isSoil = field.get(x, y);
				// check if field location is soil or weed
				if (isSoil instanceof Soil|| isSoil instanceof Weed) {
					if(parts.length >= 2) {
						// correctly map zero-based indexing system
						x = Integer.parseInt(cells[1]) - 1;
						y = Integer.parseInt(cells[2]) - 1;
						applyWeedKiller(x, y); 
					} else {
						System.out.print("|----------------------|"
								+ "Tool can only be "
								+ "applied to weeds.\n"
								+ "|----------------------|\n");
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
				case "fs":
					System.out.println(field.getSummary());
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
				
						Item isSoil1 = field.get(x, y);
						// checking soil instance
						if (isSoil1 instanceof Soil) {
							System.out.print("\n|----------------------|\n"
									+ "Select Seeds to plant\n"
									+ "|----------------------|\n"
									+ ">> [a] Apple $" + Apples.cost + "\n"
									+ ">> [g] Grain $" + Grain.cost + "\n");
							
							 if (hasCarrots) {
									System.out.print(">> [c] Carrot $" + Carrots.cost + "\n");
								}
							 System.out.print("> ");
							// listens for next line of string
							String option = scanner.nextLine();
							
							switch(option) {
							//plant apple
							case "a":
								if(bankBalance >= Apples.cost) {
									field.plant(x, y, new Apples());
									bankBalance -= Apples.cost;
									System.out.println("\n|----------------------|\n"
											+ "Apple has been planted!\n"
											+ "|----------------------|\n");
									break;
								} else {
									System.out.println("|----------------------|\n"
											+ "Sorry, not enough funds :(\n"
											+ "|----------------------|\n");
								}
							break;
							// plant grain
							case "g":
								if(bankBalance >= Grain.cost) {
									field.plant(x, y, new Grain());
									bankBalance -= Grain.cost;
									System.out.println("|----------------------|\n"
											+ "Grain has been planted!\n"
											+ "|----------------------|\n");
									break;
								} else {
									System.out.println("|----------------------|\n"
											+ "Sorry, not enough funds :(\n"
											+ "|----------------------|\n");
								}
								break;
								// plant grain
							case "c":
								if (!hasCarrots) {
									 System.out.println("|----------------------|\n"
									 		+ "You do not have carrots.\n"
									 		+ "|----------------------|\n");
									 break;
								}
								if(bankBalance >= Carrots.cost) {
									field.plant(x, y, new Carrots());
									bankBalance -= Grain.cost;
									System.out.println("|----------------------|\n"
											+ "Carrot has been planted!\n"
											+ "|----------------------|\n");
									break;
								} else {
									System.out.println("|----------------------|\n"
											+ "Sorry, not enough funds :(\n"
											+ "|----------------------|\n");
								}
								break;
						default:
							return;
							}
						}
					}
				}
			}
			
	// run the farm simulation
	public void run() {
		System.out.println("|----------------------|\n"
				+ "| Congratulations!     \n"
				+ "| You've inherited your\n"
				+ "| grandfather's farm.  \n"
				+ "|----------------------|\n"
				+ "\n"
				+ "Name your farm:");
		System.out.print("> ");
		
		String farmName = scanner.nextLine();
		
		System.out.print( "\n----------------------|\n"
				+ "|" + farmName + " farm,\n"
				+ "|what a wonderful\n"
				+ "|name!\n"
				+ "|----------------------|\n");
		
		while(true) {
		
			// display field
			System.out.println("\n" + farmName + " Farm"
					+ "\n--------------------------------\n"
					+ "\n" + field
					+"\n_________________________________\n"
					+ "| Bank balance: $" + bankBalance
					+ "\n________________________________\n"
					+ "\nEnter action [...]\n"
					+ "\n> [f ] Farm"
					+ "\n> [s ] Shop "
					+ "\n> [fs] Field Summary"
					+ "\n> [q ] Quit");
			System.out.print("> ");
			
			String input = scanner.nextLine();
			
			if ("q".equals(input)) {
				System.out.println("Quitting...");
				System.exit(0);
			}
			// actions - try, switch, case for options
			try {
				switch (input.toLowerCase()) {
					case "f":
						farmingOptions();
						break;
					case "fs":
						System.out.println(field.getSummary());
					case "s":
						shopOptions();
				}
			} catch (Exception e) {
				System.out.print("|----------------------|\n"
						+ "Error occured.\n"
						+ "Please try again.\n"
						+ "|----------------------|\n");
			}
			
		}
	}
	public static int getTotalProfit() {
		return totalProfit;
	}
}
	