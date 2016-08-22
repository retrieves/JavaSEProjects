package searchTable;

import java.util.Random;

public class MyOrderedArrayList<E extends Comparable<E>> {
	private Object[] value;
	private int elements;
	private static int defaultIncrement = 5;
	private static int defalutCapacity = 10; // 默认容量

	public MyOrderedArrayList() {
		this(defalutCapacity);
	}

	public MyOrderedArrayList(int length) {
		value = new Object[length];
		elements = 0;
	}

	public boolean add(E e) {
		if (elements + 1 > value.length) {
			Object[] dest = new Object[value.length + defaultIncrement];
			System.arraycopy(value, 0, dest, 0, value.length);
			value = dest;
		}
		// 如果容量不足，则扩充
		int end = elements;
		// end值为现有元素的个数
		// 采用插入排序
		while (end > 0 && get(end - 1).compareTo(e) > 0) {
			value[end] = value[end - 1];
			end--;
		}
		// 将所有比e大的元素都移到e的右边
		value[end] = e;
		elements++;
		return true;
	}

	public int size() {
		return elements;
	}

	private void checkBounds(int index) {
		if (index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException("现有元素共" + elements + "个，欲访问元素下标为" + index);
		}
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		checkBounds(index);
		return (E) value[index];

	}

	public int indexOf(E e) {
		int start = 0;
		int end = elements - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (e.compareTo(get(mid)) > 0) {
				start = mid + 1;
				// 如果e比中间值大，那么取右半部分
			} else if (e.compareTo(get(mid)) < 0) {
				end = mid - 1;
				// 如果e比中间值小，那么取左半部分
			} else {
				return mid;
			}
		}
		// 当start>end 且 该索引处的值不为e，则表示没有找到
		return -1;
	}

	public static void main(String[] args) {
		Random r = new Random();
		MyOrderedArrayList<Integer> list = new MyOrderedArrayList<>();
		int integer = 0;
		for (int i = 0; i < 20; i++) {
			integer = r.nextInt(20);
			list.add(integer);
			System.out.print(integer + "  ");
		}
		System.out.println();
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + "  ");
		}
		System.out.println();
		System.out.println(list.get(3));
	}
}
