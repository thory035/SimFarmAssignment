package students.items;

public class Apples extends Food {
	// counter to track Apple instances
		private static int generationCount = 0;
		
		public static int cost = 2;
		// superclass constructor
		public Apples() {
			super(3, 5, 3);
			// counter: incremental(1)
			generationCount++;
		}
		
		@Override
		public String toString() {
			if (age < maturAge) {
				return"a";
			} else {
				return "A";
			}
		}
		// gets total number of grain instances	
		public static int getGenerationCount() {
			return generationCount;
		}
}
