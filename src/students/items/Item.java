package students.items;
// no instantiation for Item class
public class Item {
	// tracking (start at 0)
		private int age = 0;
		private int maturAge = 0;
		private int deathAge = 0;
		private int value = 0; 

	// counter: incremental(1)
	public void tick() {
		age++; 
	}
	
	public int getAge() {
		return age;
	}
	//(setter)
	public void setAge(int newAge) {
		this.age = newAge;
	}

	public boolean died() {
		return age > deathAge;
		
	}
	
}
