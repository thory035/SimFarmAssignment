package students.items;

public class Grain extends Food{
	
	private static int generationCount = 0;
	
	public Grain() {
		super(2, 6, 2);
		
		generationCount++;
	}
	
	public String toString() {
		if (age < maturAge) {
			return"G";
		} else {
			return "g";
		}
		
	public static int getGenerationCount() {
		return generationCount;
	}
}
