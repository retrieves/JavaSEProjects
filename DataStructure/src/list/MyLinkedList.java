package list;

import java.util.NoSuchElementException;

/**
 * 单向链表，不带有头结点，全部是有效结点
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

	// 添加到尾
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
			throw new IndexOutOfBoundsException("链表元素共" + size + "个，所要访问的元素下标为" + index);
		}
	}

	private void checkEmpty() {
		if (head == null) {
			throw new NoSuchElementException("链表为空");
		}
	}

	// 插入元素的合理位置下表是从0~表长.对于0和表长要单独处理
	public void add(int index, E e) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException("插入元素的位置不合法:" + index);
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
			// 此时i == index-1，node指向该位置的前一个元素
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

	// 返回头
	public E element() {
		checkEmpty();
		return head.data;
	}

	public E get(int index) {
		checkBounds(index);
		// 保证index所指的元素在链表合理范围内
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

	// 移除头结点
	public E remove() {
		checkEmpty();
		Node node = head.next;// 用来保存头结点的下一个结点
		E e = head.data;
		head = node;
		size--;
		return e;
	}

	// 删除的合理位置下表为0~表长-1，0和表长-1要单独处理
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
			// node指向index-1，即所要删除的元素的前一个元素
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
