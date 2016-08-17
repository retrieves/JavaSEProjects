package tree;


public class Test {
	public static void main(String[] args) {
		/*int [] datas = {8,3,1,6,4,7,10,14,13};
		BiTree<Integer> tree=  new BiTree<>();
		for (int i = 0; i < datas.length; i++) {
//			tree.insert(datas[i]);
			tree.createBiTree(tree.root(), datas[i]);
		}*/
/*		System.out.println("µÝ¹éÏÈÐò±éÀú£º");
		tree.preOrder(tree.root());
		System.out.println("\r\nµÝ¹éÖÐÐò±éÀú£º");
		tree.inOrder(tree.root());
		System.out.println("\r\nµÝ¹éºóÐò±éÀú£º");
		tree.postOrder(tree.root());
		System.out.println("\r\n--------------------");
		
		System.out.println("·ÇµÝ¹éÏÈÐò±éÀú£º");
		tree.noRecPreOrder(tree.root());
		System.out.println("\r\n·ÇµÝ¹éÖÐÐò±éÀú£º");
		tree.noRecInOrder(tree.root());
		System.out.println("\r\n·ÇµÝ¹éºóÐò±éÀú£º");
		tree.noRecPostOrder(tree.root());
		System.out.println("\r\n--------------------");
		
		System.out.println(tree.find(20));
		System.out.println(tree.find(3).data);
		tree.delete(8);
		tree.preOrder(tree.root());
		
		System.out.println(tree.depth());
		System.out.println(tree.size());
		
		System.out.println(tree.parent(tree.find(13)).data);
		System.out.println(tree.leftChild(tree.find(6)).data);
		tree.destroy();
		System.out.println(tree.root());
		tree.preOrder(tree.root());
		System.out.println();
		tree.clear(tree.find(6));
		tree.preOrder(tree.root());
		System.out.println(tree.leafSize());
		BiTree<Integer> tree2 = tree.clone();
		tree2.preOrder(tree2.root());
		System.out.println();
		tree2.insert(32);
		tree2.preOrder(tree2.root());
		System.out.println();
		tree.preOrder(tree.root());
		
		String pre = "ABCDEFGH";
		String in = "BDCEAFHG";
		BiTree<Character> tree3 = BiTree.preInCreateBiTree(pre, in);
		tree3.preOrder(tree3.root());
		*/
		BiTree<Character> tree = BiTree.createBiTree("ABE##CFG#H###DI#JK#####");
		tree.preOrder(tree.root());
		System.out.println();
		tree.printAllBiTreePaths();
	}
}
