package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemList {

	protected ItemNode root;
	protected int size;

	public ItemList() {
	}

	public ItemList(ItemNode root) {
		this.root = root;
	}

	public void addOrIncrementCount(Item item, int count) {
		ItemNode last = null;
		for (int i = 0; i < size; i++) {
			final ItemNode it = get(i);
			if (it.getItem().getName().equals(item.getName())) {
				it.setCount(it.getCount() + count);
				return;
			}
			last = it;
		}

		final ItemNode it = new ItemNode(item, count);
		size++;
		if (last == null) {
			root = it;
		} else {
			last.setNext(it);
		}
	}

	public ItemNode get(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		return getRecursive(root, index);
	}

	private ItemNode getRecursive(ItemNode node, int index) {
		if (node == null) {
			return null;
		}
		if (index == 0) {
			return node;
		}

		return getRecursive(node.getNext(), index - 1);
	}

	public int removeOrDecrement(Item item, int count) {
		ItemNode prev = null;
		for (int i = 0; i < size; i++) {
			final ItemNode it = get(i);
			if (it.getItem().getName().equals(item.getName())) {
				final int ret = Math.min(count, it.getCount());
				it.setCount(it.getCount() - count);
				if (it.getCount() <= 0) {
					size--;
					if (prev == null) {
						// we are root
						root = it.getNext();
					} else {
						prev.setNext(it.getNext());
					}
				}
				return ret;
			}
			prev = it;
		}

		throw new ItemNotFoundException(item.getName());
	}

	public void saveToCsv(String filename) throws IOException {
		Files
				.writeString(Paths.get(filename),
						Arrays
								.stream(toArray())
								.map(c -> c.getItem().getName() + "," + c.getItem().getPrice() + "," + c.getCount())
								.collect(Collectors.joining("\n")),
						StandardOpenOption.CREATE);
	}

	public void loadFromCsv(String filename) throws IOException {
		Files.readAllLines(Paths.get(filename)).forEach(l -> {
			final String[] tokens = l.split(",");
			addOrIncrementCount(new Item(tokens[0], Double.parseDouble(tokens[1])), Integer.parseInt(tokens[2]));
		});
	}

	public ItemNode[] toArray() {
		final List<ItemNode> items = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			items.add(get(i));
		}
//		Collections.sort(items);
		return items.toArray(ItemNode[]::new);
	}

}
