package searchTable;
/**
 * B-��
 * @author New Song
 *
 */
public class BSubTree {
	public static final int order = 3;//����
}
class BSubTreeNode<E extends Comparable<E>>{
	int keyNum;//�ؼ��ָ���
	BSubTreeNode<E> parent;//ָ��˫�׵�ָ��
	E []keys;//�ؼ�������
	BSubTreeNode<E> [] ptrs;//ָ����
	Record[] recordPtrs;//ָ���¼��ָ����
}
//��¼��
class Record {
	
}