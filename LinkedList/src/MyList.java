public class MyList {

	private Node root;
	private int size;

	public MyList() {
	}

	public MyList(Node root) {
		this.root = root;
	}

	public void add(int val) {
		size++;

		final Node newNode = new Node(val);

		if (root == null) {
			this.root = newNode;
			return;
		}

		Node node = root;
		while (node.getNext() != null) {
			node = node.getNext();
		}
		node.setNext(newNode);
	}

	public int remove(int idx) {
		if (idx == 0) {
			int data = root.getData();
			root = root.getNext();
			return data;
		}

		Node prev = root;

		while (prev != null && idx > 1) {
			prev = prev.getNext();
			idx--;
		}

		if (prev == null || prev.getNext() == null) {
			throw new IndexOutOfBoundsException("idx too big");
		}

		Node toRemove = prev.getNext();
		int data = toRemove.getData();
		prev.setNext(toRemove.getNext());

		size--;

		return data;
	}

	public int get(int idx) {
		Node n = root;
		while (n != null && idx != 0) {
			idx--;
			n = n.getNext();
		}

		if (idx != 0 || n == null) {
			throw new IndexOutOfBoundsException("idx to big");
		}

		return n.getData();
	}

	public void clear() {
		size = 0;
		this.root = null;
	}

	public int indexOf(int value) {
		Node tmp = root;
		int i = 0;
		while (tmp != null && tmp.getData() != value) {
			tmp = tmp.getNext();
			i++;
		}
		if (tmp == null)
			return -1;
		return i;
	}

	public boolean contains(int value) {
		/*
		 * Node tmp = root; while (tmp != null) { if (tmp.getData() == value) return true; tmp =
		 * tmp.getNext(); } return false;
		 */

		return indexOf(value) != -1;
	}

	public void selectionSort() {
		if (root == null || root.getNext() == null) {
			return;
		}

		Node current = root;

		while (current != null) {
			Node minNode = current;
			Node next = current.getNext();

			while (next != null) {
				if (next.getData() < minNode.getData()) {
					minNode = next;
				}
				next = next.getNext();
			}

			int temp = current.getData();
			current.setData(minNode.getData());
			minNode.setData(temp);

			current = current.getNext();
		}
	}

	public int size() {
		return size;
	}

	public void print() {
		if (root == null) {
			return;
		}

		Node node = root;
		while (node != null) {
			System.out.println(node.getData());
			node = node.getNext();
		}
	}

	@Override
	public String toString() {
		return root == null ? "[]" : "[" + root.toString() + "]";
	}

	public static void main(String[] args) {
		final MyList m = new MyList();

		m.add(1);
		m.add(2);
		m.add(3);

		m.print();
		System.out.println(m.toString());

		System.out.println(m.size());

		System.out.println(m.get(0));
		System.out.println(m.get(1));
		System.out.println(m.get(2));

		m.remove(0);
		System.out.println(m.toString());

		m.add(7);
		m.add(8);
		m.add(9);

		System.out.println(m.toString());
		System.out.println(m.indexOf(8));
		System.out.println(m.indexOf(10));

		System.out.println(m.contains(9));
		System.out.println(m.contains(12));
	}

}
