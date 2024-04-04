package students.tools;

import students.items.Item;

public abstract class Tool extends Item{

	protected int durability = 0;
	protected int strength = 0;
	protected String toolName;
	
	public Tool(int value, String toolName, int durability, int strength) {
		super(value, strength, strength);
		this.toolName = toolName;
		this.durability = durability;
		this.strength = strength;
	}
	
	public String getToolName() {
		return toolName;
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
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
			if(!(obj instanceof Tool))
				return false;

		Tool tool = (Tool) obj;
		
		return (this.toolName == tool.toolName)
				&& (this.durability == tool.durability)
				&& (this.strength == tool.strength);
	}	
	
	
}
