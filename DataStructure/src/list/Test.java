package list;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();
		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			list.add(r.nextInt(20));
		}
		list.traverse();
		list.remove(0);
		list.traverse();
		
	} 
}
/*class MyOrderedArrayList<E  extends Comparable<E>> {
	private Object[] value;
	private int elements;
	private static int defaultIncrement = 5;
	public <E extends Comparable<E>> boolean add(E  e) {
		if (elements + 1 > value.length) {
			Object [] dest = new Object [value.length + defaultIncrement];
			System.arraycopy(value, 0, dest, 0, value.length);
			free();
			value = dest;
		}
		//����������㣬������
		int end = elements;
		//endֵΪ����Ԫ�صĸ���
		while(end > 0 && get(end-1).compareTo(e) > 0){
			value[end] = value[end-1];
			end--;
		}
		//�����б�e���Ԫ�ض��Ƶ�e���ұ�
		value[end] = e;
		elements++;
		return true;
	}
	public int indexOf(E e) {
		int start = 0;
		int end = elements - 1;
		int mid = 0;
		while (start < end) {
			mid = (start + end) / 2;
			if (e.compareTo(get(mid)) > 0) {
				start = mid;
				//���e���м�ֵ����ôȡ�Ұ벿��
			} else if (e.compareTo(get(mid)) < 0) {
				end = mid;
				//���e���м�ֵС����ôȡ��벿��
			} else {
				return mid;
			}
		}
		//��start==end �� ����������ֵ��Ϊe�����ʾû���ҵ�
		return -1;
	} 
}*/

