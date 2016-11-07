package queue;
/**
 * ����ʵ��ѭ�����У�û�п����Ԫ�أ�ȫ������ЧԪ��
 * �жϿ���ʹ��elements��Ա������ָʾ
 * @author New Song
 *
 * @param <E>
 */
@SuppressWarnings("all")
public class MyQueue<E> {
	private Object[] value;
	private int front;// ָ��ͷ���
	private int rear;// ָ��β������һ�����
	private int elements;// ��ЧԪ�ظ���

	public MyQueue(int length) {
		if (length <= 0) {
			throw new IndexOutOfBoundsException("���ȱ������0");
		}
		value = new Object[length];
		front = 0;
		rear = 0;
		elements = 0;
	}
	
	//���
	public void enQueue(E e) {
		if (isFull()) {
			return;
		}
		value[rear] = e;
		rear = (rear + 1) % value.length;
		elements++;
	}
	
	public E front(){
		int ptr = rear;
		for(int i = elements-1;i>=0;--i){
			ptr = (ptr-1)% value.length;
		}
		return (E) value[rear];
	}
	
	//����
	public E deQueue() {
		if (isEmpty())
			return null;
		Object obj = value[front];
		front = (front + 1) % value.length;
		elements--;
		return (E)obj;
	}

	public E peek() {
		if (isEmpty())
			return null;
		return (E)value[front];
	}

	public boolean isEmpty() {
		return elements == 0;
		// һ��ʹ��elements���ж��Ƿ����������ʹ��rear��front֮��Ĺ�ϵ���ж�
	}

	public boolean isFull() {
		return elements == value.length;
	}

	public void destroy() {
		int j = front;
		for (int i = 0; i < elements; i++) {
			value[j] = null;
			j = (j + 1) % value.length;
		}
		value = null;
		rear = front = 0;
		elements = 0;
	}

	public int size() {
		return elements;
	}

	public void traverse() {
		System.out.print("[");
		int j = front;
		for (int i = 0; i < elements; i++) { // ����������elements������
			System.out.print(value[j] + (i + 1 == elements ? "" : ","));
			j = (j + 1) % value.length;
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		MyQueue<Integer> queue = new MyQueue<>(10);
		for (int i = 0; i < 20; i++) {
			queue.enQueue(i + 1);
		}
//		System.out.println(queue.isFull());
//		System.out.println(queue.size());
		// queue.destroy();
		queue.traverse();
		System.out.println(queue.front());
	}
}
