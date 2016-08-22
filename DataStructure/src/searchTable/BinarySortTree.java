package searchTable;

import java.util.Random;

/**
 * ����������
 * 
 * @author New Song
 *
 */
public class BinarySortTree<E extends Comparable<E>> {
	private BiSortTreeNode<E> root;
	
	public BinarySortTree() {
	}

	public BinarySortTree(BiSortTreeNode<E> root) {
		this.root = root;
	}

	public BiSortTreeNode<E> search(E data) {
		return search(root, data);
	}

	// ����һ����㣬ʹ�õݹ�
	// ���data��Ϊ��ǰ����ֵ���򷵻ص�ǰ���
	// ���dataС�ڵ�ǰ��㣬��ô�ݹ���ҵ�ǰ����������
	// ���򣬵ݹ���ҵ�ǰ����������
	private BiSortTreeNode<E> search(BiSortTreeNode<E> node, E data) {
		if (node == null) {
			return null;
		}
		int res = data.compareTo(node.data);
		if (res > 0) {
			return search(node.Rchild, data);
		} else if (res < 0) {
			return search(node.Lchild, data);
		} else {
			return node;
		}
	}

	// ����һ�����
	public void insert(E data) {
		root = insert(root, data);
		// ע����Ҫ��ֵ�������ڵ���ڶ���insert������node�ͻ����ˣ�û�аѽ������root
		// ���õ�ʱ��һ��null������node����ʹnode������Ϊnull��Ҳû�����������¸���root
	}

	// Ҳ����Ҫ�ݹ���в��ҵģ��ݹ���ҵ�����λ�ú���룬���������ظ�Ԫ��
	private BiSortTreeNode<E> insert(BiSortTreeNode<E> node, E data) {
		if (node == null) {
			node = new BiSortTreeNode<E>(data);
		} else {
			if (data.compareTo(node.data) < 0) {
				node.Lchild = insert(node.Lchild, data);
			} else if(data.compareTo(node.data) > 0){
				node.Rchild = insert(node.Rchild, data);
			}
		}
		return node;
	}

	// ���ص�ǰ�����е�ֵ��С���Ǹ����
	private BiSortTreeNode<E> findMin(BiSortTreeNode<E> node) {
		if (node == null) {
			return null;
		}
		if (node.Lchild != null) {
			return findMin(node.Lchild);
		} else {
			return node;
		}
	}

	// ɾ��һ�����
	public void delete(E data) {
		root = delete(root, data);
	}

	private BiSortTreeNode<E> delete(BiSortTreeNode<E> node, E data) {
		if (node == null) {
			return null;
		}
		int res = data.compareTo(node.data);
		if (res > 0) {
			node.Rchild = delete(node.Rchild, data);
		} else if (res < 0) {
			node.Lchild = delete(node.Lchild, data);
			// ɾ��һ����㲻���������Լ���Ϊnull�������丸�ڵ�ҲҪ����������/��������Ϊnull
			// ����Ϊ�ҵ������
			// ����ҵ��Ľ���û����������û������������ôֱ����Ϊnull
			// ���ֻ����������ֻ������������ô����ǰ��㸳ֵΪ��Ϊ�յ���һ������
			// ������У���ô�ҵ���ǰ������������ֵ��С���Ǹ���㣬ɾ��������Ϊ��һ��û�������������Բ��õڶ���ɾ�����Լ��ɣ�
			// Ȼ�󽫵�ǰ��㸳ֵΪ��Сֵ
		} else if (node.Lchild != null && node.Rchild != null) {
			node.data = findMin(node.Rchild).data;
			node.Rchild = delete(node.Rchild, node.data);
			// ����һ��ɾ����ɾ��������node��������Ϊ���ڵ㣬ֵΪmin��������
			// �����˽��ֻ������������������û�е����
		} else {
			node = node.Lchild == null ? node.Rchild : node.Lchild;
		}
		return node;
	}

	public BiSortTreeNode<E> root() {
		return this.root;
	}

	public void preOrder(BiSortTreeNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preOrder(subTree.Lchild);
			preOrder(subTree.Rchild);
		}
	}

	public void inOrder(BiSortTreeNode<E> subTree) {
		if (subTree != null) {
			inOrder(subTree.Lchild);
			System.out.print(subTree.data + " ");
			inOrder(subTree.Rchild);
		}
	}

	public void postOrder(BiSortTreeNode<E> subTree) {
		if (subTree != null) {
			postOrder(subTree.Lchild);
			postOrder(subTree.Rchild);
			System.out.print(subTree.data + " ");
		}
	}
	
	public static void main(String[] args) {
		BinarySortTree<Integer> tree = new BinarySortTree<>();
		Random r = new Random();
		int integer = 0;
		for (int i = 0; i < 20; i++) {
			integer = r.nextInt(20);
			System.out.print(integer+" ");
			tree.insert(integer);
		}
		System.out.println();
		tree.inOrder(tree.root());
	}
}
class BiSortTreeNode<E extends Comparable<E>> {
	E data;
	BiSortTreeNode<E> Lchild;
	BiSortTreeNode<E> Rchild;

	public BiSortTreeNode(E data) {
		this.data = data;
	}

	public BiSortTreeNode(E data, BiSortTreeNode<E> Lchild, BiSortTreeNode<E> Rchild) {
		this.data = data;
		this.Lchild = Lchild;
		this.Rchild = Rchild;
	}
}