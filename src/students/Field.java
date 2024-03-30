package students;

import students.items.Apples;
import students.items.Food;
import students.items.Grain;
import students.items.Item;
import students.items.Soil; 
import students.items.Weed;
import students.items.UntilledSoil;

public class Field {
	// array of items to represent the field size
	public Item[][] field;
	
	// initialises array 
	public Field(int height, int width) {
		
		if (height > 10) {
			height = 10;
		}
		if (width > 10) {
			width = 10;
		}
		this.field = new Item[height][width];
		// add new Soil to Field under conditions 
		for (int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				// using import to add soil
				this.field[row][column] = new Soil();
			}
		}
	}

	public void tick() {
		// iterate each row
		for (int row = 0; row < field.length; row++) {
			// iterate each column
			for (int column = 0; column < field[row].length; column++) {
				// refers to the instance of Item and assign to variable	
				Item fieldItem = field[row][column];
				// check if not null
				if (fieldItem != null) {	
					// calls upon method from Item class
					fieldItem.tick();
					// if this is a Soil object and there is a 20% chance
					if (fieldItem instanceof Soil && Math.random() < 0.2) {
						// this location becomes a Weed
						field[row][column] = new Weed();
					}
					// otherwise if fieldItem died
					else if (fieldItem.died()) {
						// this location becomes UntilledSoil
						field[row][column] = new UntilledSoil();
						}
					}
				}
			}
		}
	@Override
	public String toString() {
		// initialises for alignment	
			StringBuilder align = new StringBuilder (" ");
			for (int header = 1; header <= field[0].length; header++) {
				align.append(header > 9 ? header: " " + header).append(" ");
			}
			align.append("\n");
			
			// loop rows
			for (int row = 0; row < field.length; row++) {
				// adjust space between numbers
				align.append(row + 1).append(row + 1 > 9 ? "" : " ");
				for (int column = 0; column < field[row].length; column++) {
					if (field[row][column] instanceof Soil) {
						align.append(".  ");
					} else if (field[row][column] != null) {
						align.append(field[row][column].toString()).append(" ");
					} else {
						align.append(".  ");
					}
				}
				align.append("\n");
			}
			return align.toString();
	}
	// tills location and turns it to Soil
	public void till(int row, int column) {
		// if this location is in the Field
		if (row >= 0 && row < field.length && column < field[row].length) {
			// add Soil to location
			field[row][column] = new Soil();
		}
	}
	// get item copy from field location
	public Item get(int row, int column) {
		if (row >= 0 && row < field.length && column >= 0 && column < field[row].length) {
			// item is returned to field location
			Item item = field[row][column];
			if (item != null) {
				// clone item before returning it
				return item.clone();
			}
		}
		return null;
	}
	// plant at field location
	public void plant(int row, int column, Item item) throws IllegalArgumentException {
		// check if location is inside field
		if (row < 0 || row >= field.length || column < 0 || column >= field[row].length) {
			throw new IllegalArgumentException("Can only plant wirhin field dimensions");
		}
			// if dimensions are true, plant item
			this.field[row][column] = item;
		}	
	// calculate total value of all items in the Field
	public int getValue() {
		// initialise total value
		int totalValue = 0;
		// iterate through each field location
		for (int row = 0; row < field.length; row++) {
			for (int column = 0; column < field[row].length; column++) {
				// get item at field location
				Item fieldItem = field[row][column];
				// check if there's an item there
				if (fieldItem != null) {
					// add item's value to total
					totalValue += fieldItem.getValue();
				}
			}
		}
		return totalValue;
	}
	// create summary of total value of items on field
	public String getSummary() {
	 int appleCount = 0;
	 int grainCount = 0;
	 int soilCount = 0;
	 int untilledCount = 0;
	 int weedCount = 0;
	 int totalValue = 0;
	 // iterate through items on Field
	 for (Item[] row : field) {
		 for (Item item : row) {
			 if (item != null) {
				 // increment based on type
				 if (item instanceof Apples) appleCount++;
				 else if (item instanceof Grain) grainCount++;
				 else if (item instanceof Soil) soilCount++;
				 else if (item instanceof UntilledSoil) untilledCount++;
				 else if (item instanceof Weed) weedCount++;
				 // if item is an instance 
				 if (item instanceof Food && item.getValue() > 0) {
					 // add to total value
					 totalValue += item.getValue();
				}	
			}
		}
	}
	// return summary as a string
	return String.format("Apples: "+ appleCount +
						"\nGrain: " + grainCount +
						"\nSoil: " + soilCount +
						"\nUntilled: " + untilledCount +
						"\nWeed: " + weedCount +
						"\nFor a total of $: " + totalValue +
						"\nTotal apples created: " + Apples.getGenerationCount() +
						"\nTotal grain created: " + Grain.getGenerationCount() 
						+ "\n");
	}		
}