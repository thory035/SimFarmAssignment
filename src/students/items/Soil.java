package students.items;

public class Soil extends Item {

	// superclass constructor
	public Soil() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
	}
	
	@Override
	public String toString() {
		return ".";
	}
}
