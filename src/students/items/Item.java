package students.items;
// no instantiation for Item class
public abstract class Item implements Cloneable {
		// tracking (start at 0)
		public int  age = 0;
		public int maturAge = 0;
		public int deathAge = 0;
		public int value = 0; 
		// constructor
		public Item(int maturAge, int deathAge, int value) {
			this.maturAge = maturAge;
			this.deathAge = deathAge;
			this.value = value;
		}
		// counter: incremental(1)
		public void tick() {
		age++; 
		}
		// returns copy of item instance
		public Item clone() {
			try {
				return (Item) super.clone();
				} catch (CloneNotSupportedException e) {
				throw new RuntimeException("Clone not supported", e);
			}
		}
		public int getAge() {
		return age;
		}
		public int getMaturAge() {
		return maturAge;
		}
		//(setter)
		public void setAge(int newAge) {
			this.age = newAge;
		}
		public boolean died() {
			return age > deathAge;	
		}
		public int getValue() {
			if (age >= maturAge) {
				return value;
			} else {
				// item is not ready to be harvested
				return 0;
			}
		}
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
				if(!(obj instanceof Item))
					return false;
			// casting obj type to specific type
			Item items = (Item) obj;
			// if obj1 is equal to obj2 
			return (this.age == items.age)
					&& (this.maturAge == items.maturAge)
					&& (this.deathAge == items.deathAge)
					&& (this.value == items.value);
		}	
		public abstract String toString();
}