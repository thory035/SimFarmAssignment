package students;


public class Field extends Item {
	// array of items to represent the field size
	private Item[][] field;
	
	public Field(int height, int width) {
		this.field = new Item[height][width];
	}
	
}
