package model;

public class ItemNotFoundException extends RuntimeException {

	public ItemNotFoundException(String name) {
		super("Item not found: " + name);
	}

}
