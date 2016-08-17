package tree;

import java.util.LinkedList;

/**
 * �����ֵܱ�ʾ��
 * 
 * @author New Song
 *
 * @param <E>
 */
public class Tree<E> {
	private ChildSiblingNode<E> root;
	private LinkedList<ChildSiblingNode<E>> pathList;
	// ��������Ӹ��ڵ㵽����Ҷ�ӽ�������·��

	public Tree() {
	}

	public Tree(ChildSiblingNode<E> root) {
		this.root = root;
	}

	// �����
	public int depth() {
		return depth(root);
	}

	private int depth(ChildSiblingNode<E> node) {
		if (node == null) {
			return 0;
		} else {
			int depthOfChild = depth(node.firstChild);
			int depthOfSibling = depth(node.nextSibling);
			return (depthOfChild + 1) > depthOfSibling ? depthOfChild + 1 : depthOfSibling;
		}
	}

	// �����/ɭ�֣�תΪ�˶�����֮�󣩴Ӹ���㵽ÿ��Ҷ�ӽ���·��
	// ������������
	// ��һ����ǰ���ΪҶ�ӽ��ֻ��Ҫ����ָ����Ϊ�ռ��ɣ�����Ҫ��ָ����Ϊ�գ���Ϊ��ָ�����ʾ�������ֵ�
	// �ڶ�����Ӧ��������ʵڶ�������ʱ�����ĵ�һ����������������������Ӧ��������ɾ����ǰ���
	public void printAllTreePaths() {
		if (pathList == null) {
			pathList = new LinkedList<>();
		}
		printTreePath(root);
		pathList.clear();
		;
	}

	// �ݹ�+ѭ��
	private void printTreePath(ChildSiblingNode<E> node) {
		// ������Ϊѭ����ÿ��ѭ������һ������
		// ���nodeֵû�иı�Ļ���ʹ��while�ͻ������ѭ��
		// �ݹ���ò�û�иı������ֵ
		// �ݹ�ʵ�����һ��·����ѭ��ʵ�ֳ�������·��
		while (node != null) {
			pathList.addLast(node);
			if (node.firstChild == null) {
				// ��ָ����Ϊ�ձ�ʾ��ΪҶ�ӽ�㣬�����·��
				for (ChildSiblingNode<E> treeNode : pathList) {
					System.out.print(treeNode.data + " ");
				}
				System.out.println();
				// �������Ӹ��ڵ㵽Ҷ�ӵ�������

			} else {
				printTreePath(node.firstChild);
				// ��������Ѿ�����˵�һ��·����
			}
			// ����Ϊ���������������ڽ���ڶ�������
			pathList.removeLast();
			node = node.nextSibling;
			// node��Ϊ�ڶ��������ĸ�
		}
		// �ݹ�����һ��·����ѭ����ת���ֵ�
	}
	
	// �������Ĵ洢�ṹ������һ����������õ�������Ե��ַ�����ÿһ��������ÿո����
	public static Tree<Character> createTree(String pairStr) {
		LinkedList<ChildSiblingNode<Character>> queue = new LinkedList<>();
		ChildSiblingNode<Character> root = null;
		ChildSiblingNode<Character> node = null;
		ChildSiblingNode<Character> parentNode = null;
		ChildSiblingNode<Character> lastChild = null;
		char parent = 0;
		char child = 0;
		for (int i = 0; i < pairStr.length()/3+1; i++) {
			parent = pairStr.charAt(i*3);
			child = pairStr.charAt(i*3+1);
			if(child == '#'){
				break;
			}
			node = new ChildSiblingNode<>(child);
			queue.addLast(node);
			if(parent == '#'){
				root = node;
			}else{
				//������㲢��ӣ�ֻ�е���ǰ��㲻Ϊ���ڵ�ʱ��������˫�׽��
				while((parentNode = queue.getFirst()).data != parent){
					queue.removeFirst();
				}
				//������parentNodeָ��ǰ����˫�׽��
				if(parentNode.firstChild == null){
					parentNode.firstChild = node;
					lastChild = node;
				}else{
					lastChild.nextSibling = node;
					lastChild = node;
				}
				//lastChild����ָ��˫�׽��ոս�����ϵ�ĺ��ӽ��
				//���˫�׽���firstChildΪ�գ���ô����Ϊ��ǰ��㣬����lastChildҲָ��ǰ��㣨��һ�����ӣ�
				//�����Ϊ�գ���ʾ�Ѿ��к����ˣ���ô��lastChild�����ҵ��亢�ӣ��������ֵ�ָ��ָ��ǰ���
			}
		}
		return new Tree<>(root);
	}
	
	public ChildSiblingNode<E> root(){
		return root;
	}
	//�����ȸ������͵ȼ��ڶ��������������
	//�ȷ��ʸ��ڵ㣬�ڰ��մ������ҵ�˳���ȸ�������������������ɭ�֣�
	public void preRoot(ChildSiblingNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preRoot(subTree.firstChild);
			preRoot(subTree.nextSibling);
		}
	}
	//���ĺ�������͵ȼ��ڶ��������������
	//�Ⱥ��������һ���������ٷ��ʸ��ڵ㣬Ȼ��������Һ��������������
	public void postRoot(ChildSiblingNode<E> subTree) {
		if (subTree != null) {
			postRoot(subTree.firstChild);
			System.out.print(subTree.data + " ");
			postRoot(subTree.nextSibling);
		}
	}
	public static void main(String[] args) {
		String str = "#A AB AC AD CE CF FG ##";
		Tree<Character> tree = createTree(str);
		System.out.print("�����ȸ�������");
		tree.preRoot(tree.root());
		System.out.print("\r\n���ĺ��������");
		tree.postRoot(tree.root());
		System.out.println("\r\n����������ڵ㵽����Ҷ�ӽ���·����");
		tree.printAllTreePaths();
	}
}


class ChildSiblingNode<E> {
	E data;
	ChildSiblingNode<E> firstChild;// ��һ������
	ChildSiblingNode<E> nextSibling;// �Ҳ���ֵ�
	
	public ChildSiblingNode(E data) {
		this.data = data;
	}
}
