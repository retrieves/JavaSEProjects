package graph;
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * �����޸ĵ���Ӻͳ��Ӳ�������ʽ����
 * 
 * @author New Song
 *
 * @param <E>
 */
public class LinkedQueue {
	private Node front;
	private Node prev;
	private Node rear;
	private int size;
	
	//frontָ��ͷ���
	//rearָ�����һ�����
	
	public LinkedQueue() {
		front = null;
		rear = front;
		size = 0;
	}

	private void checkEmpty() {
		if (front == null) {
			throw new NoSuchElementException("����Ϊ��");
		}
	}

	// ���ʱ������ӽ���prevָ����ָ����иոճ��ӵĽ�㣨���е�prevָ����ָ��Ľ�㣩
	public boolean enQueue(int e) {
		if (isEmpty()) {
			front = new Node(e, prev,rear);
			rear = front;
			// ֻ��һ�����ʱ������ͷ����β
		} else {
			Node newNode = new Node(e,prev, null);
			rear.next = newNode;
			rear = newNode;
		}
		size++;
		return true;
	}
	
	//����ʱֻ�ƶ�frontָ�룬��ɾ��Ԫ��
	public int deQueue() {
		checkEmpty();
		int e = front.data;
		prev = front;
		front = front.next;
		size--;
		return e;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		Node node = front;
		Node temp = null;
		while (node != null) {
			temp = node.next;
			node = null;
			node = temp;
		}
		front = null;
		rear = null;
		size = 0;
	}
	
	public LinkedList<Integer> path(){
		LinkedList<Integer> list = new LinkedList<>();
		Node node = rear;
		list.addFirst(node.data);
		while(node.prev != null){
			node = node.prev;
			list.addFirst(node.data);
		}
		return list;
	}
	
	public int peek() {
		checkEmpty();
		return front.data;
	}

	public int size() {
		return size;
	}
	
	class Node {
		int data;
		Node prev;
		Node next;

		public Node(int data, Node prev,Node next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
}
