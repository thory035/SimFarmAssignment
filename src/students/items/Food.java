package students.items;

//sub class of Item - should not be able to be instantiated
public abstract class Food extends Item {
	
	 public Food(int maturAge, int deathAge, int value) {
		 super(maturAge, deathAge, value);
	 }

}
