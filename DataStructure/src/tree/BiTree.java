package tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Javaʵ�ֶ�������� ʹ�õݹ�Ĳ���һ����������������һ��������public�ģ����ڵ��õģ���һ��������private��ʵ�ʵݹ�ģ�
 * 
 * @author New Song
 *
 */

public class BiTree<E extends Comparable<E>> implements Cloneable {
	private TreeNode<E> root;
	private static int index = -1;
	//����ʹ����������ַ�������
	private  LinkedList<TreeNode<E>> pathList;
	//��������Ӹ��ڵ㵽����Ҷ�ӽ�������·��
	
	public BiTree() {
	}

	public BiTree(TreeNode<E> root) {
		this.root = root;
	}

	// ��node����´���������node��ʼֵΪroot���ڲ��ҹ����б�Ϊroot���ӽڵ�
	// ���rootΪ�գ���ôroot��ֵΪdata
	// ��������²��ң�ֱ���ҵ����ʵ�λ�ã������½�㣬��ֵΪdata
	// ������node��ֵ����data������������Ⱦ�û�в���ı�Ҫ��
	public void createBiTree(TreeNode<E> node, E data) {
		if (root == null) {
			root = new TreeNode<E>(data);
		} else {
			if (data.compareTo(node.data) < 0) {
				if (node.Lchild == null) {
					node.Lchild = new TreeNode<E>(data);
				} else {
					createBiTree(node.Lchild, data);
				}
			} else {
				if (node.Rchild == null) {
					node.Rchild = new TreeNode<E>(data);
				} else {
					createBiTree(node.Rchild, data);
				}
			}
		}
	}

	// ����һ�����
	public void insert(E data) {
		root = insert(root, data);
		// ע����Ҫ��ֵ�������ڵ���ڶ���insert������node�ͻ����ˣ�û�аѽ������root
		// ���õ�ʱ��һ��null������node����ʹnode������Ϊnull��Ҳû�����������¸���root
	}

	// Ҳ����Ҫ�ݹ���в��ҵģ��ݹ���ҵ�����λ�ú���룬���������ظ�Ԫ��
	private TreeNode<E> insert(TreeNode<E> node, E data) {
		if (node == null) {
			node = new TreeNode<E>(data);
		} else {
			if (data.compareTo(node.data) < 0) {
				node.Lchild = insert(node.Lchild, data);
			} else if(data.compareTo(node.data) > 0){
				node.Rchild = insert(node.Rchild, data);
			}
		}
		return node;
	}

	public TreeNode<E> search(E data) {
		return search(root, data);
	}

	// ����һ����㣬ʹ�õݹ�
	// ���data��Ϊ��ǰ����ֵ���򷵻ص�ǰ���
	// ���dataС�ڵ�ǰ��㣬��ô�ݹ���ҵ�ǰ����������
	// ���򣬵ݹ���ҵ�ǰ����������
	private TreeNode<E> search(TreeNode<E> node, E data) {
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

	// ɾ��һ�����
	public void delete(E data) {
		root = delete(root, data);
	}

	private TreeNode<E> delete(TreeNode<E> node, E data) {
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

	// ���ص�ǰ�����е�ֵ��С���Ǹ����
	private TreeNode<E> findMin(TreeNode<E> node) {
		if (node == null) {
			return null;
		}
		if (node.Lchild != null) {
			return findMin(node.Lchild);
		} else {
			return node;
		}
	}

	public boolean isEmpty() {
		return root == null;
	}

	// ������������������
	public int depth() {
		return depth(root);
	}

	private int depth(TreeNode<E> subTree) {
		if (subTree == null) {
			return 0;
			// �������Ϊ0���ݹ��������ʼ����
		} else {
			int Ldepth = depth(subTree.Lchild);
			int Rdepth = depth(subTree.Rchild);
			return Ldepth > Rdepth ? (Ldepth + 1) : (Rdepth + 1);
			// �������ʵ���ϰ��������������һ����Ҷ�ӽ�㣬��ʱ���ص���1����һ���Ƿ�Ҷ�ӽ�㣬���ص��ǽ������������+1
			// �ۺ���������ÿ����Ҷ�ӽ�㣬��Ⱦͼ�1���õ���������ȣ������ϸ��ڵ�����1
			/*
			 * }else if(subTree.Lchild == null || subTree.Rchild == null){
			 * return 1; }
			 */
		}

	}

	// �������Ľ�����
	public int size() {
		return size(root);
	}

	private int size(TreeNode<E> subTree) {
		if (subTree == null) {
			return 0;
		} else {
			int Lsize = size(subTree.Lchild);
			int Rsize = size(subTree.Rchild);
			return Lsize + Rsize + 1;
		}
	}

	public TreeNode<E> parent(TreeNode<E> element) {
		if (element == null) {
			return null;
		}
		return parent(root, element);
	}

	// ��һ������subTree��ָ�ý��֮�½����������ڶ�������element��Ҫ�Ҹ��ڵ���ӽڵ�
	private TreeNode<E> parent(TreeNode<E> subTree, TreeNode<E> element) {
		TreeNode<E> parent;
		if (subTree == null) {
			return null;
		} else {
			if (subTree.Lchild == element || subTree.Rchild == element) {
				return subTree;
			} else {
				parent = parent(subTree.Lchild, element);
				// ���������еݹ�����element�ĸ��ڵ�
				return parent != null ? parent : parent(subTree.Rchild, element);
				// ��������������ҵ��ˣ��ͷ��أ�û���ҵ�������������������
				// �����û���ҵ����ͷ���null
			}
		}
	}

	public TreeNode<E> leftChild(TreeNode<E> node) {
		if (node == null) {
			return null;
		}
		return node.Lchild;
	}

	public TreeNode<E> rightChild(TreeNode<E> node) {
		if (node == null) {
			return null;
		}
		return node.Rchild;
	}

	public TreeNode<E> root() {
		return this.root;
	}

	public void destroy() {
		destory(root);
		root = null;
	}

	// ���ٲ��ú������
	// ����ʱҪ�������������������������������Ϊnull���������ֿ�ָ���쳣
	private void destory(TreeNode<E> subTree) {
		if (subTree != null) {
			destory(subTree.Lchild);
			destory(subTree.Rchild);
			subTree = null;
		}
	}

	// ���Ӧ��ÿ�ֱ�����ʽ�����У�����������������
	public void clear(TreeNode<E> subTree) {
		if (subTree != null) {
			subTree.data = null;
			clear(subTree.Lchild);
			clear(subTree.Rchild);
		}
	}

	public void preOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			System.out.print(subTree.data + " ");
			preOrder(subTree.Lchild);
			preOrder(subTree.Rchild);
		}
	}

