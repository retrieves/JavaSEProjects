package tree;

public class TreeExpress {

}
@SuppressWarnings("all")
/**
 * ˫�ױ�ʾ��
 * @author New Song
 *
 * @param <E>
 */
class ParentTree<E extends Comparable<E>> {
	private ParentNode<E> [] nodes ;
	private int root;//���ڵ��������е�����
	private int size;//������
	private static int maxTreeSize = 100;
}

class ParentNode<E extends Comparable<E>> {
	E data;
	int parent;// ˫���������е�����
}
/**
 * ���������ʾ��
 * ������������
 * ÿ�����ĺ��ӷ���������
 * @author New Song
 *
 * @param <E>
 */
@SuppressWarnings("all")
class ChildTree<E extends Comparable<E>>{
	private ChildBox<E> [] nodes ;
	private int root;//���ڵ��������е�����
	private int size;//������
}

class ChildBox<E extends Comparable<E>>{
	E data;
	ChildNode firstChild;//��һ������ָ��
}

class ChildNode{ //���ӽ��
	int childIndex;//���ӽ���������е�λ��
	ChildNode next; //��һ�����ӽ���ָ��
}
/**
 * �����ֵܱ�ʾ��
 * @author New Song
 *
 * @param <E>
 *//*
class ChildSiblingTree<E extends Comparable<E>>{
	private ChildSiblingNode<E> root;
}
class ChildSiblingNode<E extends Comparable<E>>{
	E data;
	ChildSiblingNode<E> firstChild;//��һ������
	ChildSiblingNode<E> nextSibling;//�Ҳ���ֵ�
}
*/
