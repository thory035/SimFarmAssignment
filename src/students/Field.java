package students;

import students.items.Item;
import students.items.Soil;

public class Field {
	// array of items to represent the field size
	private Item[][] field;
	
	// initializes array 
	public Field(int height, int width) {
		this.field = new Item[height][width];
		// add new Soil to Field under conditions 
		for(int i = 0; i < height; i++) {
			for (int k = 0; k < width; k++) {
				// using import to add soil
				this.field[i][k] = new Soil();
			}
		}
	}
	
}
