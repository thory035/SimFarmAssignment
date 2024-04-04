package students.items;

public class Carrots extends Food implements Cloneable {
	// counter to track Apple instances
	public static int generationCount = 0;
	public static int cost = 2;
	
	// superclass constructor
	public Carrots() {
		super(2, 5, 3);
	}
	
	@Override
	public Carrots clone() {
		Carrots cloned = (Carrots) super.clone();
		// counter: incremental(1)
		generationCount++;
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
