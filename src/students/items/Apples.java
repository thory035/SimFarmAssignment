package students.items;

public class Apples extends Food implements Cloneable {
	// counter to track Apple instances
	public static int generationCount = 0;
	public static int cost = 2;
	
	// superclass constructor
	public Apples() {
		super(3, 5, 3);
	}
	
	@Override
	public Apples clone() {
		Apples cloned = (Apples) super.clone();
		// counter: incremental(1)
		generationCount++;
		return cloned;
	}
	
	@Override
	public String toString() {
		if (age >= maturAge) {
			return"A ";
		} else {
			return "a ";
		}
	}
	// gets total number of apple instances	
	public static int getGenerationCount() {
		return generationCount;
	}
}
