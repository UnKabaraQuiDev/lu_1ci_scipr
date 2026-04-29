package model;

public class ItemNode implements Comparable<ItemNode> {

	private Item item;
	private int count;
	private ItemNode next;

	public ItemNode(Item item, int count) {
		this.item = item;
		this.count = count;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ItemNode getNext() {
		return next;
	}

	public void setNext(ItemNode next) {
		this.next = next;
	}

	@Override
	public int compareTo(ItemNode other) {
		return item.getName().compareTo(other.item.getName());
	}

	@Override
	public String toString() {
		return item + " - " + count;
	}

}
