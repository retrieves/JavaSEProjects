package searchTable;

import java.util.Random;

/**
 * 二叉排序树
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

	// 查找一个结点，使用递归
	// 如果data即为当前结点的值，则返回当前结点
	// 如果data小于当前结点，那么递归查找当前结点的左子树
	// 否则，递归查找当前结点的右子树
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

	// 插入一个结点
	public void insert(E data) {
		root = insert(root, data);
		// 注意结果要赋值，否则在调完第二个insert方法后node就回收了，没有把结果赋给root
		// 调用的时候将一个null赋给了node，即使node后来不为null了也没法把引用重新赋给root
	}

	// 也是需要递归进行查找的，递归地找到合适位置后插入，不允许有重复元素
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

	// 返回当前子树中的值最小的那个结点
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

	// 删除一个结点
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