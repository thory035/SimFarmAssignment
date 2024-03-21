package students;

import students.items.Item;
import students.items.Soil; 
import students.items.Weed;
import students.items.UntilledSoil;

import java.lang.Math;

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
	}
