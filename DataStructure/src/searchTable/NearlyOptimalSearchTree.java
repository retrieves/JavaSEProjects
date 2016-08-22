package searchTable;

import java.util.ArrayList;
import java.util.List;

public class NearlyOptimalSearchTree<E extends Comparable<E>> {
	private TreeNode<E> root;
	private List<E> data;// Ҫ���������
	private int[] sw;// �洢�ۼ�Ȩֵ��
	private int[] weight;// �洢ÿ������Ȩֵ

	public NearlyOptimalSearchTree(List<E> data, int[] weight) {
		this.data = data;
		this.weight = weight;
		initSW(weight);
		root = nearlyOptimal(1, data.size() );
	}

	public TreeNode<E> root() {
		return root;
	}

	private void initSW(int[] weight) {
		int sum = 0;
		sw = new int[weight.length + 1];
		for (int i = 0; i < weight.length; i++) {
			sum += weight[i];
			sw[i + 1] = sum;
		}
		// sw[0] = 0
	}

	// ��ʼ����
	// ÿ�εݹ�ѡ��һ����С�ġ�Pi��������Ϊ���ڵ�
	// ��Pi = |(sw[high]+sw[low-1]) - (sw[i] +sw[i-1])|
	// ��low��i-1��Ϊ������
	// ��i+1��high��Ϊ������
	public TreeNode<E> nearlyOptimal(int low, int high) {
		if (low > high) {
			return null;
		}
		int index = low;
		int dw = sw[high] + sw[low - 1];
		int minDeltaPi = Math.abs(sw[high] - sw[low]);// ��ʼ��minDeltaPiΪ���ֵ
		// ѡ����С�ġ�Pi���õ�iָ��
		for (int i = low + 1; i <= high; i++) {
			if (Math.abs(dw - sw[i] - sw[i - 1]) < minDeltaPi
					|| (Math.abs(dw - sw[i] - sw[i - 1]) == minDeltaPi && weight[i-1] > weight[index-1])) {
				//֮����Ҫi-1 index-1 ����Ϊ����Ӧ������㷨������Ԫ���Ǵ�1��ʼ�洢��
				minDeltaPi = Math.abs(dw - sw[i] - sw[i - 1]);
				index = i;
				// ���������ġ�Pi��ȣ���ôȡȨֵ������Ϊ���ڵ�
			}
		}
		// indexָ���ʵ��Ԫ�ص���һ��
		TreeNode<E> node = new TreeNode<>(data.get(index - 1));
		node.Lchild = nearlyOptimal(low, index - 1);
		node.Rchild = nearlyOptimal(index + 1, high);
		return node;
	}

	public void preOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preOrder(subTree.Lchild);
			preOrder(subTree.Rchild);
		}
	}
	
	public boolean contains(E data){
		return contains(root,data);
	}
	
	//�ҵ�����true��û���ҵ�����false
	private boolean contains(TreeNode<E> node,E data){
		if(node == null){
			return false;
		}
		if(node.data.equals(data) ){
			return true;
		}else{
			if(contains(node.Lchild,data) || contains(node.Rchild,data)){
				return true;
			}else{
				return false;
			}
		}
	}
	public static void main(String[] args) {
		List<Character> data = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			data.add((char) ('A' + i));
		}
		int[] weight = { 1, 1, 2, 5, 3, 4, 4, 3, 5, 2, 1 };
		NearlyOptimalSearchTree<Character> tree = new NearlyOptimalSearchTree<>(data, weight);
		tree.preOrder(tree.root());
		System.out.println();
		System.out.println(tree.contains('C'));
		System.out.println(tree.contains('M'));
	}
	
}

class TreeNode<E extends Comparable<E>> {
	E data;
	TreeNode<E> Lchild;
	TreeNode<E> Rchild;

	public TreeNode(E data) {
		this.data = data;
	}
}