package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//�ֱ��ʾ����ͼ��������������ͼ��������
enum GraphKind {
	DG, DN, UDG, UDN
}

// ��ʾ����� ����
class ArcCell {
	int adj;
	// ����ͼ��˵����1��ʾ�������ڣ���0��ʾ���㲻����
	// ��������˵����ʾȨֵ���ͣ���������ֵ��ʾ��Ȩֵ�������ʾû�����ӻ����Լ����Լ�
	InfoType infoType;

	public ArcCell() {
		// TODO Auto-generated constructor stub
	}

	public ArcCell(int adj) {
		this.adj = adj;
	}

	class InfoType {
		String info;

		// �û������Ϣ����
		public InfoType(String info) {
			this.info = info;
		}
	}

	@Override
	public String toString() {
		return adj + (infoType != null ? infoType.info : "");
	}
}

// ��ʾ��������
class Vertex<E extends Comparable<E>> {
	private E data;

	public Vertex(E data) {
		this.data = data;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data + "";

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else {
			if (obj instanceof Vertex) {
				Vertex<E> v = (Vertex<E>) obj;
				if (v.data.equals(this.data)) {
					return true;
				}
			}
		}
		return false;
	}
}

/**
 * �����ʾ��/�ڽӾ����ʾ��
 * 
 * @author New Song
 *
 */
public class MetrixGraph<E extends Comparable<E>> {
	public static final int MAX_VERTEX_NUM = 20;
	private static final int infinity = java.lang.Integer.MAX_VALUE;// ��ʾ���е�����
	private static final int hasJoined = 0;
	private static final int noVisited = -1;
	private List<Vertex<E>> vexs; // ����
	private ArcCell[][] arcs; // �ڽӾ���
	private int vexNum, arcNum; // ͼ�ĵ�ǰ�������ͻ���
	private GraphKind kind; // ͼ������
	private boolean[] visited;// ���ʱ�־���飬��¼�����ж����ڱ����������Ƿ񱻷���

	private int[] visitOrder;// ���ʱ�־���飬��¼�����ж����ڱ��������еķ��ʴ���������ؽڵ�
	private int[] low;// ��¼��ǰ������ܴﵽ����С���ֵ��������ؽڵ�
	private int rexCount;// ������������ʱ�����ڼ�¼�����Ķ�������������ؽڵ�
	private int mid;// mid��¼��ǰ�����ܹ��ﵽ����С���ֵ��������ؽڵ�

	private LinkedList<String> path;
	private List<Closedge> closedge;// ����������С�������ĸ�������

