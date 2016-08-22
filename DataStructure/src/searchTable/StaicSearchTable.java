package searchTable;

public class StaicSearchTable<E extends Comparable<E>> {
	private Object[] value;
	private int elements; //��ЧԪ�ظ���
	private static int defalutCapacity = 10; //Ĭ������
	public StaicSearchTable() {
		this(defalutCapacity);
	}

	public StaicSearchTable(int length) {
		value = new Object[length];
		elements = 0;
	}
	
	public boolean add(E e) {
		value[elements] = e;
		elements++;
		return true;
	}
	
	public int indexOf(E e) {
		for (int i = 0; i < elements; i++) {
			if (value[i].equals(e))
				return i;
		}
		return -1;
	}
	//ע�����ֲ���Ҫ������Ľṹ�ǲ�һ����
	public int indexOfWithGuard(E e){
		value[0] = e;
		int i = elements;
		while(!value[i].equals(e)){
			i--;
		}
		return i;
	}
}
