package students;

import students.items.Item;
import students.items.Soil;

public class Field {
	// array of items to represent the field size
	public Item[][] field;
	
	// initializes array 
	public Field(int height, int width) {
		this.field = new Item[height][width];
		// add new Soil to Field under conditions 
		for(int row = 0; row < height; row++) {
			for (int column = 0; column < width; column++) {
				// using import to add soil
				this.field[row][column] = new Soil();
			}
		}
	}

	public void tick() {
		// iterate each row
		for(int row = 0; row < field.length; row++) {
			// iterate each column
			for(int column = 0; column < field[row].length; column++) {
				// check if not null
				if (field[row][column] != null) {	
					// increase age++
					field[row][column].tick();
				}
			}
		}
	}
	
	// initialise function
			// for the index at row position
			// for the index at column position
				// if at row|column position is not null and soil(instanceof)
					// calculate random number, check if less than 20% (0.2)
					// need to import Math.random()??
					// if true replace soil with weed (new Weed)
}
