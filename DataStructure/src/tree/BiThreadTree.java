package tree;

/**
 * ���������������� ���췽ʽ�ǰ����������������� Ȼ������������ �����Բ������������ʽ���б���
 * 
 * @author New Song
 *
 * @param <E>
 */
enum PointerTag {
	Link, Thread
}

public class BiThreadTree<E extends Comparable<E>> {
	private ThreadTreeNode<E> head;
	private ThreadTreeNode<E> root;
	private ThreadTreeNode<E> pre;
	private static int index = -1;

	public BiThreadTree(ThreadTreeNode<E> root) {
		this.root = root;
	}

	// �������������ʽ����һ����ͨ�Ķ�������û��������
	// ע�⣬index
	// ������ȫ�ֱ���/��̬����������Ǿֲ���������ô�ڵݹ�����У���ʹindexֵ�޸ģ�Ҳ���ڷ��ص�ʱ���Ϊԭ����ֵ��ÿ�η���ʱ���оֲ�������ջ���ָ�ֵ��
	// preҲ��������������Ҫ���ñ���������޸�ֵ�����������Ҫ���ñ�������Ϊȫ�ֱ���
	public static BiThreadTree<Character> createThreadTree(String preStr) {
		char[] pre = preStr.toCharArray();
		BiThreadTree<Character> tree = new BiThreadTree<>(createSubTree(pre));
		index = -1;
		return tree;
	}

	private static ThreadTreeNode<Character> createSubTree(char[] pre) {
		index++;
		ThreadTreeNode<Character> subTree = null;
		if (pre[index] == '#') {
			subTree = null;
		} else {
			subTree = new ThreadTreeNode<>(pre[index]);
			subTree.Lchild = createSubTree(pre);
			subTree.Rchild = createSubTree(pre);
		}
		return subTree;
	}

	// �������
	public void inOrderThreadTraverse() {
		ThreadTreeNode<E> node = head.Lchild;
		while (node != head) {
			// node ָ����ڵ㣬������������Ĺ������ƶ�������������׶�
			while (node.LTag == PointerTag.Link) {
				node = node.Lchild;
			}
			System.out.print(node.data + " ");
			// ��������ķ�ʽ���б���
			while (node.RTag == PointerTag.Thread && node.Rchild != head) {
				node = node.Rchild; // �൱��node = node.next
				System.out.print(node.data + " ");
				// ע��������Ҫ���������������ٷ���
				// ����ȷ�������������������ô������ܽ����´�ѭ�������޷�������������������
			}
			// ��ʱҪô�Ѿ�������ϣ�Ҫô�����������ڣ���Ҫ�����ƶ�������������׶�
			node = node.Rchild;
		}
	}
	
	
	ThreadTreeNode<E> first(ThreadTreeNode<E> node){
		while (node.LTag == PointerTag.Link) {
			node = node.Lchild;
		}
		return node;
	}
	
	ThreadTreeNode<E> next(ThreadTreeNode<E> node){
		ThreadTreeNode<E> ptr = node.Rchild;
		if(ptr == head){
			return head;
		}
		if (node.RTag == PointerTag.Link ){
			while (ptr.LTag == PointerTag.Link) {
				ptr = ptr.Lchild;
			}
		}
		return ptr;
	}
	
	// �������
	public void inOrderThreadTraverse2() {
		ThreadTreeNode<E> node = null;
		for(node = first(head.Lchild);node != head;node = next(node)){
			System.out.print(node.data + " ");
		}
	}
	
	/**
	 * ����ͷ��㲢������
	 */
	public void inOrderThreading() {
		head = new ThreadTreeNode<>();
		head.LTag = PointerTag.Link;
		head.RTag = PointerTag.Thread;
		head.Rchild = head;
		// ����ͷ��㣬��ָ����ָ����ڵ㣬��ָ����ָ������
		if (root == null) {
			head.Lchild = head;
		} else {
			head.Lchild = root;
			pre = head;
			// pre��ʼֵΪhead�����㽫��һ��������ָ������Ϊhead
			inThreading(root);
			// ������������preָ�����һ�����
			pre.RTag = PointerTag.Thread;
			pre.Rchild = head;
			head.Rchild = pre;
			// ����ָ����
		}
	}

	// ��������ǰ���������������
	public void inThreading(ThreadTreeNode<E> node) {
		if (node != null) {
			inThreading(node.Lchild);
			// ���뵽����������׶�
			if (node.Lchild == null) {
				node.LTag = PointerTag.Thread;
				node.Lchild = pre;
			}
			// �������ڵ�ǰ�����н��д������������´ε����н��д���
			// head��RchildҪ��һ��ʼ�ǿ�
			if (pre.Rchild == null) {
				pre.RTag = PointerTag.Thread;
				pre.Rchild = node;
			}
			pre = node;
			inThreading(node.Rchild);
		}
	}

	public static void main(String[] args) {
		String pre = "ABDH##I##EJ###CF##G##";
		BiThreadTree<Character> tree = createThreadTree(pre);
		tree.inOrderThreading();
		tree.inOrderThreadTraverse();
		System.out.println();
		tree.inOrderThreadTraverse2();
		
	}
}

class ThreadTreeNode<E extends Comparable<E>> {
	E data;
	ThreadTreeNode<E> Lchild;
	ThreadTreeNode<E> Rchild;
	PointerTag LTag = PointerTag.Link, RTag = PointerTag.Link;

	public ThreadTreeNode() {
	}

	public ThreadTreeNode(E data) {
		this.data = data;
	}
}
