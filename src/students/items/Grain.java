package students.items;

public class Grain extends Food{
	// counter to track Grain instances
	private static int generationCount = 0;
	
	public static int cost = 1;
	// superclass constructor
	public Grain() {
		super(2, 6, 2);
		// counter: incremental(1)
		generationCount++;
	}
	
	@Override
	public String toString() {
		if (age < maturAge) {
			return"g";
		} else {
			return "G";
		}
	}
	// gets total number of grain instances	
	public static int getGenerationCount() {
		return generationCount;
	}
}
