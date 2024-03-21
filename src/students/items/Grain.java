package students.items;

public class Grain extends Food{
	// counter to track Grain instances
	public static int generationCount = 0;
	
	public static int cost = 1;
	// superclass constructor
	public Grain() {
		super(2, 6, 2);
		// counter: incremental(1)
		generationCount++;
	}
	
	@Override
	public String toString() {
		if (age >= maturAge) {
			return"G";
		} else {
			return "g";
		}
	}
	// gets total number of grain instances	
	public static int getGenerationCount() {
		return generationCount;
	}
}
