package searchTable;

import java.util.Random;

public class MyOrderedArrayList<E extends Comparable<E>> {
	private Object[] value;
	private int elements;
	private static int defaultIncrement = 5;
	private static int defalutCapacity = 10; // Ĭ������

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
		// ����������㣬������
		int end = elements;
		// endֵΪ����Ԫ�صĸ���
		// ���ò�������
		while (end > 0 && get(end - 1).compareTo(e) > 0) {
			value[end] = value[end - 1];
			end--;
		}
		// �����б�e���Ԫ�ض��Ƶ�e���ұ�
		value[end] = e;
		elements++;
		return true;
	}

	public int size() {
		return elements;
	}

	private void checkBounds(int index) {
		if (index >= elements || index < 0) {
			throw new ArrayIndexOutOfBoundsException("����Ԫ�ع�" + elements + "����������Ԫ���±�Ϊ" + index);
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
				// ���e���м�ֵ����ôȡ�Ұ벿��
			} else if (e.compareTo(get(mid)) < 0) {
				end = mid - 1;
				// ���e���м�ֵС����ôȡ��벿��
			} else {
				return mid;
			}
		}
		// ��start>end �� ����������ֵ��Ϊe�����ʾû���ҵ�
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
