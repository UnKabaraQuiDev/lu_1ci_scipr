package model;

public class Cart extends ItemList {

	public double getTotal() {
		int price = 0;
		for(int i = 0; i < size; i++) {
			final ItemNode it = get(i);
			price += it.getCount() * it.getItem().getPrice();
		}
		return price;
	}
	
}
