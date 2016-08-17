package list;

import java.util.NoSuchElementException;

/**
 * ˫������
 * 
 * @author New Song
 *
 * @param <E>
 */
public class MyDoubleLinkedList<E> implements MyList<E> {
	private Node head;
	private Node tail;
	private int size;

	public MyDoubleLinkedList() {
		head = null;
		tail = head;
		size = 0;
	}

	// ��ӵ�β
	public boolean add(E e) {
		if (isEmpty()) {
			head = new Node(e, null, tail);
			tail = head;
			// ֻ��һ�����ʱ������ͷ����β
		} else {
			Node newNode = new Node(e, tail, null);
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}

	private void checkBounds(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("����Ԫ�ع�" + size + "������Ҫ���ʵ�Ԫ���±�Ϊ" + index);
		}
	}

	private void checkEmpty() {
		if (head == null) {
			throw new NoSuchElementException("����Ϊ��");
		}
	}

	// ���뵽��λ�ã���λ��ԭ�е�Ԫ�غ���
	public void add(int index, E e) {
		if (index == 0) {
			addFirst(e);
		} else {
			Node node = null;
			int i = 0;
			if (index <= size / 2) {
				node = head;
				while (node != null && i < index) {
					node = node.next;
					i++;
				} // node ָ���λ�õ�Ԫ��
			} else {
				node = tail;
				i = size - 1;
				while (node != null && i > index) {
					node = node.prev;
					i--;
				}
			}
			// ��ʱnodeָ��index����Ԫ�أ���Ҫ����Ԫ�صĺ�һ��Ԫ��
			Node newNode = new Node(e, node.prev, node);
			node.prev.next = newNode;
			node.prev = newNode;
			size++;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void addFirst(E e) {
		if (isEmpty()) {
			head = new Node(e, null, tail);
			tail = head;
			// �������Ϊ�գ���ô��βҲΪ��һ����㣨�μ�add������
		} else {
			Node newNode = new Node(e, null, head);
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}

	public void addLast(E e) {
		add(e);
	}

	public void clear() {
		Node node = head;
		Node temp = null;
		while (node != null) {
			temp = node.next;
			node = null;
			node = temp;
		}
		head = null;
		tail = null;
		size = 0;
	}

	public boolean contains(E e) {
		Node node = head;
		while (node != null) {
			if (node.data.equals(e)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}

	// ����ͷ
	public E element() {
		checkEmpty();
		return head.data;
	}

	public E get(int index) {
		checkBounds(index);
		Node node = null;
		int i = 0;
		if (index <= size / 2) {
			node = head;
			while (node != null && i < index) {
				node = node.next;
				i++;
			}
		} else {
			node = tail;
			i = size - 1;
			while (node != null && i > index) {
				node = node.prev;
				i--;
			}
		}
		return node.data;
	}

	public E getFirst() {
		return element();
	}

	public E getLast() {
		checkEmpty();
		return tail.data;
	}

	public int indexOf(E e) {
		Node node = head;
		int i = 0;
		while (node != null) {
			if (node.data.equals(e)) {
				return i;
			}
			node = node.next;
			i++;
		}
		return -1;
	}

	public int lastIndexOf(E e) {
		Node node = tail;
		int i = size;
		while (node != null) {
			if (node.data.equals(e)) {
				return i;
			}
			node = node.prev;
			i--;
		}
		return -1;
	}

	// �Ƴ�ͷ���
	public E remove() {
		checkEmpty();
		Node node = head.next;// ��������ͷ������һ�����
		E e = head.data;
		if (node == null) { // ���ֻ��һ��ͷ��㣬��ôͷ�������Ϊ�գ�
							// ����ж����㣬��ô��һ������ǰ��Ϊ�գ��ͷ�ͷ��㣬ͷ���ָ����һ�����
			head = null;
		} else {
			node.prev = null;
			head = node;
		}
		size--;
		return e;
	}

	public E remove(int index) {
		checkBounds(index);
		if (index == 0) {
			return remove();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node node = head;
			int i = 0;
			if (index <= size / 2) {
				while (node != null && i < index) {
					node = node.next;
					i++;
				}
			} else {
				node = tail;
				i = size;
				while (node != null && i > index) {
					node = node.prev;
					i--;
				}
			}
			// nodeָ��index����λ�õ�Ԫ��
			E e = node.data;
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node = null;
			return e;
		}
	}

	public E removeLast() {
		checkEmpty(); // ���ȼ�������Ƿ�Ϊ��
		E e = tail.data;
		if (tail.prev != null) { // �������һ�����
			tail.prev.next = null;
			tail = tail.prev;
		} else { // ���ֻ��һ�����
			tail = head = null;
		}
		return e;
	}

	public E removeFirst() {
		return remove();
	}

	public int size() {
		return size;
	}

	public void traverse() {
		Node node = head;
		System.out.print("[");
		while (node != null) {
			System.out.print(node.data + (node == tail ? "" : ","));
			node = node.next;
		}
		System.out.println("]");
	}

	public void traverseReverse() {// �������
		Node node = tail;
		System.out.print("[");
		while (node != null) {
			System.out.print(node.data + (node == head ? "" : ","));
			node = node.prev;
		}
		System.out.println("]");
	}

	class Node {
		E data;
		Node prev;
		Node next;

		public Node(E data, Node prev, Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			list.addFirst(i + 1);
		}
		list.traverse();
		list.remove(2);
		list.traverseReverse();
		/*
		 * System.out.println(list.size); list.traverse(); list.remove();
		 * list.traverseReverse();
		 * 
		 * list.traverse(); list.add(3, 100); list.traverse();
		 * list.addFirst(200); list.traverse(); list.addLast(300);
		 * list.traverse();
		 * 
		 * list.remove(0); list.traverse(); list.removeFirst(); list.traverse();
		 * System.out.println(list.remove()); list.traverse(); // list.clear();
		 * System.out.println(list.size); list.traverse();
		 * System.out.println(list.contains(21)); // list.clear();
		 * System.out.println( list.element());
		 * System.out.println(list.get(19));
		 * System.out.println(list.getFirst());
		 * System.out.println(list.getLast());
		 * System.out.println(list.indexOf(23));
		 * System.out.println(list.indexOf(12));
		 * System.out.println(list.remove(19)); list.traverse();
		 * System.out.println(list.remove()); list.traverse(); list.remove(0);
		 * list.traverse(); list.removeFirst(); list.traverse();
		 * list.removeLast(); list.traverse();
		 */
	}
}
