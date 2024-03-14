package students.items;

public class UntilledSoil extends Item {
	
	public static int cost = -1;
	// superclass constructor
	public UntilledSoil() {
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -2);
	}
	
	@Override
	public String toString() {
		return "/";
	}
}
