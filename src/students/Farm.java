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
	private static String farmName;
	private Field field;
	private int bankBalance;
	private static int totalProfit = 0;
	private boolean hasWeedKiller = false;
	private boolean hasCarrots = false;
	
	public Farm(int fieldWidth, int fieldHeight, int startingFunds) {
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
	
	// shopping options
	public void shopOptions() {
		while (true) {
			System.out.println("\nBank balance: $" + bankBalance
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
			// takes capitalised letter to lower case
			switch (input.toLowerCase()) {
			case "wk":
				// calculations for purchase
				if (getBankBalance() >= 10) {
					setBankBalanance(getBankBalance() - 10);
					hasWeedKiller = true;
				System.out.print("|----------------------|\n"
						+ "Weed Killer purchased!\n"
						+ "Item was added to farming\n"
						+ "|----------------------|\n");
			} else {
				System.out.println("|----------------------|\n"
						+ "Not enough funds :(\n"
						+ "|----------------------|\n");
			}
				break;
			case "c":
				// calculations for purchase
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
				System.out.print(field.getSummary());
				System.out.print("| Thank you for playing!\n"
						+ "|......................|\n");
				System.exit(0);
				break;
				
			case "b":
				return;
			default:
				break;
			}
		}
	}
	
	public static String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public void farmingOptions() {
		while (true) {
			System.out.println( "\n" + field
					+ "\nBank balance: $" + bankBalance
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
			// if weedkiller has been purchased
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
				// if user inputs wk before purchase of tool
				 if (!hasWeedKiller) {
					 System.out.println("|----------------------|\n"
					 		+ "You do not have\n "
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
								+ "Tool can only be\n"
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
				// harvest planted item
				case "h":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;
					harvest(x, y);
					break;
				// wait = 1 tick()
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
				// plant
				case "p":
					x = Integer.parseInt(cells[1]) - 1;
					y = Integer.parseInt(cells[2]) - 1;					
						// if item is soil get location
						Item isSoil1 = field.get(x, y);
						// checking soil instance
						if (isSoil1 instanceof Soil) {
							System.out.print("\n|----------------------|\n"
									+ "Select Seeds to plant\n"
									+ "|----------------------|\n"
									+ ">> [a] Apple $" + Apples.cost + "\n"
									+ ">> [g] Grain $" + Grain.cost + "\n");
							// if carrots have been purchased 
							if (hasCarrots) {
									System.out.print(">> [c] Carrot $" + Carrots.cost + "\n");
								}
							 System.out.print("> ");
							String option = scanner.nextLine();
							
							switch(option) {
							// plant apple
							case "a":
								// calculations for purchase
								if(bankBalance >= Apples.cost) {
									field.plant(x, y, new Apples());
									bankBalance -= Apples.cost;
									System.out.println("\n|----------------------|\n"
											+ "Apple has been planted!\n"
											+ "|----------------------|");
									break;
								} else {
									System.out.println("|----------------------|\n"
											+ "Sorry, not enough funds :(\n"
											+ "|----------------------|\n");
								}
							break;
							// plant grain
							case "g":
								// calculations for purchase
								if(bankBalance >= Grain.cost) {
									field.plant(x, y, new Grain());
									bankBalance -= Grain.cost;
									System.out.println("|----------------------|\n"
											+ "Grain has been planted!\n"
											+ "|----------------------|");
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
								// calculations for purchase
								if(bankBalance >= Carrots.cost) {
									field.plant(x, y, new Carrots());
									bankBalance -= Grain.cost;
									System.out.println("|----------------------|\n"
											+ "Carrot has been planted!\n"
											+ "|----------------------|");
									break;
								} else {
									System.out.println("|----------------------|\n"
											+ "Sorry, not enough funds :(\n"
											+ "|----------------------|\n");
								}
									break;
						default:
							break;
							}
						}
					}
				}
			}
			
	public void askFarmName() {
		// opening message
		if (this.farmName == null) {
		System.out.println("|----------------------|\n"
				+ "| Congratulations!     \n"
				+ "| You've inherited your\n"
				+ "| grandfather's farm.  \n"
				+ "|----------------------|\n"
				+ "\n"
				+ "Name your farm:");
		System.out.print("> ");
		this.setFarmName(scanner.nextLine());

		System.out.print( "\n----------------------|\n"
				+ "|" + farmName + " farm,\n"
				+ "|what a wonderful\n"
				+ "|name!\n"
				+ "|----------------------|\n");
		}
	}
	// run the farm simulation
	public void run() {
		askFarmName();
		while(true) {
			System.out.println("\n" + farmName + " Farm"
					+ "\n--------------------------------\n"
					+ "\n" + field
					+"\n| Bank balance: $" + bankBalance + "\n"
					+ "\nEnter action [...]\n"
					+ "\n> [f ] Farm"
					+ "\n> [s ] Shop "
					+ "\n> [fs] Field Summary");
			// for testing and debugging
			//+ "\n> [q ] Quit");
			System.out.print("> ");
			
			String input = scanner.nextLine();
			
			if ("q".equals(input)) {
				System.out.println("Quitting...");
				System.exit(0);
			}
			try {
				switch (input.toLowerCase()) {
					case "f":
						farmingOptions();
						break;
					case "fs":
						System.out.println(field.getSummary());
						break;
					case "s":
						shopOptions();
						break;
				}
			} catch (Exception e) {
				System.out.print("|----------------------|\n"
						+ "Please try again.\n"
						+ "|----------------------|\n");
			}
			
		}
	}
	public static int getTotalProfit() {
		return totalProfit;
	}
}
	