	public MetrixGraph(E[] data, GraphKind kind, int vexNum, int arcNum, boolean isNet) {
		this.vexs = new ArrayList<>(MAX_VERTEX_NUM);
		this.arcs = new ArcCell[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
		this.visited = new boolean[MAX_VERTEX_NUM];
		this.visitOrder = new int[MAX_VERTEX_NUM];
		this.low = new int[MAX_VERTEX_NUM];
		this.closedge = new ArrayList<>(MAX_VERTEX_NUM);
		this.kind = kind;
		this.vexNum = vexNum;
		this.arcNum = arcNum;

		for (int i = 0; i < data.length; i++) {
			vexs.add(new Vertex<>(data[i]));
		}
		for (int i = 0; i < arcs.length; i++) {
			for (int j = 0; j < arcs[i].length; j++) {
				arcs[i][j] = new ArcCell();
			}
		}
		if (isNet) {
			for (int i = 0; i < arcs.length; i++) {
				for (int j = 0; j < arcs[i].length; j++) {
					arcs[i][j].adj = infinity;
				}
			}
		}
	}

	// ������췽��ר������Kruskal�㷨����С������ʹ��
	private MetrixGraph(List<Vertex<E>> vexs, GraphKind kind) {
		this.vexs = vexs;
		this.kind = kind;
		this.arcNum = 0;
		this.vexNum = vexs.size();
		this.arcs = new ArcCell[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
		this.visited = new boolean[MAX_VERTEX_NUM];
		for (int i = 0; i < vexNum; i++) {
			for (int j = 0; j < vexNum; j++) {
				arcs[i][j] = new ArcCell();
				arcs[i][j].adj = infinity;
			}
		}
	}

	public boolean isNet() {
		return kind == GraphKind.DN || kind == GraphKind.UDN;
	}

	public boolean isDirected() {
		return kind == GraphKind.DG || kind == GraphKind.DN;
	}

	// ��ͼ�в���һ�������������е�λ��/����
	public int locateVertex(E data) {
		for (int i = 0; i < vexs.size(); i++) {
			if (vexs.get(i).getData().equals(data)) {
				return i;
			}
		}
		return -1;
	}

	public static <E extends Comparable<E>> MetrixGraph<E> createGraph(GraphKind kind, E[] data, E[] tail, E[] head,
			int[] weight, String[] info) {
		if (data == null || kind == null) {
			throw new NullPointerException();
		}
		MetrixGraph<E> graph = null;
		switch (kind) {
		case DG:
			graph = createDirectedGraph(data, tail, head, info);
			break;
		case DN:
			graph = createDirectedNet(data, tail, head, weight, info);
			break;
		case UDG:
			graph = createUnDirectedGraph(data, tail, head, info);
			break;
		case UDN:
			graph = createUnDirectedNet(data, tail, head, weight, info);
			break;
		default:
			break;
		}
		return graph;
	}

	// ��������ͼ
	private static <E extends Comparable<E>> MetrixGraph<E> createDirectedGraph(E[] data, E[] tail, E[] head,
			String[] info) {
		if (tail == null || head == null) {
			return new MetrixGraph<>(data, GraphKind.DG, data.length, 0, false);
		}
		MetrixGraph<E> graph = new MetrixGraph<>(data, GraphKind.DG, data.length, tail.length, false);
		int tailIndex, headIndex = 0;
		for (int i = 0; i < graph.arcNum; i++) {
			tailIndex = graph.locateVertex(tail[i]);
			headIndex = graph.locateVertex(head[i]);
			graph.arcs[tailIndex][headIndex].adj = 1;
			if (info != null) {
				graph.arcs[tailIndex][headIndex].infoType = graph.arcs[tailIndex][headIndex].new InfoType(info[i]);
			}
		}
		return graph;
	}

	// ����������������ͼ�������ڻ�/���ϵ�VRType��ͬ����ʼ����ʽҲ��ͬ
	// ��Ҫ����һ����������������Ȩֵ
	private static <E extends Comparable<E>> MetrixGraph<E> createDirectedNet(E[] data, E[] tail, E[] head,
			int[] weight, String[] info) {
		if (tail == null || head == null) {
			return new MetrixGraph<>(data, GraphKind.DN, data.length, 0, true);
		}
		MetrixGraph<E> graph = new MetrixGraph<>(data, GraphKind.DN, data.length, tail.length, true);
		int tailIndex, headIndex = 0;
		for (int i = 0; i < graph.arcNum; i++) {
			tailIndex = graph.locateVertex(tail[i]);
			headIndex = graph.locateVertex(head[i]);
			graph.arcs[tailIndex][headIndex].adj = weight[i];
			if (info != null) {
				graph.arcs[tailIndex][headIndex].infoType = graph.arcs[tailIndex][headIndex].new InfoType(info[i]);
			}
		}
		return graph;
	}

	// ��������ͼ��������ͼ�������������������i��j�Ļ���ͬʱҲҪ����j��i�Ļ�
	private static <E extends Comparable<E>> MetrixGraph<E> createUnDirectedGraph(E[] data, E[] tail, E[] head,
			String[] info) {
		if (tail == null || head == null) {
			return new MetrixGraph<>(data, GraphKind.UDG, data.length, tail.length, false);
		}
		MetrixGraph<E> graph = new MetrixGraph<>(data, GraphKind.UDG, data.length, tail.length, false);
		int tailIndex, headIndex = 0;
		for (int i = 0; i < graph.arcNum; i++) {
			tailIndex = graph.locateVertex(tail[i]);
			headIndex = graph.locateVertex(head[i]);
			graph.arcs[tailIndex][headIndex].adj = graph.arcs[headIndex][tailIndex].adj = 1;
			if (info != null) {
				graph.arcs[tailIndex][headIndex].infoType = graph.arcs[tailIndex][headIndex].new InfoType(info[i]);
				graph.arcs[headIndex][tailIndex].infoType = graph.arcs[headIndex][tailIndex].new InfoType(info[i]);
			}
		}
		return graph;
	}

	// ����������
	private static <E extends Comparable<E>> MetrixGraph<E> createUnDirectedNet(E[] data, E[] tail, E[] head,
			int[] weight, String[] info) {
		if (head == null || tail == null) {
			return new MetrixGraph<>(data, GraphKind.UDN, data.length, 0, true);
		}
		MetrixGraph<E> graph = new MetrixGraph<>(data, GraphKind.UDN, data.length, tail.length, true);
		int tailIndex, headIndex = 0;
		for (int i = 0; i < graph.arcNum; i++) {
			tailIndex = graph.locateVertex(tail[i]);
			headIndex = graph.locateVertex(head[i]);
			graph.arcs[tailIndex][headIndex].adj = graph.arcs[headIndex][tailIndex].adj = weight[i];
			if (info != null) {
				graph.arcs[tailIndex][headIndex].infoType = graph.arcs[tailIndex][headIndex].new InfoType(info[i]);
				graph.arcs[headIndex][tailIndex].infoType = graph.arcs[headIndex][tailIndex].new InfoType(info[i]);
			}
		}
		return graph;
	}

	// ��ӡ����ͻ�/��
	public void display() {
		System.out.print(kind == GraphKind.DG ? "DirectedGraph:"
				: kind == GraphKind.DN ? "DirectedNet:"
						: kind == GraphKind.UDG ? "UnDirectedGraph:" : "UnDirectedNet:");
		System.out.println(vexNum + " vertexs," + arcNum + " arcs");
		System.out.println("Vertexs:");
		for (int i = 0; i < vexNum; i++) {
			System.out.print(i + ":" + vexs.get(i) + "  ");
		}
		System.out.println("\r\nArcs:");
		// ������з���ģ���ôȫ�����
		// ���������ģ���ô���һ�뼴��
		// ע���ӡʱ�����ݶ������������ӡ�����Ǹ��ݻ�����������ӡ
		for (int i = 0; i < vexNum; i++) {
			for (int j = isDirected() ? 0 : i + 1; j < vexNum; j++) {
				if (isNet() && arcs[i][j].adj == java.lang.Integer.MAX_VALUE) {
					System.out.print(i + "-" + j + ":NULL  ");
				} else {
					System.out.print(i + "-" + j + ":" + arcs[i][j] + "  ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void destroy() {
		vexs.clear();
		for (int i = 0; i < arcNum; i++) {
			for (int j = 0; j < arcNum; j++) {
				arcs[i][j] = null;
			}
		}
		arcNum = 0;
		vexNum = 0;
	}

	// ����һ�����㣬���ظö����ֵ
	public E getVertex(Vertex<E> vex) {
		Vertex<E> v = null;
		for (int i = 0; i < vexs.size(); i++) {
			v = vexs.get(i);
			if (v.equals(vex)) {
				return v.getData();
			}
		}
		return null;
	}

	// �ж����ͼ�еĶ����Ƿ����ֵΪdata�Ķ���
	public boolean contains(E data) {
		for (int i = 0; i < vexs.size(); i++) {
			if (vexs.get(i).getData().equals(data)) {
				return true;
			}
		}
		return false;
	}

	// ����һ������͸ö������ֵ�����ö����ֵ��Ϊ��ֵ
	public E putVertex(Vertex<E> vex, E newValue) {
		if (contains(newValue)) {
			return null;
		}
		Vertex<E> v = null;
		E oldValue = null;
		for (int i = 0; i < vexs.size(); i++) {
			v = vexs.get(i);
			if (v.equals(vex)) {
				oldValue = v.getData();
				v.setData(newValue);
			}
		}
		return oldValue;
	}

	// ����һ�����㣬�����������ĵ�һ���ڽӵ��������������ڣ�
	// ���������ģ������Ըö���Ϊ��β�ĵ�һ���ڽӵ�
	// ���������ģ����رߵĵ�һ���ڽӵ�
	public int firstAdjVex(Vertex<E> vex) {
		if (vex == null) {
			return -1;
		}
		int index = locateVertex(vex.getData());
		if (index == -1) {
			return -1;
		}
		int adj = 0;
		if (isNet()) {
			adj = infinity;
		}
		for (int i = 0; i < arcNum; i++) {
			if (arcs[index][i].adj != adj) {
				// �����/������
				return i;
			}
		}
		return -1;
	}

	// ����һ������v������v�������w����һ���ڽӵ������
	public int nextAdjVex(Vertex<E> v, Vertex<E> w) {
		if (v == null || w == null) {
			return -1;
		}
		int tailIndex = locateVertex(v.getData());
		int preIndex = locateVertex(w.getData());
		if (tailIndex == -1 || preIndex == -1) {
			return -1;
		}
		int adj = 0;
		if (isNet()) {
			adj = infinity;
		}
		if (arcs[tailIndex][preIndex].adj == adj) {
			return -1;
		}
		// ���w����v���ڽӵ㣬��ôֱ�ӷ��ؿ�
		for (int i = preIndex + 1; i < arcNum; i++) {
			if (arcs[tailIndex][i].adj != adj) {
				// �����/������
				return i;
			}
		}
		return -1;
	}

	private int firstAdjVex(int index) {
		if (index < 0) {
			return -1;
		}
		int adj = 0;
		if (isNet()) {
			adj = infinity;
		}
		for (int i = 0; i < arcNum; i++) {
			if (arcs[index][i].adj != adj) {
				return i;
			}
		}
		return -1;
	}

	private int nextAdjVex(int tailIndex, int preIndex) {
		if (tailIndex < 0 || preIndex < 0) {
			return -1;
		}
		int adj = 0;
		if (isNet()) {
			adj = infinity;
		}
		if (arcs[tailIndex][preIndex].adj == adj) {
			return -1;
		}
		for (int i = preIndex + 1; i < arcNum; i++) {
			if (arcs[tailIndex][i].adj != adj) {
				return i;
			}
		}
		return -1;
	}

	// ��ͼ�в���һ�����㣬��δ���������㽨����ϵ
	public void insertVex(Vertex<E> vex) {
		if (vexNum >= MAX_VERTEX_NUM || vex == null || vexs.contains(vex)) {
			return;
		}
		vexs.add(vex);

		// ��ʼ�������йصĻ�/��
		for (int i = 0; i < vexNum + 1; i++) {
			arcs[i][vexNum] = new ArcCell();
			if (arcs[vexNum][i] == null) {
				arcs[vexNum][i] = new ArcCell();
			}
			if (isNet()) {
				arcs[i][vexNum].adj = infinity;
				arcs[vexNum][i].adj = infinity;
			}
		}
		vexNum++;
	}

	// ��ͼ��ɾ��һ����㣬���˸ý��֮�⻹Ҫ����ý�������Ļ�/��ɾ��
	public void deleteVex(Vertex<E> vex) {
		if (vex == null) {
			return;
		}
		int index = locateVertex(vex.getData());
		if (index == -1) {
			return;
		}
		int adj = 0;
		// adj�����ж������߻��Ƿ���ڣ���������߻򻡵�adj != adj���ͱ�ʾ��������һ�����ڵı�
		// ����ͼ���ԣ�û�б�/������adj = 0�����������ԣ�û�б�/������adj = infinity
		if (isNet()) {
			adj = infinity;
		}
		vexs.remove(index);
		// ɾ����/��
		// ��ά�����ƶ���ȥ��index��
		for (int i = 0; i < vexNum; i++) {
			for (int j = index; j < vexNum - 1; j++) {
				if (j == index && arcs[i][j].adj != adj) {
					// ֻ�е�Ҫ�����ǵ�Ԫ��Ϊ����ɾ���Ļ�/��ʱ������Ҫ�����ж��Ƿ���ڱ�/��
					arcNum--;
				}
				arcs[i][j] = arcs[i][j + 1];
			}
			arcs[i][vexNum - 1] = null;
		}
		// ��ά�����ƶ���ȥ��index��
		// �����Ѿ�ȥ����һ�У���ô�е����ֵӦ�ü�ȥһ
		for (int i = 0; i < vexNum - 1; i++) {
			for (int j = index; j < vexNum - 1; j++) {
				if (j == index && arcs[j][i].adj != adj && isDirected()) {
					// ֻ�е�Ҫ�����ǵ�Ԫ��Ϊ����ɾ���Ļ�/��ʱ������Ҫ�����ж��Ƿ���ڱ�/��
					// ��������Ķ��ԣ�arcNum��һ�ξ͹��ˣ������ظ���
					arcNum--;
				}
				arcs[j][i] = arcs[j + 1][i];
			}
			arcs[vexNum - 1][i] = null;
		}
		vexNum--;
	}

	// ���һ����/�ߣ��������͵��ĸ��������п���
	public void insertArc(Vertex<E> tail, Vertex<E> head, int weight, String info) {
		if (tail == null || head == null) {
			return;
		}
		int tailIndex = locateVertex(tail.getData());
		int headIndex = locateVertex(head.getData());
		if (tailIndex == -1 || headIndex == -1) {
			return;
		}
		// �����ͼ����ô����Ϊ1�������������ô����ΪȨֵ
		if (isNet()) {
			arcs[tailIndex][headIndex].adj = weight;
		} else {
			arcs[tailIndex][headIndex].adj = 1;
		}
		if (info != null) {
			arcs[tailIndex][headIndex].infoType = arcs[tailIndex][headIndex].new InfoType(info);
		}
		// ���������ģ���ô�ԳƵĻ�ҲҪ����
		if (!isDirected()) {
			arcs[headIndex][tailIndex].adj = arcs[tailIndex][headIndex].adj;
			if (info != null) {
				arcs[headIndex][tailIndex].infoType = arcs[headIndex][tailIndex].new InfoType(info);
			}
		}
		arcNum++;
	}

	// ���һ����/�ߣ��������͵��ĸ��������п���
	// ʹ������
	private void insertArc(int tailIndex, int headIndex, int weight, String info) {
		if (tailIndex < 0 || headIndex < 0) {
			return;
		}
		// �����ͼ����ô����Ϊ1�������������ô����ΪȨֵ
		if (isNet()) {
			arcs[tailIndex][headIndex].adj = weight;
		} else {
			arcs[tailIndex][headIndex].adj = 1;
		}
		if (info != null) {
			arcs[tailIndex][headIndex].infoType = arcs[tailIndex][headIndex].new InfoType(info);
		}
		// ���������ģ���ô�ԳƵĻ�ҲҪ����
		if (!isDirected()) {
			arcs[headIndex][tailIndex].adj = arcs[tailIndex][headIndex].adj;
			if (info != null) {
				arcs[headIndex][tailIndex].infoType = arcs[headIndex][tailIndex].new InfoType(info);
			}
		}
		arcNum++;
	}

	// ɾ��һ����/��
	// ���������ģ���ôɾ��һ���������������ģ���ôɾ��������
	// �����ͼ����ô��0�������������ô��infinity
	public void deleteArc(Vertex<E> tail, Vertex<E> head) {
		if (tail == null || head == null) {
			return;
		}
		int tailIndex = locateVertex(tail.getData());
		int headIndex = locateVertex(head.getData());
		if (tailIndex == -1 || headIndex == -1) {
			return;
		}
		int adj = 0;
		if (isNet()) {
			adj = infinity;
		}
		arcs[tailIndex][headIndex].adj = adj;
		arcs[tailIndex][headIndex].infoType = null;
		if (!isDirected()) {
			arcs[headIndex][tailIndex].adj = adj;
			arcs[headIndex][tailIndex].infoType = null;
		}
		arcNum--;
	}

	private void deleteArc(int tailIndex, int headIndex) {
		if (tailIndex < 0 || headIndex < 0) {
			return;
		}
		int adj = 0;
		if (isNet()) {
			adj = infinity;
		}
		arcs[tailIndex][headIndex].adj = adj;
		arcs[tailIndex][headIndex].infoType = null;
		if (!isDirected()) {
			arcs[headIndex][tailIndex].adj = adj;
			arcs[headIndex][tailIndex].infoType = null;
		}
		arcNum--;
	}

	// ������ȱ���
	public void DFSTraverse() {
		// ���ʱ�־���鸳ֵfalse
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		// �����ж�����б����������ǰ����û�б����ʹ����Ͷ�����б���
		for (int i = 0; i < vexNum; i++) {
			if (!visited[i]) {
				DFS(i);
			}
			// �������ͨͼ����ô��һ�α���������ˣ�������ǣ���ô����
		}
	}

	// ��ĳ��������б������������ȸ��������ȷ���������Ȼ��˳��������ȱ��������ڽӵ�
	// �ݹ�
	private void DFS(int index) {
		visited[index] = true;
		System.out.print(vexs.get(index) + " ");
		for (int i = firstAdjVex(index); i != -1; i = nextAdjVex(index, i)) {
			if (!visited[i]) {
				DFS(i);
			}
		}
	}

	// ������ȱ�����ʹ��ѭ�������ǵݹ�
	// �����ڲ�α���
	public void BFSTraverse() {
		// ���ʱ�־���鸳ֵfalse
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		// �����ж�����б����������ǰ����û�б����ʹ����Ͷ�����б���
		int index = 0;
		for (int i = 0; i < vexNum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				queue.add(i);
				System.out.print(vexs.get(i) + "  ");
				// ȡһ��������з���
				while (!queue.isEmpty()) {
					index = queue.removeFirst();
					// index��ʼ��ָ����е�ͷ��ָ�루��һ��Ҫ���з��ʵĶ��㣩�������ѭ�����Ƿ��ʸö���������ڽӵ�
					for (int j = firstAdjVex(index); j != -1; j = nextAdjVex(index, j)) {
						if (!visited[j]) {
							visited[j] = true;
							queue.add(j);
							System.out.print(vexs.get(j) + "  ");
						}
					}
					// �����ö���������ڽӵ㣬ÿ�η���ǰ������ջ
					// ������Ϻ��ջ��ָ������ָ�����ͷ
				}
			}
			// �������ͨͼ����ô��һ�α���������ˣ�������ǣ���ô����
		}
	}

	// ��һ���Ӷ���i������s�ļ�·��
	// ʹ����������㷨
	public boolean searchPath(Vertex<E> from, Vertex<E> to) {
		if (from == null || to == null) {
			return false;
		}
		int fromIndex = locateVertex(from.getData());
		int toIndex = locateVertex(to.getData());
		if (fromIndex == -1 || toIndex == -1) {
			return false;
		}
		// ���ʱ�־���鸳ֵfalse
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		if (path == null) {
			path = new LinkedList<>();
		}
		if (DFSearch(fromIndex, toIndex)) {
			for (String step : path) {
				System.out.print(step + "  ");
			}
			path.clear();
			System.out.println("\r\nFound!");
			return true;
		} else {
			System.out.println("Not Found!");
			return false;
		}
	}

	private boolean DFSearch(int from, int to) {
		visited[from] = true;
		path.addLast("" + vexs.get(from));
		if (from == to) {
			return true;
		}
		// from->to ���Էֽ�Ϊ from + (from+next)->next
		for (int i = firstAdjVex(from); i != -1; i = nextAdjVex(from, i)) {
			if (!visited[i]) {
				if (DFSearch(i, to)) {
					return true;
				} else {
					path.removeLast();
					// ��������ȥ��������һ������ļ�¼����Ϊû���ҵ�·��
					// ����
				}
			}
		}
		return false;
	}

	public void searchMinPath(Vertex<E> from, Vertex<E> to) {
		if (from == null || to == null) {
			return;
		}
		int fromIndex = locateVertex(from.getData());
		int toIndex = locateVertex(to.getData());
		if (fromIndex == -1 || toIndex == -1) {
			return;
		}
		// ���ʱ�־���鸳ֵfalse
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		if (path == null) {
			path = new LinkedList<>();
		}
		// ���ʱ�־���鸳ֵfalse
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		LinkedList<Integer> res = null;
		if ((res = BFSearch(fromIndex, toIndex)) != null) {
			for (Integer integer : res) {
				System.out.print(vexs.get(integer) + "  ");
			}
			System.out.println("\r\nFound!");
		} else {
			System.out.println("Not Found!");
		}
		path.clear();
	}

	private LinkedList<Integer> BFSearch(int from, int to) {
		LinkedQueue queue = new LinkedQueue();
		int index = 0;
		visited[from] = true;
		queue.enQueue(from);
		while (!queue.isEmpty()) {
			index = queue.deQueue();
			// index��ʼ��ָ����е�ͷ��ָ�루��һ��Ҫ���з��ʵĶ��㣩�������ѭ�����Ƿ��ʸö���������ڽӵ�
			for (int i = firstAdjVex(index); i != -1; i = nextAdjVex(index, i)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.enQueue(i);
				}
				if (i == to) {
					return queue.path();
					// ���ص���·�������еĶ�������
				}
			}
			// �����ö���������ڽӵ㣬ÿ�η���ǰ������ջ
			// ������Ϻ��ջ��ָ������ָ�����ͷ
		}
		return null;
	}

	class Closedge {
		int vexIndex;// �ñ�������U�еĶ���
		int lowCost; // �ñߵ�Ȩֵ������ñ��Ѿ�������С����������ô��ΪhasJoined

		public Closedge(int vexIndex, int lowCost) {
			this.vexIndex = vexIndex;
			this.lowCost = lowCost;
		}
	}

	// ������С��������Prim�㷨
	// ���������closedge��
	// closedge�е�lowCostֵΪhasJoined��ʾ�������Ѿ������뵽��С��������
	public void miniSpanTreeOfPrim() {
		int vexIndex = 0;
		closedge.add(new Closedge(0, hasJoined));
		// ��0�����㣨��ʼ�Ķ��㣩���뵽��С������
		System.out.println("��ʼ����Ϊ" + this.vexs.get(vexIndex));
		for (int i = 1; i < vexNum; i++) {
			closedge.add(new Closedge(i, arcs[vexIndex][i].adj));
		}
		// ��ʱclosedge���������0���������ڵ��ڽӵ��Լ�����֮��ߵ�Ȩֵ
		// ÿ��ѭ������һ����
		for (int i = 1; i < vexNum; i++) {
			vexIndex = minWeightIndex(closedge);
			System.out.println("������:����Ϊ" + vexs.get(vexIndex) + ",ȨֵΪ" + closedge.get(vexIndex).lowCost);
			closedge.get(vexIndex).lowCost = hasJoined;
			// ��ǰ����СȨֵ�ı��Ѿ����룬��Ҫ�ڸ�����������ӵ�ǰ�������������ӵı�
			for (int j = 0; j < vexNum; j++) {
				if (arcs[vexIndex][j].adj < closedge.get(j).lowCost) {
					closedge.get(j).lowCost = arcs[vexIndex][j].adj;
					closedge.get(j).vexIndex = vexIndex;
				}
			}
			// forѭ��������closedge������ �µ�vexIndex�ıߵ���СȨֵ�ıߣ���ͬʱ�����������ıߣ�ǰ����Ȩֵû�и�С�ģ�
		}
	}

	// ��ø���������Ȩֵ��С�ı�����Ӧ�Ķ�������
	// �����ܷ���0������Ҫ>=1
	private int minWeightIndex(List<Closedge> closedge) {
		int vexIndex = 1;
		int minWeight = java.lang.Integer.MAX_VALUE;
		for (int i = 1; i < vexNum; i++) {
			// Ȩֵ����ΪhasJoined��Ҳ������������δ���뵽��С��������
			if (closedge.get(i).lowCost != hasJoined && minWeight > closedge.get(i).lowCost) {
				vexIndex = i;
				minWeight = closedge.get(i).lowCost;
			}
		}
		return vexIndex;
	}

	// ���������ڽӾ����Ӧ�ı�
	// �߰���i��j����������Լ��ñߵ�Ȩֵs
	class Edge implements Comparable<Edge> {
		int fromIndex;
		int toIndex;
		int weight;

		public Edge(int fromIndex, int toIndex, int weight) {
			this.fromIndex = fromIndex;
			this.toIndex = toIndex;
			this.weight = weight;
		}

		@Override
		public int compareTo(MetrixGraph<E>.Edge edge) {
			return weight - edge.weight;
		}

	}

	// ��С��������Kruskal
	public void miniSpanTreeOfKruskal() {
		MetrixGraph<E> graph = new MetrixGraph<>(this.vexs, this.kind);
		List<Edge> edges = edges();
		Edge edge = null;
		int edgeNum = 0;
		for (int i = 0; i < edges.size(); i++) {
			edge = edges.get(i);
			graph.insertArc(edge.fromIndex, edge.toIndex, edge.weight, null);
			if (graph.circuitExists()) {
				graph.deleteArc(edge.fromIndex, edge.toIndex);
			} else {
				System.out.println(
						"������:����Ϊ" + vexs.get(edge.fromIndex) + "--" + vexs.get(edge.toIndex) + ",ȨֵΪ" + edge.weight);
				edgeNum++;
				if (edgeNum == this.vexNum - 1) {
					return;
				}
			}
		}
	}

	// ���ͼ���Ƿ���ڻ�·
	public boolean circuitExists() {
		return false;
	}

	private List<Edge> edges() {
		List<Edge> edges = new ArrayList<>(arcNum);
		for (int i = 0; i < arcNum; i++) {
			for (int j = i + 1; j < arcNum; j++) {
				if (arcs[i][j].adj != infinity) {
					edges.add(new Edge(i, j, arcs[i][j].adj));
				}
			}
		}
		Collections.sort(edges);
		return edges;
	}

	// ʹ��������ȱ������ҹؽڵ�articulation
	// ���ܳ���ͬһ���ؽڵ��ӡ��ε���������ǲ���©
	public void findArticul() {
		// ���ʱ�־���鸳ֵnoVisited
		for (int i = 0; i < vexNum; i++) {
			visitOrder[i] = noVisited;
			low[i] = 0;
		}
		// ��Ϊ���Ա�֤����ͨͼ�����ֻҪ����һ��������ܱ���ȫ��
		// Ҫ����һ�������Ƿ��ж�������������ȷ��ʵ�һ�����㣬Ȼ���ٷ�����������
		rexCount = 0;
		visitOrder[0] = 0;
		// ���ʴ����и��ڵ�����Ϊ0����ʾ��һ��������
		int index = firstAdjVex(0);
		DFSArticul(index);
		if (rexCount < vexNum) {
			// ˵����Ҫ����������
			// ���Ҹ��ڵ��ǹؽڵ�
			System.out.println("�ؽڵ�:" + vexs.get(0));
			for (int i = nextAdjVex(0, index); i != -1; i = nextAdjVex(0, i)) {
				if (visitOrder[i] != noVisited) {
					DFSArticul(i);
				}
			}
		}
	}
	// low�����ŵ���
	// �ݹ�
	// low��w����visited��k��ʵ�����ڶ��Լ��������ڽӵ�ķ����еõ��ģ��ѷ��ʹ����ڽӵ㣨�����Լ������ȣ�ȡ����ʴ���
	// δ���ʹ����Ƚ��з��ʣ��������ȡ��lowֵ������lowֵҲ�����Լ��ķ��ʴ��������ڽӵ�ķ��ʴ�����ȡ����С�ĵõ��ģ���
	// �����ڽӵ���ʽ����͵õ����Լ���lowֵ��
	// Ҷ�ӽ��������ڽӵ㶼�Ƿ��ʹ��ģ����ȣ����ʹ����ǵķ��ʴ�����ȡ��С�ļ��ɣ��õ�lowֵ��
	// �ؽڵ���ǣ����δ���ʹ����ڽӵ�����������lowֵ���Լ��ķ��ʴ�������ȣ�
	// Ҳ�����Լ��ĺ��ӽ��û���������Լ����ʵĽ���лرߣ���ô�Լ����ǹؽڵ㡣
	// ���Լ�ɾ�����Լ��ĺ��Ӿ��޷��������Լ����ʵĽ�㽨����ϵ��

	private void DFSArticul(int index) {
		rexCount++;
		// ��ÿ����㿪ʼ��ʱ������
		visitOrder[index] = mid = rexCount;
		// ���ʴ��������mid�����˵�ǰ����ķ��ʴ���
		for (int i = firstAdjVex(index); i != -1; i = nextAdjVex(index, i)) {
			if (visitOrder[i] == noVisited) {
				// ���û�з��ʸ��ڽӵ㣬��ô�ݹ����
				DFSArticul(i);
				// ���ʽ���������˸��ڽӵ��lowֵ��midֵȡԭֵ��lowֵ�н�С��
				mid = mid > low[i] ? low[i] : mid;
				// ������ڽӵ��lowֵ�ڵ�ǰ������֮�����ͬ����ô���ж���ǰ���Ϊ�ؽڵ�
				if (low[i] >= visitOrder[index]) {
					System.out.println("�ؽڵ�:" + vexs.get(index));
				}
			} else {
				// ������ʹ���һ���ǵ�ǰ�������ڻ�˫�ף�����ô�͸���midֵΪ���ڽӵ�ķ��ʴ���
				mid = mid > visitOrder[i] ? visitOrder[i] : mid;
			}
		}
		// �����ǰ���������ڽӵ㶼������ϣ���ô��ʱmidֵ��һ��Ϊ��ȡ������Сֵ��Ҳ�����ܷ��ʵ��Ľ�����С���ֵ
		// ���浽low������
		low[index] = mid;
	}

	// Dijkstra�㷨�����·��
	public String[] shortestPathOfDijkstra(Vertex<E> source) {
		int sourceIndex = locateVertex(source.getData());
		if (sourceIndex == -1) {
			return null;
		}
		String[] path = new String[vexNum];
		int[] distance = new int[vexNum];
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
			distance[i] = arcs[sourceIndex][i].adj;
			path[i] = vexs.get(sourceIndex) + "-" + vexs.get(i);
		}
		// ʹ��visited��������ʾÿ�������Ƿ�ѡ��
		// path����Ĭ��ֵ����ΪԴ�㵽��ǰ���㣬��Ϊ�п�����������ս��
		visited[sourceIndex] = true;
		// ��ʱdistance�洢���Ǵ�Դ������Ļ���Ȩֵ�������ڵĻ�ȨֵΪinfinity
		// ��ʼ��ѭ����ÿ��ѭ�������м���һ������
		int minWeight = infinity;
		int vexIndex = 0;// ָ�������Ķ���
		// ÿ��ѭ��������һ�����㣬����ΪvexNum-1
		// iֻ���ڼ���
		for (int i = 1; i < vexNum; i++) {
			// ��ѭ��������СȨֵ�Ļ�ָ��Ķ�������
			// ע��ÿ�ζ�Ҫ���¸�minWeight��ֵ
			minWeight = infinity;
			for (int j = 0; j < vexNum; j++) {
				if (!visited[j] && minWeight > distance[j]) {
					vexIndex = j;
					minWeight = distance[j];
				}
			}
			visited[vexIndex] = true;
			// path[vexIndex]+="-"+vexs.get(vexIndex);
			// ����distance�����¼��붥�������ӵĻ�����Ƿ���֮ǰ�Ļ���Ȩֵ֮��С��distance
			// ���С�ڣ��͸���Ϊ��Сֵ
			// Ҫ���¼��붥�������ӵĶ���Ļ��Ǵ��ڵģ�����Ϊinfinity��������û�б����������Ķ���
			for (int j = 0; j < vexNum; j++) {
				if (!visited[j] && arcs[vexIndex][j].adj != infinity
						&& (minWeight + arcs[vexIndex][j].adj < distance[j])) {
					distance[j] = minWeight + arcs[vexIndex][j].adj;
					path[j] = path[vexIndex] + "-" + vexs.get(j);
					// ��path�޸�Ϊ֮ǰ����������·�����ϵ�ǰ�����ֵ�����Ǵ�Դ�㵽��ǰ���������·��
				}
			}
		}
		// ������������·����Ȩֵ
		for (int i = 0; i < vexNum; i++) {
			// Դ����Լ�����Ĭ��ֵΪinfinity�������޸�Ϊ0
			path[i] += " ȨֵΪ" + (distance[i] != infinity ? distance[i] : 0);
		}
		return path;
	}

}