	public void inOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			inOrder(subTree.Lchild);
			System.out.print(subTree.data + " ");
			inOrder(subTree.Rchild);
		}
	}

	public void postOrder(TreeNode<E> subTree) {
		if (subTree != null) {
			postOrder(subTree.Lchild);
			postOrder(subTree.Rchild);
			System.out.print(subTree.data + " ");
		}
	}

	// �ǵݹ� �������
	public void noRecPreOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		while (subTree != null || !stack.isEmpty()) {
			while (subTree != null) {
				System.out.print(subTree.data + " ");
				stack.push(subTree);
				subTree = subTree.Lchild;// ����������
			}
			if (!stack.isEmpty()) {
				subTree = stack.pop();
				subTree = subTree.Rchild;// ����������
			}
		}
	}

	// �ǵݹ� �������
	public void noRecInOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		// subTree��Ϊ�գ����ʾ���ڣ���Ҫ���з��ʣ�ջ��Ϊ�ձ�ʾû��ȫ�������ꡣֻҪ��ǰû�б����������û�б����꣬������ѭ����
		// ���subTree��Ϊ�գ����ȷ��������������ڴ�֮ǰ��Ҫ�������ջ��ÿ�η���������֮ǰ������ǰ�����ջ��
		// ���Ϊ�գ���ʾ�����������ڣ���ô��ջ�����������������½���ѭ����
		while (subTree != null || !stack.isEmpty()) {
			while (subTree != null) {
				// while��ʾ���ϵط�������������ֱ���ﵽ����������׶�
				stack.push(subTree);
				subTree = subTree.Lchild;// ����������
			}
			if (!stack.isEmpty()) {
				subTree = stack.pop();
				System.out.print(subTree.data + " ");// ����������
				subTree = subTree.Rchild;// ����������
			}
		}
	}

	// ʹ��һ�������һ�����ʽ���������ʵ�ַǵݹ�������
	public void noRecPostOrder(TreeNode<E> subTree) {
		Stack<TreeNode<E>> stack = new Stack<>();
		TreeNode<E> lastVisited = null;
		// ���ﲻ��Ҫ����whileѭ������Ϊ��ջ�յ�ʱ�򣬾Ϳ�֪�����Ѿ�������subTree�ֻص��˸��ڵ�
		while (subTree != null) {
			stack.push(subTree);
			subTree = subTree.Lchild;// ����������
		}
		while (!stack.isEmpty()) {
			// ��������Ա�֤�Ѿ��������������ĵ׶�
			subTree = stack.pop();
			// �ص���ǰ���
			if (subTree.Rchild == null || subTree.Rchild == lastVisited) {
				// һ�����ڵ㱻���ʵ�ǰ���ǣ������������������ѱ����ʹ�
				System.out.print(subTree.data + " ");
				lastVisited = subTree;
			} else {
				// ��ʱ���������ĵ׶ˣ�������������������������δ�����ʣ���ô��Ҫ����������
				// ����������֮ǰҲҪ��ջ���Ա�֤�ڷ��������������ܻص���ǰ������������
				stack.push(subTree);
				subTree = subTree.Rchild;
				// ���������������ҿ��Ա�֤��������Ϊ�գ���ô���������м������к���������ȷ���������ֱ���������׶�
				// ֮��ͻص�ѭ����Ŀ�ʼ�����������
				while (subTree != null) {
					stack.push(subTree);
					subTree = subTree.Lchild;// ����������
				}
			}
		}
	}

	public int leafSize() {
		return leafSize(root);
	}

	private int leafSize(TreeNode<E> subTree) {
		if (subTree == null) {
			return 0;
		} else if (subTree.Lchild == null && subTree.Rchild == null) {
			return 1;
		} else {
			// �������Ҷ�ӽ�㣬��ô�ֱ�����������Ҷ�ӽ�����������Ҷ�ӽ�㣬��Ӻ󷵻�
			return leafSize(subTree.Lchild) + leafSize(subTree.Rchild);
		}
	}

	// �������ĸ��ƣ���������һģһ���Ķ�����
	// ������������ķ�ʽ
	public BiTree<E> clone() {
		BiTree<E> tree = new BiTree<>();
		tree.root = clone(root);
		return tree;
	}

	private TreeNode<E> clone(TreeNode<E> subTree) {
		if (subTree != null) {
			TreeNode<E> newNode = new TreeNode<>(subTree.data);
			newNode.Lchild = clone(subTree.Lchild);
			newNode.Rchild = clone(subTree.Rchild);
			return newNode;
		} else {
			return null;
		}
	}
	
	//ʹ������������ַ���������������ַ����������������ڿհ��ַ�/�����ַ�
	//ֻ�ܴ����洢�ַ��Ķ�����
	public static BiTree<Character> preInCreateBiTree(String preStr, String inStr) {
		char[] pre = preStr.toCharArray();
		char[] in = inStr.toCharArray();
		return new BiTree<>(createSubTree(pre, in, 0, 0, pre.length));
	}

	private static TreeNode<Character> createSubTree(char[] pre, char[] in, int preStart, int inStart, int size) {
		if (size == 0) {
			return null;
		}
		TreeNode<Character> subTree = new TreeNode<>(pre[preStart]);
		int mid = indexOf(in, pre[preStart]);
		if (mid != inStart) {
			subTree.Lchild = createSubTree(pre, in, preStart + 1, inStart, mid - inStart);
		}
		//mid-inStart Ϊ������������
		if (mid != inStart + size -1 ) {
		//����if��Ϊ������û����������û�������������
		//û������������ �������Ŀ�ʼ���ǽ��� �� ��ʼ��mid+1 ����mid = inStart+size-1���� ��ʼΪ inStart+size��������ҲΪ��������û�б�Ҫ����������
			subTree.Rchild = createSubTree(pre, in, preStart + 1 + (mid -inStart), mid + 1, size - (mid -inStart) - 1);
		}
		//size - (mid -inStart) - 1)���������������������н������size��ȥ���������������ټ�ȥ��ͷ�����ĸ��ڵ�1��
		return subTree;
	}

	
	private static int indexOf(char[] arr, char ch) {
		for (int i = 0; i < arr.length; i++) {
			if (ch == arr[i]) {
				return i;
			}
		}
		return -1;
	}
	
	//ʹ����������ַ���������ֻ�ܴ����洢�ַ��Ķ�����
	public static BiTree<Character> createBiTree(String preStr){
		char[] pre = preStr.toCharArray();
		BiTree<Character> tree = new BiTree<>(createSubTree(pre)) ;
		index = -1;
		return tree;
	}
	
	private static TreeNode<Character> createSubTree(char[] pre){
		index++;
		TreeNode<Character> subTree = null;
		if(pre[index] == '#'){
			subTree = null;
		}else{
			subTree = new TreeNode<>(pre[index]);
			subTree.Lchild = createSubTree(pre);
			subTree.Rchild = createSubTree(pre);
		}
		return subTree;
	}
	//����������Ӹ��ڵ㵽ÿ��Ҷ�ӽ���·��
	public void printAllBiTreePaths(){
		if(pathList == null){
			pathList = new LinkedList<>();
		}
		printBiTreePath(root);
		pathList.clear();;
	}
	
	private void printBiTreePath(TreeNode<E> node){
		if(node != null){
			pathList.addLast(node);
			if(node.Lchild == null && node.Rchild == null){
				for (TreeNode<E> treeNode : pathList) {
					System.out.print(treeNode.data+" ");
				}
				System.out.println();
				//�������Ӹ��ڵ㵽Ҷ�ӵ�������
			}else{
				printBiTreePath(node.Lchild);
				printBiTreePath(node.Rchild);
			}
			pathList.removeLast();
			//�����굱ǰ��㣬�˳�����
		}
	}
	
}

class TreeNode<E extends Comparable<E>> {
	E data;
	TreeNode<E> Lchild;
	TreeNode<E> Rchild;

	public TreeNode(E data) {
		this.data = data;
	}

	public TreeNode(E data, TreeNode<E> Lchild, TreeNode<E> Rchild) {
		this.data = data;
		this.Lchild = Lchild;
		this.Rchild = Rchild;
	}
}
