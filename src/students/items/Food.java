package students.items;

//sub class of Item - should not be able to be instantiated
public abstract class Food extends Item implements Cloneable {
	
	 public Food(int maturAge, int deathAge, int value) {
		 super(maturAge, deathAge, value);
	 }

	 @Override
	 public Food clone() {
			 return(Food) super.clone();
	 }
	
}
