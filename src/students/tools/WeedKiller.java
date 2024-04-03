package students.tools;

import students.Field;
import students.items.Item;
import students.items.UntilledSoil;
import students.items.Weed;

public class WeedKiller extends Tool {

	public Field field;
	
	// superclass constructor
	public WeedKiller(int value, String toolname, int durability, int strength, Field field) {
		super(25, "Weed Killer", 10, 3);
		this.field = field;
	}

	// applied 2D array representation
	public void applyWeedKiller(int cenX, int cenY) {
		// defines radius for aoe
		int range = 1;
		// iterate over rows in the 3x3 grid centred at x, y
		for (int row = cenX - range; row <= cenX + range; row++) {
			// iterate over columns in the 3x3 grid centred at x, y
			for (int column = cenY - range; column <= cenY + range; column++) {
				// check if current cell is within field
				if (row >= 0 && row < field.field.length && column < field.field[0].length) {
					// access item in field location
					Item fieldItem = field.field[row][column];
					if (fieldItem instanceof Weed) {
						// replace weed with soil
						field.field[row][column] = new UntilledSoil();
					}
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return "#@ ";
	}
}
