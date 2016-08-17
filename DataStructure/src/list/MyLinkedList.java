package list;

import java.util.NoSuchElementException;

/**
 * ��������������ͷ��㣬ȫ������Ч���
 * 
 * @author New Song
 *
 * @param <E>
 */
public class MyLinkedList<E> implements MyList<E> {
	private Node head;
	private Node tail;
	private int size;

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	// ��ӵ�β
	public boolean add(E e) {
		if (head == null) {
			head = new Node(e, tail);
			tail = head;
		} else {
			Node newNode = new Node(e, null);
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

	// ����Ԫ�صĺ���λ���±��Ǵ�0~��.����0�ͱ�Ҫ��������
	public void add(int index, E e) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("����Ԫ�ص�λ�ò��Ϸ�:" + index);
		} else if (index == 0) {
			addFirst(e);
		} else if (index == size) {
			add(e);
		} else {
			Node node = head;
			int i = 0;
			while (node != null && i < index - 1) {
				node = node.next;
				i++;
			}
			// ��ʱi == index-1��nodeָ���λ�õ�ǰһ��Ԫ��
			Node newNode = new Node(e, node.next);
			node.next = newNode;
			size++;
		}
	}

	public void addFirst(E e) {
		Node newNode = new Node(e, head);
		head = newNode;
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
		// ��֤index��ָ��Ԫ�����������Χ��
		Node node = head;
		int i = 0;
		while (node != null && i < index) {
			node = node.next;
			i++;
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

	// �Ƴ�ͷ���
	public E remove() {
		checkEmpty();
		Node node = head.next;// ��������ͷ������һ�����
		E e = head.data;
		head = node;
		size--;
		return e;
	}

	// ɾ���ĺ���λ���±�Ϊ0~��-1��0�ͱ�-1Ҫ��������
	public E remove(int index) {
		checkBounds(index);
		if (index == 0) {
			return remove();
		} else if (index == size - 1) {
			return removeLast();
		} else {
			Node node = head;
			int i = 0;
			while (node != null && i < index - 1) {
				node = node.next;
				i++;
			}
			// nodeָ��index-1������Ҫɾ����Ԫ�ص�ǰһ��Ԫ��
			Node target = node.next;
			node.next = target.next;
			E e = target.data;
			target = null;
			return e;
		}
	}

	public E removeLast() {
		Node node = head;
		while (node.next != tail) {
			node = node.next;
		}
		E e = tail.data;
		node.next = null;
		tail = null;
		tail = node;
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

	class Node {
		E data;
		Node next;

		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		for (int i = 0; i < 20; i++) {
			list.add(i + 1);
		}

		list.traverse();
		list.add(3, 100);
		list.traverse();
		list.addFirst(200);
		list.traverse();
		list.addLast(300);
		list.traverse();

		// list.clear(); System.out.println(list.size); list.traverse();

		System.out.println(list.contains(21));
	//	list.clear();

		System.out.println(list.element());
		System.out.println(list.get(19));
		System.out.println(list.getFirst());
		System.out.println(list.getLast());

		System.out.println(list.indexOf(23));
		System.out.println(list.indexOf(12));
		System.out.println(list.remove(19));
		list.traverse();
		System.out.println(list.remove());
		list.traverse();

		list.remove(0);
		list.traverse();
		list.removeFirst();
		list.traverse();
		list.removeLast();
		list.traverse();
	}
}
