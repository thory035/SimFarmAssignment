package students.tools;

public class WeedKiller extends Tool {

	public static int generationCount = 0;
	// superclass constructor
	public WeedKiller(int value, int durability, int strength, int range) {
		super(50, 10, 3, 9);
		this.range = range;
	}
	
	public void applyWeedKiller() {
		
	}
	
	@Override
	public String toString() {
		return "#@ ";
	}
}
