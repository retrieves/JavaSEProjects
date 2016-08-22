package searchTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;


public class MyChainedHashTable<E> {
	private LinkedList<E>[] value;
	private int currentSize;
	public static final int DEFAULT_TABLE_SIZE = 11;
	
	public MyChainedHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	@SuppressWarnings("unchecked")
	public MyChainedHashTable(int size) {
		value = new LinkedList[size];
		size = 0;
	}

	public void clear() {
		for (int i = 0; i < value.length; i++) {
			value[i] = null;
		}
		this.currentSize = 0;
	}

	public boolean add(E data) {
		if (data == null || contains(data)) {
			return false;
		}
		int pos = hash(data);
		if(value[pos] == null){
			value[pos] = new LinkedList<>();
		}
		value[pos].add(data);
		currentSize++;
		return true;
	}

	public void remove(E data) {
		int pos = hash(data);
		if(value[pos] != null){
			value[pos].remove(data);
		}
		currentSize--;
	}

	public boolean contains(E data) {
		int pos = hash(data);
		if (value[pos] != null && value[pos].contains(data)) {
			return true;
		} else {
			return false;
		}
	}

	private int hash(E data) {
		return data.hashCode() % value.length;
	}

	public void traverse() {
		for (int i = 0; i < value.length; i++) {
			if (value[i] != null) {
				Iterator<E> it  = value[i].iterator();
				while(it.hasNext()){
					System.out.print(it.next()+" ");
				}
			}
		}
		System.out.println();
	}

	
	public static void main(String[] args) {
		MyChainedHashTable<String> hashtable = new MyChainedHashTable<>();
		Random r = new Random();
		for (int i = 0; i < 30; i++) {
			hashtable.add(""+(char)(r.nextInt(60)+65)+(char)(r.nextInt(60)+65));
		}
		hashtable.traverse();
	}
}
