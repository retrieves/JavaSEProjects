package sort;

import java.util.Arrays;

public class Sort {

	public static <T extends Comparable<T>> void insertSort(T[] arr) {
		int sortedIndex = 0;
		T guard = null;
		for (int unSortedIndex = 1; unSortedIndex < arr.length; unSortedIndex++) {
			// i���������ĵ�һ��Ԫ�ؿ�ʼ(Ĭ�Ͽ�ʼʱ��Ϊֻ��һ��Ԫ�أ����Ե�һ��Ԫ���������)
			guard = arr[unSortedIndex];// ���������ĵ�һ��Ԫ�ط��ڸ�����
			sortedIndex = unSortedIndex - 1; // j�������������һ��Ԫ��
			while (sortedIndex >= 0 && arr[sortedIndex].compareTo(guard) > 0) {
				// ��������������һ��Ԫ�رȸ��ڴ���ô���ƣ�ֱ�������ҵ��ʺ�����λ��Ϊֹ
				arr[sortedIndex + 1] = arr[sortedIndex];
				sortedIndex--;
			}
			arr[sortedIndex + 1] = guard; // j+1���λ�ÿ��˳�����Ȼ�󽫸��ڸ���j-1���λ����
		}
	}

	public static <T extends Comparable<T>> void binaryInsertSort(T[] arr) {
		int low = 0, high = 0, mid = 0;
		T guard = null;
		for (int unSortedIndex = 1; unSortedIndex < arr.length; unSortedIndex++) {
			// i���������ĵ�һ��Ԫ�ؿ�ʼ(Ĭ�Ͽ�ʼʱ��Ϊֻ��һ��Ԫ�أ����Ե�һ��Ԫ���������)
			guard = arr[unSortedIndex];// ���������ĵ�һ��Ԫ�ط��ڸ�����
			low = 0;
			high = unSortedIndex - 1;// low~highָ������������
			while (low <= high) {
				mid = (low + high) / 2;
				if (guard.compareTo(arr[mid]) > 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			// ������lowָ�������Ԫ�ص�λ�ã���Ҫ��low��֮���Ԫ��(�������������һ��Ԫ��unSortedIndex-1)�����
			for (int i = unSortedIndex - 1; i >= low; i--) {
				arr[i + 1] = arr[i];
			}
			arr[low] = guard;
		}
	}

	public static <T extends Comparable<T>> void shellSort(T[] arr) {
		int movIdx = 0;// �ƶ�ʱ�������
		T guard = null;
		// �����ӳ��ȵ�һ�뿪ʼ�����ջ����1������0ʱ����
		for (int increment = arr.length / 2; increment > 0; increment /= 2) {
			for (int i = increment; i < arr.length; i++) {
				// i�ӵ�һ��ĵڶ���Ԫ�أ���һ����ڶ��������������ʼ
				if (arr[i].compareTo(arr[i - increment]) < 0) {// ����ı�ǰ���С����Ҫ����
					guard = arr[i];// ������Ԫ��
					for (movIdx = i - increment; movIdx >= 0 && guard.compareTo(arr[movIdx]) < 0; movIdx -= increment) {
						arr[movIdx + increment] = arr[movIdx];
					} // ��Ծʽ���ƶ�
					arr[movIdx + increment] = guard;
				}
			}
		}
	}

	public static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
		// ֻ���ж���һ����Ԫ�زŽ�������
		if (left < right) {
			int low = left, high = right;
			T pivotKey = arr[low];// ����
			while (low < high) {
				while (low < high && arr[high].compareTo(pivotKey) >= 0) {
					high--;
				}
				// high�����ƶ���ֱ������С�������Ԫ�أ�Ȼ��high��ָ��Ԫ�ظ���lowָ��Ԫ�أ�Ҳ�����Ѿ�����������Ԫ�أ����ᶪʧ��
				arr[low] = arr[high];
				while (low < high && arr[low].compareTo(pivotKey) <= 0) {
					low++;
				}
				// low�����ƶ���ֱ���������������Ԫ�أ�Ȼ��low��ָ��Ԫ�ظ���high��ָ��Ԫ��
				arr[high] = arr[low];
			}
			// �˳�ʱlow��high��ָ��ͬһԪ�أ��м��һ��λ�ã��������ḳ�����Ԫ��
			// ����������������Ԫ�ض�������С�������������Ԫ�ض��������
			arr[low] = pivotKey;
			quickSort(arr, left, low - 1);// �ֶ���֮
			quickSort(arr, low + 1, right);
		}
	}

	public static <T extends Comparable<T>> void selectSort(T[] arr) {
		int k = 0;
		T t = null;
		// ��������ǰ���������ں�
		for (int i = 0; i < arr.length - 1; i++) {// ���һ��Ԫ���Զ�����
			k = i;// k����i��ֵ��ָ�������������һ��Ԫ��
			for (int j = i + 1; j < arr.length; j++) {// jָ���������ĵ�һ��Ԫ��
				if (arr[k].compareTo(arr[j]) > 0) {
					k = j;// kʼ��ָ����СԪ��
				}
			}
			if (k != i) {
				t = arr[i];
				arr[i] = arr[k];
				arr[k] = t;
			}
			// ��������������СԪ�غ������������һ��Ԫ��
		}
	}

	public static <T extends Comparable<T>> void headpSort(T[] arr) {
		T t = null;
		for (int i = arr.length / 2; i >= 0; i--) {
			maxHeap(arr, arr.length, i);// �����󶥶ѣ�ÿ�ε��ö�����һ�ö��������������������ô��������
		}
		for (int i = arr.length - 1; i >= 1; i--) {
			t = arr[0];
			arr[0] = arr[i];
			arr[i] = t;
			maxHeap(arr, i, 0);// �Ƚ��Ѷ�Ԫ����ѵ������������һ��Ԫ�ؽ�����Ȼ�����µ���Ϊ��(ÿ��ѭ����ʵ����һ��Ԫ�ص�������˵����ķ�Χ��һ)
		}
	}

	private static <T extends Comparable<T>> void maxHeap(T[] arr, int heapSize, int root) {
		T t = null;
		int Lchild = root * 2 + 1;
		int Rchild = root * 2 + 2;
		// Lchild��Rchildָ��headָ��Ԫ�ص���������
		int maxIndex = root;
		// maxIndexָ����ڵ�����Һ��ӽ���е����ֵ
		if (Lchild < heapSize && arr[Lchild].compareTo(arr[maxIndex]) > 0) {
			maxIndex = Lchild;
		}
		if (Rchild < heapSize && arr[Rchild].compareTo(arr[maxIndex]) > 0) {
			maxIndex = Rchild;
		}
		if (maxIndex != root) {
			// �����������ڵ�����������е����ֵ�������ڵ�
			t = arr[maxIndex];
			arr[maxIndex] = arr[root];
			arr[root] = t;
			// �����ǰ�������ĵ���Ӱ����������������������ô��������
			// ���������������͵��������������������������������͵���������������
			maxHeap(arr, heapSize, maxIndex);
		}
	}

	// ʵ�ִ�low��high������
	public static <T extends Comparable<T>> void mergeSort(T[] arr, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			mergeSort(arr, low, mid);
			mergeSort(arr, mid + 1, high);
			merge(arr,low,mid,high);
		}
	}

