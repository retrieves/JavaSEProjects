package stack;

/**
 * 用数组实现一个栈
 * @author New Song
 *
 * @param <E>
 */
@SuppressWarnings("all")
public class MyStack<E> {
	private Object[] value;
	private int top;
	// top指向栈顶，缺省为-1，是第一个有效元素的前一个位置
	private static int defaultCapacity = 10;
	private static int defaultIncrement = 5;

	public MyStack(int length) {
		value = new Object[length];
		top = -1;
		// 当为-1时栈为空，当为>=0的数时，即为栈顶元素下标，栈中元素的个数是top+1
	}   // top始终指向栈顶

	public MyStack() {
		this(defaultCapacity);
	}

	private void checkBounds() {
		if (top == -1) {
			throw new IndexOutOfBoundsException("栈空");
		}
	}

	public int size() {
		return top + 1;
	}

	public void push(E e) {
		if ((top + 2) > value.length) {
			// top+1是当前元素个数，再+1是将要存放的元素个数
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

	// 从栈底遍历到栈顶
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

	// 将栈中的数值清空，但栈的结构不变
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
