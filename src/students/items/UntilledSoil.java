package students.items;

public class UntilledSoil extends Item {
	
	// superclass constructor
	public UntilledSoil() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -2);
	}
	
	@Override
	public String toString() {
		return "/ ";
	}
}
