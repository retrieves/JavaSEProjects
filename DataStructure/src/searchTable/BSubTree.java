package searchTable;
/**
 * B-树
 * @author New Song
 *
 */
public class BSubTree {
	public static final int order = 3;//阶数
}
class BSubTreeNode<E extends Comparable<E>>{
	int keyNum;//关键字个数
	BSubTreeNode<E> parent;//指向双亲的指针
	E []keys;//关键字数组
	BSubTreeNode<E> [] ptrs;//指针域
	Record[] recordPtrs;//指向记录的指针域
}
//记录类
class Record {
	
}