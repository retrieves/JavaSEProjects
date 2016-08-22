package searchTable;

import java.util.Random;

public class AVLTree<E extends Comparable<E>> {
	private AVLTreeNode<E> root;

	public AVLTreeNode<E> search(E data) {
		return search(root, data);
	}

	// 查找一个结点，使用递归
	// 如果data即为当前结点的值，则返回当前结点
	// 如果data小于当前结点，那么递归查找当前结点的左子树
	// 否则，递归查找当前结点的右子树
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

	// LL型：当对当前结点的左孩子的左子树进行插入时，需要进行单向右旋
	// 用返回的Lchild（新的根节点）代替原来的根结点
	private AVLTreeNode<E> rotateWithLeftChild(AVLTreeNode<E> parent) {
		AVLTreeNode<E> Lchild = parent.Lchild;
		parent.Lchild = Lchild.Rchild;
		Lchild.Rchild = parent;
		// 调整完后重新求当前结点和左孩子的高度
		parent.height = Math.max(height(parent.Lchild), height(parent.Rchild)) + 1;
		Lchild.height = Math.max(height(Lchild.Lchild), height(Lchild.Rchild)) + 1;
		return Lchild;
	}

	// RR型：当对当前结点的右孩子的右子树进行插入时，需要进行单向左旋
	// 用返回的Rchild（新的根节点）代替原来的根结点
	private AVLTreeNode<E> rotateWithRightChild(AVLTreeNode<E> parent) {
		AVLTreeNode<E> Rchild = parent.Rchild;
		parent.Rchild = Rchild.Lchild;
		Rchild.Lchild = parent;
		// 调整完后重新求当前结点和左孩子的高度
		parent.height = Math.max(height(parent.Lchild), height(parent.Rchild)) + 1;
		Rchild.height = Math.max(height(Rchild.Lchild), height(Rchild.Rchild)) + 1;
		return Rchild;
	}

	// LR型：当对当前结点的左孩子的右子树进行插入时，需要进行先左旋后右旋
	// 用返回的Rchild（新的根节点）代替原来的根结点
	private AVLTreeNode<E> doubleRotateWithLeftChild(AVLTreeNode<E> parent) {
		parent.Lchild = rotateWithRightChild(parent.Lchild);
		return rotateWithLeftChild(parent);
	}

	// RL型：当对当前结点的右孩子的左子树进行插入时，需要进行先右旋后左旋
	// 用返回的Rchild（新的根节点）代替原来的根结点
	private AVLTreeNode<E> doubleRotateWithRightChild(AVLTreeNode<E> parent) {
		parent.Rchild = rotateWithLeftChild(parent.Rchild);
		return rotateWithRightChild(parent);
	}

	// 将以上各种情况统一起来
	private AVLTreeNode<E> rotate(AVLTreeNode<E> node) {
		if(node == null){
			return null;
		}
		if (height(node.Lchild) - height(node.Rchild) == 2) {
			// 如果不平衡，再检测属于在左孩子上插在了左子树or右子树上
			if (height(node.Lchild.Lchild) >= height(node.Lchild.Rchild)) {
				// 如果插在了左子树上，那么属于LL型
				node = rotateWithLeftChild(node);
			} else {
				// 如果插在了右子树上，那么属于LR型
				node = doubleRotateWithLeftChild(node);
			}
		} else if (height(node.Rchild) - height(node.Lchild) == 2) {
			// 如果不平衡，再检测属于在左孩子上插在了左子树or右子树上
			if (height(node.Rchild.Lchild) >= height(node.Rchild.Rchild)) {
				// 如果插在了左子树上，那么属于RL型
				node = doubleRotateWithLeftChild(node);
			} else {
				// 如果插在了右子树上，那么属于RR型
				node = rotateWithLeftChild(node);
			}
		}
		return node;
	}
	// 插入一个结点

	public void insert(E data) {
		root = insert(root, data);
		// 注意结果要赋值，否则在调完第二个insert方法后node就回收了，没有把结果赋给root
		// 调用的时候将一个null赋给了node，即使node后来不为null了也没法把引用重新赋给root
	}

	// 也是需要递归进行查找的，递归地找到合适位置后插入，不允许有重复元素
	private AVLTreeNode<E> insert(AVLTreeNode<E> node, E data) {
		if (node == null) {
			node = new AVLTreeNode<E>(data);
		} else {
			if (data.compareTo(node.data) < 0) {
				node.Lchild = insert(node.Lchild, data);
				if (height(node.Lchild) - height(node.Rchild) == 2) {
					if (data.compareTo(node.Lchild.data) < 0 ) {
						// 如果插在了左子树上，那么属于LL型
						node = rotateWithLeftChild(node);
					} else {
						// 如果插在了右子树上，那么属于LR型
						node = doubleRotateWithLeftChild(node);
					}
				}
			} else if (data.compareTo(node.data) > 0) {
				node.Rchild = insert(node.Rchild, data);
				if (height(node.Rchild) - height(node.Lchild) == 2) {
					if (data.compareTo(node.Rchild.data) < 0 ) {
						// 如果插在了左子树上，那么属于RL型
						node = doubleRotateWithRightChild(node);
					} else {
						// 如果插在了右子树上，那么属于RR型
						node = rotateWithRightChild(node);
					}
				}
			}
		}
		node.height = Math.max(height(node.Lchild), height(node.Rchild))+1;
		return node;
	}

	// 返回当前子树中的值最小的那个结点
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

	// 删除一个结点
	public void delete(E data) {
		root = delete(root, data);
	}
	//无论插入还是删除，操作结束后都需要更新height值
	//删除后为了保持ABL树的特性，如果不平衡的话，需要对当前结点的左子树、右子树和自己进行旋转
	private AVLTreeNode<E> delete(AVLTreeNode<E> node, E data) {
		if (node == null) {
			return null;
		}
		int res = data.compareTo(node.data);
		if (res > 0) {
			node.Rchild = delete(node.Rchild, data);
		} else if (res < 0) {
			node.Lchild = delete(node.Lchild, data);
			// 删除一个结点不仅包括将自己赋为null，而且其父节点也要将其左子树/右子树赋为null
			// 以下为找到的情况
			// 如果找到的结点既没有左子树又没有右子树，那么直接设为null
			// 如果只有左子树或只有右子树，那么将当前结点赋值为不为空的那一个子树
			// 如果都有，那么找到当前结点的右子树中值最小的那个结点，删除它（因为它一定没有左子树，所以采用第二种删除策略即可）
			// 然后将当前结点赋值为最小值
		} else if (node.Lchild != null && node.Rchild != null) {
			node.data = findMin(node.Rchild).data;
			node.Rchild = delete(node.Rchild, node.data);
			// 进行一次删除，删除的是以node的右子树为根节点，值为min的这个结点
			// 包含了结点只有左子树或右子树或都没有的情况
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
