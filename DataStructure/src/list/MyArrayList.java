package list;

import java.util.Comparator;

@SuppressWarnings("all")
public class MyArrayList<E> implements MyList<E>{
	private Object[] value;
	private int elements; //有效元素个数
	private static int defalutCapacity = 10; //默认容量
	private static int defaultIncrement = 5; //默认增量

	public MyArrayList() {
		this(defalutCapacity);
	}

	public MyArrayList(int length) {
		value = new Object[length];
		elements = 0;
	}

	private void checkBounds(int index) {
		if (index >= elements || index < 0 ) {
			throw new ArrayIndexOutOfBoundsException("现有元素共" + elements + "个，欲访问元素下标为" + index);
		}
	}
	
	public  void ensureCapacity(int minCapacity){
		Object [] dest = new Object[minCapacity];
		System.arraycopy(value, 0, dest, 0, elements);
		free();
		value = dest;
	}

	public boolean add(E e) {
		if (elements + 1 > value.length) {
			ensureCapacity(value.length + defaultIncrement);
		}
		value[elements] = e;
		elements++;
		return true;
	}
	
	public void add(int index,E e){
		if(index > elements){
			throw new ArrayIndexOutOfBoundsException("插入元素的位置不合法:"+index);
		}
		//index最大值为元素个数，表示插到尾部
		Object []dest = new Object[value.length+1];
		System.arraycopy(value, 0, dest, 0, index);
		System.arraycopy(value, index, dest, index+1, elements-index);
		dest[index] = e;
		free();
		value = dest;
		elements++;
	}
	
	public E get(int index) {
		checkBounds(index);
		return (E)value[index];

	}

	public boolean contains(E e) {
		for (int i = 0; i < value.length; i++) {
			if (value[i].equals(e))
				return true;
		}
		return false;
	}

	public int indexOf(E e) {
		for (int i = 0; i < elements; i++) {
			if (value[i].equals(e))
				return i;
		}
		return -1;
	}

	public boolean isEmpty() {
		if (elements == 0)
			return true;
		return false;
	}

	public E remove(int index) {
		checkBounds(index);
		Object obj = get(index);
		Object[] dest = new Object[value.length - 1];
		System.arraycopy(value, 0, dest, 0, index);
		System.arraycopy(value, index + 1, dest, index, elements - index - 1);
		free();
		value = dest;
		elements--;
		return (E)obj;
	}

	public boolean remove(E e) {
		int index = -1;
		if ((index = indexOf(e)) != -1) {
			remove(index);
			return true;
		} else {
			return false;
		}
	}
	
	private void free(){
		for (int i = 0; i <elements; i++) {
			value[i] = null;
		}
	}
	
	public void clear(){
		for (int i = 0; i < elements; i++) {
			value[i] = null;
		}
		elements = 0;
	}
	
	public int size(){
		return elements;
	}
	
	public Object [] toArray(){
		Object [] dest = new Object[elements];
		System.arraycopy(value, 0, dest, 0, elements);
		return dest;
	}
	
	public E set(int index,E e){
		checkBounds(index);
		Object oldValue = value[index];
		value[index] = e;
		return (E)oldValue;
	}
	
	public int lastIndexOf(E e){
		for(int i = elements-1;i>=0; i--){
			if(value[i].equals(e))
				return i;
		}
		return -1;
	}
	
	public void traverse(){
		System.out.print("[");
		for (int i = 0; i < elements; i++) {
			System.out.print(value[i]+(i==elements-1?"]\n":","));
		}
	}
	public  void sort(Comparator<E> comp){
		int k = 0;
		for (int i = 0; i < value.length-1; i++) {
			k = i;
			for (int j = i+1; j < value.length; j++) {
				if(comp.compare((E)value[k],(E)value[j]) > 0){
					k = j;
				}
			}
			if(k != i){
				Object temp = value[k];
				value[k] = value[i];
				value[i] = temp;
			}
		}
	}
}

