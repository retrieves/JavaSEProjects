package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree<E> {
	private HuffmanNode<E> root;
	public HuffmanTree() {
		// TODO Auto-generated constructor stub
	}
	public HuffmanTree(HuffmanNode<E> root) {
		this.root = root;
	}
	
	public static <E> HuffmanTree<E> createHuffmanTree(List<HuffmanNode<E>> list){
		
		HuffmanNode<E> Lchild = null;
		HuffmanNode<E> Rchild = null;
		HuffmanNode<E> parent = null;
		while(list.size() > 1 ){
			Collections.sort(list);//�����н��������򣬵õ���С����Ľ������
			Lchild = list.remove(0);
			Rchild = list.remove(0);//ȡ��Ȩֵ��͵���������
			parent = new HuffmanNode<>(null, Lchild.weight+Rchild.weight, Lchild, Rchild);
			list.add(parent);
		}
		return new HuffmanTree<>(list.get(0));
	}
	//���������������ֻ���Ҷ�ӽ��
	public void preOrder(HuffmanNode<E> subTree) {
		if (subTree != null) {
			if(subTree.data != null){
				System.out.print(subTree+"  ");
			}
			preOrder(subTree.Lchild);
			preOrder(subTree.Rchild);
		}
	}
	
	public HuffmanNode<E> root(){
		return root;
	}
	
	public static void main(String[] args) {
		char [] data = {'A','B','C','D'};
		char [] weights = {25,10,45,20};
		List<HuffmanNode<Character>> list = new ArrayList<>();
		for (int i = 0; i < weights.length; i++) {
			list.add(new HuffmanNode<>(data[i], weights[i]));
		}
		HuffmanTree<Character> tree = createHuffmanTree(list);
		tree.preOrder(tree.root());
 	}
}

class HuffmanNode<E> implements Comparable<HuffmanNode<E>>{
	//�������֮����Ҫ���бȽϣ����ǽ���ֵ��Ҫ�Ƚϣ�
	//ֻ��Ҷ�ӽ�����data��ֵ���������û��data��ֵ
	//���н�㶼��weight
	E data;
	int weight;//Ȩֵ
	HuffmanNode<E> Lchild;
	HuffmanNode<E> Rchild;
	public HuffmanNode(E data,int weight) {
		this.data = data;
		this.weight = weight;
	}
	
	public HuffmanNode(E data,int weight,HuffmanNode<E> Lchild,HuffmanNode<E> Rchild) {
		this(data,weight);
		this.Lchild = Lchild;
		this.Rchild = Rchild;
	}
	
	@Override
	public int compareTo(HuffmanNode<E> node) {
		return weight > node.weight ? 1:(weight == node.weight? 0:-1);
	}
	//������С����
	@Override
	public String toString() {
		return "data:"+data + ",weight:"+weight;
	}
}
