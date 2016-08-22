package searchTable;

import java.util.Random;

public class AVLTree<E extends Comparable<E>> {
	private AVLTreeNode<E> root;

	public AVLTreeNode<E> search(E data) {
		return search(root, data);
	}

	// ����һ����㣬ʹ�õݹ�
	// ���data��Ϊ��ǰ����ֵ���򷵻ص�ǰ���
	// ���dataС�ڵ�ǰ��㣬��ô�ݹ���ҵ�ǰ����������
	// ���򣬵ݹ���ҵ�ǰ����������
	private AVLTreeNode<E> search(AVLTreeNode<E> node, E data) {
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

	public int height(AVLTreeNode<E> node) {
		return node == null ? 0 : node.height;
	}

	// LL�ͣ����Ե�ǰ�������ӵ����������в���ʱ����Ҫ���е�������
	// �÷��ص�Lchild���µĸ��ڵ㣩����ԭ���ĸ����
	private AVLTreeNode<E> rotateWithLeftChild(AVLTreeNode<E> parent) {
		AVLTreeNode<E> Lchild = parent.Lchild;
		parent.Lchild = Lchild.Rchild;
		Lchild.Rchild = parent;
		// �������������ǰ�������ӵĸ߶�
		parent.height = Math.max(height(parent.Lchild), height(parent.Rchild)) + 1;
		Lchild.height = Math.max(height(Lchild.Lchild), height(Lchild.Rchild)) + 1;
		return Lchild;
	}

	// RR�ͣ����Ե�ǰ�����Һ��ӵ����������в���ʱ����Ҫ���е�������
	// �÷��ص�Rchild���µĸ��ڵ㣩����ԭ���ĸ����
	private AVLTreeNode<E> rotateWithRightChild(AVLTreeNode<E> parent) {
		AVLTreeNode<E> Rchild = parent.Rchild;
		parent.Rchild = Rchild.Lchild;
		Rchild.Lchild = parent;
		// �������������ǰ�������ӵĸ߶�
		parent.height = Math.max(height(parent.Lchild), height(parent.Rchild)) + 1;
		Rchild.height = Math.max(height(Rchild.Lchild), height(Rchild.Rchild)) + 1;
		return Rchild;
	}

	// LR�ͣ����Ե�ǰ�������ӵ����������в���ʱ����Ҫ����������������
	// �÷��ص�Rchild���µĸ��ڵ㣩����ԭ���ĸ����
	private AVLTreeNode<E> doubleRotateWithLeftChild(AVLTreeNode<E> parent) {
		parent.Lchild = rotateWithRightChild(parent.Lchild);
		return rotateWithLeftChild(parent);
	}

	// RL�ͣ����Ե�ǰ�����Һ��ӵ����������в���ʱ����Ҫ����������������
	// �÷��ص�Rchild���µĸ��ڵ㣩����ԭ���ĸ����
	private AVLTreeNode<E> doubleRotateWithRightChild(AVLTreeNode<E> parent) {
		parent.Rchild = rotateWithLeftChild(parent.Rchild);
		return rotateWithRightChild(parent);
	}

	// �����ϸ������ͳһ����
	private AVLTreeNode<E> rotate(AVLTreeNode<E> node) {
		if(node == null){
			return null;
		}
		if (height(node.Lchild) - height(node.Rchild) == 2) {
			// �����ƽ�⣬�ټ�������������ϲ�����������or��������
			if (height(node.Lchild.Lchild) >= height(node.Lchild.Rchild)) {
				// ����������������ϣ���ô����LL��
				node = rotateWithLeftChild(node);
			} else {
				// ����������������ϣ���ô����LR��
				node = doubleRotateWithLeftChild(node);
			}
		} else if (height(node.Rchild) - height(node.Lchild) == 2) {
			// �����ƽ�⣬�ټ�������������ϲ�����������or��������
			if (height(node.Rchild.Lchild) >= height(node.Rchild.Rchild)) {
				// ����������������ϣ���ô����RL��
				node = doubleRotateWithLeftChild(node);
			} else {
				// ����������������ϣ���ô����RR��
				node = rotateWithLeftChild(node);
			}
		}
		return node;
	}
	// ����һ�����

	public void insert(E data) {
		root = insert(root, data);
		// ע����Ҫ��ֵ�������ڵ���ڶ���insert������node�ͻ����ˣ�û�аѽ������root
		// ���õ�ʱ��һ��null������node����ʹnode������Ϊnull��Ҳû�����������¸���root
	}

	// Ҳ����Ҫ�ݹ���в��ҵģ��ݹ���ҵ�����λ�ú���룬���������ظ�Ԫ��
	private AVLTreeNode<E> insert(AVLTreeNode<E> node, E data) {
		if (node == null) {
			node = new AVLTreeNode<E>(data);
		} else {
			if (data.compareTo(node.data) < 0) {
				node.Lchild = insert(node.Lchild, data);
				if (height(node.Lchild) - height(node.Rchild) == 2) {
					if (data.compareTo(node.Lchild.data) < 0 ) {
						// ����������������ϣ���ô����LL��
						node = rotateWithLeftChild(node);
					} else {
						// ����������������ϣ���ô����LR��
						node = doubleRotateWithLeftChild(node);
					}
				}
			} else if (data.compareTo(node.data) > 0) {
				node.Rchild = insert(node.Rchild, data);
				if (height(node.Rchild) - height(node.Lchild) == 2) {
					if (data.compareTo(node.Rchild.data) < 0 ) {
						// ����������������ϣ���ô����RL��
						node = doubleRotateWithRightChild(node);
					} else {
						// ����������������ϣ���ô����RR��
						node = rotateWithRightChild(node);
					}
				}
			}
		}
		node.height = Math.max(height(node.Lchild), height(node.Rchild))+1;
		return node;
	}

	// ���ص�ǰ�����е�ֵ��С���Ǹ����
	private AVLTreeNode<E> findMin(AVLTreeNode<E> node) {
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
	//���۲��뻹��ɾ����������������Ҫ����heightֵ
	//ɾ����Ϊ�˱���ABL�������ԣ������ƽ��Ļ�����Ҫ�Ե�ǰ���������������������Լ�������ת
	private AVLTreeNode<E> delete(AVLTreeNode<E> node, E data) {
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
		if(node != null){
			node.height = Math.max(height(node.Lchild), height(node.Rchild)) + 1;
			if(node.Lchild != null){
				node.Lchild = rotate(node.Lchild);
			}
			if(node.Rchild != null){
				node.Rchild = rotate(node.Rchild);
			}
			node = rotate(node);
		}
		return node;
	}

	public AVLTreeNode<E> root() {
		return root;
	}

	public void preOrder(AVLTreeNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preOrder(subTree.Lchild);
			preOrder(subTree.Rchild);
		}
	}

	public void inOrder(AVLTreeNode<E> subTree) {
		if (subTree != null) {
			inOrder(subTree.Lchild);
			System.out.print(subTree.data + " ");
			inOrder(subTree.Rchild);
		}
	}

	public void postOrder(AVLTreeNode<E> subTree) {
		if (subTree != null) {
			postOrder(subTree.Lchild);
			postOrder(subTree.Rchild);
			System.out.print(subTree.data + " ");
		}
	}
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>();
		Random r = new Random();
		int integer = 0;
		for (int i = 0; i < 20; i++) {
			integer = r.nextInt(20);
			System.out.print(integer+" ");
			tree.insert(integer);
		}
		System.out.println();
		tree.preOrder(tree.root());
		System.out.println();
		tree.inOrder(tree.root());
		
		tree.delete(9);

		System.out.println();
		tree.preOrder(tree.root());
		System.out.println();
		tree.inOrder(tree.root());
		System.out.println();
		System.out.println(tree.search(2));
	}
}

class AVLTreeNode<E extends Comparable<E>> {
	E data;
	AVLTreeNode<E> Lchild;
	AVLTreeNode<E> Rchild;
	int height;

	public AVLTreeNode(E data) {
		this.data = data;
	}

	public AVLTreeNode(E data, AVLTreeNode<E> Lchild, AVLTreeNode<E> Rchild) {
		this.data = data;
		this.Lchild = Lchild;
		this.Rchild = Rchild;
	}
}
