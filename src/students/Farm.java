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
	// run the farm simulation
	public void run() {
		while(true) {
		
			// display field
			System.out.println(field);
			System.out.println("Bank balance: $" + bankBalance);
			System.out.println("Enter your next action: \n"
					+ "t x y: till\n"
					+ "h x y: harvest\n"
					+ "p x y: plant\n"
					+ "pw x y: pull weed\n"
					+ "s: field summary\n"
					+ "w: wait\n"
					+ "q: quit");
		
			String input = scanner.nextLine();
			if ("q".equals(input)) break; 
		
			//separate inputs as individual items(coordinates) when spaces exist
			String[] cells = input.split(" ");
			int x, y;
		
			// actions - try, switch, case for options
			try {
				switch (cells[0]) {
					case "s":
						System.out.println(field.getSummary());
						break;
					// wait
					case "w":
						toWait();
						break;
					// till
					case "t":
						// convert str into int and correctly access the right element by -1
						// correctly map zero-based indexing system
						x = Integer.parseInt(cells[1]) - 1;
						y = Integer.parseInt(cells[2]) - 1;
						// till this location
						field.till(x, y); 
						break;
					// pull weed
					case "pw":
						x = Integer.parseInt(cells[1]) - 1;
						y = Integer.parseInt(cells[2]) - 1;
						field.pull(x, y);
						break;
					// harvest
					case "h":
						x = Integer.parseInt(cells[1]) - 1;
						y = Integer.parseInt(cells[2]) - 1;
						harvest(x, y);
						break;
				
					// plant
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
			} catch (Exception e) {
				System.out.print("Invalid cell location.\n");
			}
			
		}
	}
		
}
	