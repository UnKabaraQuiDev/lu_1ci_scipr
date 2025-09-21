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

	public int size() {
		/*
		 * if (root == null) return 0;
		 * 
		 * int count = 0;
		 * 
		 * Node node = root; while (node != null) { count++; node = node.getNext(); }
		 * 
		 * return count;
		 */

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
		MyList m = new MyList();

		m.add(1);
		m.add(2);
		m.add(3);

		m.print();
		System.out.println(m.toString());

		System.out.println(m.size());

		System.out.println(m.get(0));
		System.out.println(m.get(1));
		System.out.println(m.get(2));
	}

}
