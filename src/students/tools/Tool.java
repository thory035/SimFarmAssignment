package students.tools;

import students.items.Item;

public abstract class Tool extends Item{

	protected int durability = 0;
	protected int strength = 0;
	
	public Tool(int value, int durability, int strength) {
		super(value, strength, strength);
		this.durability = durability;
		this.strength = strength;
	}
	
	public int getDurability() {
		return durability;
	}
	
	public boolean toolBroken() {
		return durability == 0;
	}
	
	public int strength() {
		return strength;
	}
	
	
}
