package students.tools;

import students.Field;
import students.items.Item;
import students.items.UntilledSoil;
import students.items.Weed;

public class WeedKiller extends Tool {
	public static int cost = 10;

	public Field field;
	
	// superclass constructor
	public WeedKiller(int value, String toolname, int durability, int strength, Field field) {
		super(5, "Weed Killer", 10, 3);
		this.field = field;
	}

	@Override
	public String toString() {
		return "#@ ";
	}
}
