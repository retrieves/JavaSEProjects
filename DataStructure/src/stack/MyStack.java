package stack;

/**
 * ������ʵ��һ��ջ
 * @author New Song
 *
 * @param <E>
 */
@SuppressWarnings("all")
public class MyStack<E> {
	private Object[] value;
	private int top;
	// topָ��ջ����ȱʡΪ-1���ǵ�һ����ЧԪ�ص�ǰһ��λ��
	private static int defaultCapacity = 10;
	private static int defaultIncrement = 5;

	public MyStack(int length) {
		value = new Object[length];
		top = -1;
		// ��Ϊ-1ʱջΪ�գ���Ϊ>=0����ʱ����Ϊջ��Ԫ���±꣬ջ��Ԫ�صĸ�����top+1
	}   // topʼ��ָ��ջ��

	public MyStack() {
		this(defaultCapacity);
	}

	private void checkBounds() {
		if (top == -1) {
			throw new IndexOutOfBoundsException("ջ��");
		}
	}

	public int size() {
		return top + 1;
	}

	public void push(E e) {
		if ((top + 2) > value.length) {
			// top+1�ǵ�ǰԪ�ظ�������+1�ǽ�Ҫ��ŵ�Ԫ�ظ���
			Object[] dest = new Object[value.length + defaultIncrement];
			System.arraycopy(value, 0, dest, 0, top + 1);
			for (int i = 0; i < top + 1; i++) {
				value[i] = null;
			}
			value = dest;
		}
		top++;
		value[top] = e;

	}

	public E pop() {
		checkBounds();
		Object obj = value[top];
		top--;
		return (E) obj;
	}

	public E peek() {
		checkBounds();
		return (E) value[top];
	}

	// ��ջ�ױ�����ջ��
	public void print() {
		System.out.print("[");
		for (int i = 0; i <= top; i++) {
			System.out.print(value[i] + (i == top ? "]\n" : ","));
		}
	}
	public String traverse(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= top; i++) {
			sb.append(value[i]);
		}
		return sb.toString();
	}
	
	public boolean isEmpty() {
		return top == -1;
	}

	// ��ջ�е���ֵ��գ���ջ�Ľṹ����
	public void clear() {
		for (int i = 0; i <= top; i++) {
			value[i] = null;
		}
		top = -1;
	}

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		System.out.println(stack.size());
		for (int i = 0; i < 30; i++) {
			stack.push(i + 1);
		}
		stack.traverse();
		for (int i = 20; i >= 0; i--) {
			stack.push(i);
		}
		stack.traverse();

		System.out.println(stack.isEmpty());
		for (int i = 0; i < 50; i++) {
			System.out.print(stack.pop() + ",");
		}
		System.out.println();
		stack.traverse();
		System.out.println(stack.size());
	}
}
