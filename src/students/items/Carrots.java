package students.items;

public class Carrots extends Food implements Cloneable {
	// counter to track Apple instances
	public static int generationCount = 0;
	public static int cost = 1;
	
	// superclass constructor
	public Carrots() {
		super(2, 5, 5);
		// counter: incremental(1)
		generationCount++;
	}
	
	@Override
	public Carrots clone() {
		Carrots cloned = (Carrots) super.clone();

		return cloned;
	}
	
	@Override
	public String toString() {
		if (age >= maturAge) {
			return"C ";
		} else {
			return "c ";
		}
	}
	// gets total number of apple instances	
	public static int getGenerationCount() {
		return generationCount;
	}
}