	// ʵ��low~mid �� mid+1~high�ĺϲ����ϲ����������
	public static <T extends Comparable<T>> void merge(T[] arr, int low, int mid, int high) {
		T[] mergeArr = arr.clone();
		int left = low;// ���ߵ�ָ��
		int right = mid + 1;// �Ұ�ߵ�ָ��
		int mergeIndex = low;// ָ��鲢�����ָ��
		while (left <= mid && right <= high) {
			if (arr[left].compareTo(arr[right]) < 0) {
				// ���ߵ�Ԫ��С���Ұ�ߵ�Ԫ��
				mergeArr[mergeIndex] = arr[left];
				left++;
			} else {
				mergeArr[mergeIndex] = arr[right];
				right++;
			}
			mergeIndex++;
		}
		// �����߻��Ұ�߻�û�зŵ��鲢�����еķ���
		while (left <= mid) {
			mergeArr[mergeIndex] = arr[left];
			mergeIndex++;
			left++;
		}
		// �����߻��Ұ�߻�û�зŵ��鲢�����еķ���
		while (right <= high) {
			mergeArr[mergeIndex] = arr[right];
			mergeIndex++;
			right++;
		}
		//���鲢�����е�Ԫ�ظ��ƻ�ԭ���飬ע������±�����ͬ��
		while(low <= high){
			arr[low] = mergeArr[low];
			low++;
		}
	}
	//��������radix�ǻ������ؼ��ָ�������digit�����ֵ����λ��
	//������������������
	public static void radixSort(int [] arr,int radix,int digit){
		int[] count = new int[radix];//���ڼ���ÿ��������Ӧ�ĸ���Ԫ����ռbucket������λ��
		int[] bucket = new int[arr.length];//���ڴ��һ������֮�������
		int rate = 1;//rate�����������ݵĻ����õ�
		int key = 0;//key���������������
		for (int d = 1; d <= digit; d++) {
			for (int i = 0; i < count.length; i++) {
				count[i] = 0;
			}//ÿ�α���ǰ���count����
			//�������е����ݵĻ������м������õ�ÿ��������Ԫ�ظ�����Ϊ���������ǵ��±��ұ߽磩��׼��
			for (int i = 0; i < bucket.length; i++) {
				key = arr[i]/rate % radix;//key����arr��i�����Ԫ�صĵ�����dλ��ֵ
				count[key]++;
			}
			for (int i = 1; i < count.length; i++) {
				count[i] += count[i-1];//�õ�ÿ��������ŵ��ҽ磬����Ϊi�����count[i]~count[i+1]-1��
			}
			//Ϊ�˱���������ȶ��ԣ�����ʮλ����󱣳�ʮλ��ͬʱ��λ��˳�򣩣��Ӻ���ǰ����
			for (int i = arr.length-1; i >= 0 ; i--) {
				key = arr[i]/rate % radix;
				bucket[count[key]-1] = arr[i];
				count[key]--;
			}
			//��bucket�ݴ���������¸���arr
			for (int i = 0; i < arr.length; i++) {
				arr[i] = bucket[i];
			}
			rate*=10;//�������Ǹ�λ����ô�´ξ���ʮλ��rate*10
		}
	}

	public static void main(String[] args) {
		int[] arr = {  50, 123, 543, 187, 49, 30, 0, 2, 11, 100 };
		Sort.radixSort(arr,10,3);
		System.out.println(Arrays.toString(arr));
	}
}
