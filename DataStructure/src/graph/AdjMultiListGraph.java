/*package graph;
enum GraphKind {
	DG, DN, UDG, UDN
}
enum IfVisit{
	visited,unvisited
}
class InfoType {
	String info;
	// �û������Ϣ����
}
//�߽��
class EdgeNode{
	IfVisit mark;//��Ǹ������Ƿ񱻷��ʹ�
	int iVex,jVex;//�ֱ�Ϊ�����ߵ����������������е�����
	EdgeNode iLink,jLink;//�ֱ�ָ����һ�������ڽ��iVex��jVex�ı�
	InfoType info;
}
//������
class VexNode<E>{
	E data;
	EdgeNode firstEdge;//�ö���ĵ�һ����
}
*//**
 * �ڽӶ��ر��ʾ��ͼ
 * @author New Song
 *
 *//*
public class AdjMultiListGraph<E> {
	public static final int MAX_VERTEX_NUM = 20;
	VexNode<E> [] adjMulist;
	int vexNum,arcNum;
	GraphKind kind;
}
*/