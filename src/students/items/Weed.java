package students.items;

public class Weed extends Item {
		
	// superclass constructor
	public Weed() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -2);
	}
	
	@Override
	public String toString() {
		return "#";
	}
}
