package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

//分别表示有向图，有向网，无向图，无向网
enum GraphKind {
	DG, DN, UDG, UDN
}

// 表示弧或边 类型
class ArcCell {
	int adj;
	// 对于图来说，用1表示顶点相邻，用0表示顶点不相邻
	// 对于网来说，表示权值类型，正常的数值表示其权值，无穷表示没有连接或者自己和自己
	InfoType infoType;

	public ArcCell() {
		// TODO Auto-generated constructor stub
	}

	public ArcCell(int adj) {
		this.adj = adj;
	}

	class InfoType {
		String info;

		// 该弧相关信息的类
		public InfoType(String info) {
			this.info = info;
		}
	}

	@Override
	public String toString() {
		return adj + (infoType != null ? infoType.info : "");
	}
}

// 表示顶点类型
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
 * 数组表示法/邻接矩阵表示法
 * 
 * @author New Song
 *
 */
public class MetrixGraph<E extends Comparable<E>> {
	public static final int MAX_VERTEX_NUM = 20;
	private static final int infinity = java.lang.Integer.MAX_VALUE;// 表示网中的无限
	private static final int hasJoined = 0;
	private static final int noVisited = -1;
	private List<Vertex<E>> vexs; // 顶点
	private ArcCell[][] arcs; // 邻接矩阵
	private int vexNum, arcNum; // 图的当前顶点数和弧数
	private GraphKind kind; // 图的类型
	private boolean[] visited;// 访问标志数组，记录了所有顶点在遍历过程中是否被访问

	private int[] visitOrder;// 访问标志数组，记录了所有顶点在遍历过程中的访问次序，用于求关节点
	private int[] low;// 记录当前结点所能达到的最小序号值，用于求关节点
	private int rexCount;// 访问左子树的时候用于记录经过的顶点数，用于求关节点
	private int mid;// mid记录当前顶点能够达到的最小序号值，用于求关节点

	private LinkedList<String> path;
	private List<Closedge> closedge;// 用于生成最小生成树的辅助数组

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

	// 这个构造方法专门用于Kruskal算法的最小生成树使用
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

	// 在图中查找一个顶点在数组中的位置/索引
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

	// 创建有向图
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

	// 创建有向网，网和图区别在于弧/边上的VRType不同，初始化方式也不同
	// 需要增加一个参数，用于设置权值
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

	// 创建无向图，与有向图的区别在于如果建立了i到j的弧，同时也要建立j到i的弧
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

	// 创建无向网
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

	// 打印顶点和弧/边
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
		// 如果是有方向的，那么全部输出
		// 如果是无向的，那么输出一半即可
		// 注意打印时，根据顶点的数量来打印而不是根据弧的数量来打印
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

	// 给出一个顶点，返回该顶点的值
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

	// 判断这个图中的顶点是否存在值为data的顶点
	public boolean contains(E data) {
		for (int i = 0; i < vexs.size(); i++) {
			if (vexs.get(i).getData().equals(data)) {
				return true;
			}
		}
		return false;
	}

	// 给出一个顶点和该顶点的新值，将该顶点的值赋为新值
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

	// 给出一个顶点，返回这个顶点的第一个邻接点的索引（如果存在）
	// 如果是有向的，返回以该顶点为弧尾的第一个邻接点
	// 如果是无向的，返回边的第一个邻接点
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
				// 如果边/弧存在
				return i;
			}
		}
		return -1;
	}

	// 给出一个顶点v，返回v的相对于w的下一个邻接点的索引
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
		// 如果w不是v的邻接点，那么直接返回空
		for (int i = preIndex + 1; i < arcNum; i++) {
			if (arcs[tailIndex][i].adj != adj) {
				// 如果边/弧存在
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

	// 在图中插入一个顶点，尚未与其他顶点建立联系
	public void insertVex(Vertex<E> vex) {
		if (vexNum >= MAX_VERTEX_NUM || vex == null || vexs.contains(vex)) {
			return;
		}
		vexs.add(vex);

		// 初始化与其有关的弧/边
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

	// 在图中删除一个结点，除了该结点之外还要将与该结点相连的弧/边删除
	public void deleteVex(Vertex<E> vex) {
		if (vex == null) {
			return;
		}
		int index = locateVertex(vex.getData());
		if (index == -1) {
			return;
		}
		int adj = 0;
		// adj用于判断这条边或弧是否存在，如果这条边或弧的adj != adj，就表示这条边是一个存在的边
		// 对于图而言，没有边/弧就是adj = 0；对于网而言，没有边/弧就是adj = infinity
		if (isNet()) {
			adj = infinity;
		}
		vexs.remove(index);
		// 删除弧/边
		// 二维数组移动，去掉index列
		for (int i = 0; i < vexNum; i++) {
			for (int j = index; j < vexNum - 1; j++) {
				if (j == index && arcs[i][j].adj != adj) {
					// 只有当要被覆盖的元素为将被删除的弧/边时，才需要进行判断是否存在边/弧
					arcNum--;
				}
				arcs[i][j] = arcs[i][j + 1];
			}
			arcs[i][vexNum - 1] = null;
		}
		// 二维数组移动，去掉index行
		// 由于已经去掉了一列，那么列的最大值应该减去一
		for (int i = 0; i < vexNum - 1; i++) {
			for (int j = index; j < vexNum - 1; j++) {
				if (j == index && arcs[j][i].adj != adj && isDirected()) {
					// 只有当要被覆盖的元素为将被删除的弧/边时，才需要进行判断是否存在边/弧
					// 对于无向的而言，arcNum减一次就够了，不用重复减
					arcNum--;
				}
				arcs[j][i] = arcs[j + 1][i];
			}
			arcs[vexNum - 1][i] = null;
		}
		vexNum--;
	}

	// 添加一条弧/边，第三个和第四个参数可有可无
	public void insertArc(Vertex<E> tail, Vertex<E> head, int weight, String info) {
		if (tail == null || head == null) {
			return;
		}
		int tailIndex = locateVertex(tail.getData());
		int headIndex = locateVertex(head.getData());
		if (tailIndex == -1 || headIndex == -1) {
			return;
		}
		// 如果是图，那么设置为1，如果是网，那么设置为权值
		if (isNet()) {
			arcs[tailIndex][headIndex].adj = weight;
		} else {
			arcs[tailIndex][headIndex].adj = 1;
		}
		if (info != null) {
			arcs[tailIndex][headIndex].infoType = arcs[tailIndex][headIndex].new InfoType(info);
		}
		// 如果是无向的，那么对称的弧也要设置
		if (!isDirected()) {
			arcs[headIndex][tailIndex].adj = arcs[tailIndex][headIndex].adj;
			if (info != null) {
				arcs[headIndex][tailIndex].infoType = arcs[headIndex][tailIndex].new InfoType(info);
			}
		}
		arcNum++;
	}

	// 添加一条弧/边，第三个和第四个参数可有可无
	// 使用索引
	private void insertArc(int tailIndex, int headIndex, int weight, String info) {
		if (tailIndex < 0 || headIndex < 0) {
			return;
		}
		// 如果是图，那么设置为1，如果是网，那么设置为权值
		if (isNet()) {
			arcs[tailIndex][headIndex].adj = weight;
		} else {
			arcs[tailIndex][headIndex].adj = 1;
		}
		if (info != null) {
			arcs[tailIndex][headIndex].infoType = arcs[tailIndex][headIndex].new InfoType(info);
		}
		// 如果是无向的，那么对称的弧也要设置
		if (!isDirected()) {
			arcs[headIndex][tailIndex].adj = arcs[tailIndex][headIndex].adj;
			if (info != null) {
				arcs[headIndex][tailIndex].infoType = arcs[headIndex][tailIndex].new InfoType(info);
			}
		}
		arcNum++;
	}

	// 删除一条弧/边
	// 如果是有向的，那么删除一条弧；如果是无向的，那么删除两条弧
	// 如果是图，那么置0；如果是网，那么置infinity
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

	// 深度优先遍历
	public void DFSTraverse() {
		// 访问标志数组赋值false
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		// 对所有顶点进行遍历，如果当前顶点没有被访问过，就对其进行遍历
		for (int i = 0; i < vexNum; i++) {
			if (!visited[i]) {
				DFS(i);
			}
			// 如果是连通图，那么第一次遍历就完成了，如果不是，那么继续
		}
	}

	// 对某个顶点进行遍历，类似于先根遍历，先访问数据域，然后顺序深度优先遍历各个邻接点
	// 递归
	private void DFS(int index) {
		visited[index] = true;
		System.out.print(vexs.get(index) + " ");
		for (int i = firstAdjVex(index); i != -1; i = nextAdjVex(index, i)) {
			if (!visited[i]) {
				DFS(i);
			}
		}
	}

	// 广度优先遍历，使用循环而不是递归
	// 类似于层次遍历
	public void BFSTraverse() {
		// 访问标志数组赋值false
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		// 对所有顶点进行遍历，如果当前顶点没有被访问过，就对其进行遍历
		int index = 0;
		for (int i = 0; i < vexNum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				queue.add(i);
				System.out.print(vexs.get(i) + "  ");
				// 取一个顶点进行访问
				while (!queue.isEmpty()) {
					index = queue.removeFirst();
					// index是始终指向队列的头的指针（下一个要进行访问的顶点），下面的循环就是访问该顶点的所有邻接点
					for (int j = firstAdjVex(index); j != -1; j = nextAdjVex(index, j)) {
						if (!visited[j]) {
							visited[j] = true;
							queue.add(j);
							System.out.print(vexs.get(j) + "  ");
						}
					}
					// 遍历该顶点的所有邻接点，每次访问前进行入栈
					// 访问完毕后出栈，指针重新指向队列头
				}
			}
			// 如果是连通图，那么第一次遍历就完成了，如果不是，那么继续
		}
	}

	// 求一条从顶点i到顶点s的简单路径
	// 使用深度优先算法
	public boolean searchPath(Vertex<E> from, Vertex<E> to) {
		if (from == null || to == null) {
			return false;
		}
		int fromIndex = locateVertex(from.getData());
		int toIndex = locateVertex(to.getData());
		if (fromIndex == -1 || toIndex == -1) {
			return false;
		}
		// 访问标志数组赋值false
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
		// from->to 可以分解为 from + (from+next)->next
		for (int i = firstAdjVex(from); i != -1; i = nextAdjVex(from, i)) {
			if (!visited[i]) {
				if (DFSearch(i, to)) {
					return true;
				} else {
					path.removeLast();
					// 在链表中去掉访问上一个顶点的记录，因为没有找到路径
					// 回退
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
		// 访问标志数组赋值false
		for (int i = 0; i < vexNum; i++) {
			visited[i] = false;
		}
		if (path == null) {
			path = new LinkedList<>();
		}
		// 访问标志数组赋值false
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
			// index是始终指向队列的头的指针（下一个要进行访问的顶点），下面的循环就是访问该顶点的所有邻接点
			for (int i = firstAdjVex(index); i != -1; i = nextAdjVex(index, i)) {
				if (!visited[i]) {
					visited[i] = true;
					queue.enQueue(i);
				}
				if (i == to) {
					return queue.path();
					// 返回的是路径上所有的顶点索引
				}
			}
			// 遍历该顶点的所有邻接点，每次访问前进行入栈
			// 访问完毕后出栈，指针重新指向队列头
		}
		return null;
	}

	class Closedge {
		int vexIndex;// 该边依附在U中的顶点
		int lowCost; // 该边的权值，如果该边已经并入最小生成树，那么置为hasJoined

		public Closedge(int vexIndex, int lowCost) {
			this.vexIndex = vexIndex;
			this.lowCost = lowCost;
		}
	}

	// 生成最小生成树的Prim算法
	// 结果保存在closedge中
	// closedge中的lowCost值为hasJoined表示该条边已经被加入到最小生成树中
	public void miniSpanTreeOfPrim() {
		int vexIndex = 0;
		closedge.add(new Closedge(0, hasJoined));
		// 第0个顶点（开始的顶点）加入到最小生成树
		System.out.println("起始顶点为" + this.vexs.get(vexIndex));
		for (int i = 1; i < vexNum; i++) {
			closedge.add(new Closedge(i, arcs[vexIndex][i].adj));
		}
		// 此时closedge保存了与第0个顶点相邻的邻接点以及它们之间边的权值
		// 每次循环加入一条边
		for (int i = 1; i < vexNum; i++) {
			vexIndex = minWeightIndex(closedge);
			System.out.println("建立边:顶点为" + vexs.get(vexIndex) + ",权值为" + closedge.get(vexIndex).lowCost);
			closedge.get(vexIndex).lowCost = hasJoined;
			// 当前的最小权值的边已经加入，需要在辅助数组中添加当前新增顶点所连接的边
			for (int j = 0; j < vexNum; j++) {
				if (arcs[vexIndex][j].adj < closedge.get(j).lowCost) {
					closedge.get(j).lowCost = arcs[vexIndex][j].adj;
					closedge.get(j).vexIndex = vexIndex;
				}
			}
			// for循环结束后，closedge新增了 新的vexIndex的边的最小权值的边，但同时保留了以往的边（前提是权值没有更小的）
		}
	}

	// 获得辅助数组中权值最小的边所对应的顶点索引
	// 不可能返回0，起码要>=1
	private int minWeightIndex(List<Closedge> closedge) {
		int vexIndex = 1;
		int minWeight = java.lang.Integer.MAX_VALUE;
		for (int i = 1; i < vexNum; i++) {
			// 权值不能为hasJoined，也就是这条边尚未加入到最小生成树中
			if (closedge.get(i).lowCost != hasJoined && minWeight > closedge.get(i).lowCost) {
				vexIndex = i;
				minWeight = closedge.get(i).lowCost;
			}
		}
		return vexIndex;
	}

	// 用于生成邻接矩阵对应的边
	// 边包含i和j顶点的索引以及该边的权值s
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

	// 最小生成树的Kruskal
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
						"建立边:顶点为" + vexs.get(edge.fromIndex) + "--" + vexs.get(edge.toIndex) + ",权值为" + edge.weight);
				edgeNum++;
				if (edgeNum == this.vexNum - 1) {
					return;
				}
			}
		}
	}

	// 检测图中是否存在回路
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

	// 使用深度优先遍历查找关节点articulation
	// 可能出现同一个关节点打印多次的情况，但是不会漏
	public void findArticul() {
		// 访问标志数组赋值noVisited
		for (int i = 0; i < vexNum; i++) {
			visitOrder[i] = noVisited;
			low[i] = 0;
		}
		// 因为可以保证是连通图，因此只要访问一个顶点就能遍历全部
		// 要检测第一个顶点是否有多棵子树，所以先访问第一个顶点，然后再访问其他顶点
		rexCount = 0;
		visitOrder[0] = 0;
		// 访问次序中根节点设置为0，表示第一个被访问
		int index = firstAdjVex(0);
		DFSArticul(index);
		if (rexCount < vexNum) {
			// 说明还要访问右子树
			// 并且根节点是关节点
			System.out.println("关节点:" + vexs.get(0));
			for (int i = nextAdjVex(0, index); i != -1; i = nextAdjVex(0, i)) {
				if (visitOrder[i] != noVisited) {
					DFSArticul(i);
				}
			}
		}
	}
	// low数组存放的是
	// 递归
	// low【w】和visited【k】实际上在对自己的所有邻接点的访问中得到的，已访问过的邻接点（就是自己的祖先）取其访问次序，
	// 未访问过的先进行访问，访问完后取其low值（它的low值也是在自己的访问次序、所有邻接点的访问次序中取其最小的得到的）。
	// 所有邻接点访问结束就得到了自己的low值。
	// 叶子结点的所有邻接点都是访问过的（祖先），就从它们的访问次序中取最小的即可，得到low值。
	// 关节点就是：如果未访问过的邻接点访问完后它的low值比自己的访问次序大或相等，
	// 也就是自己的孩子结点没有与先于自己访问的结点有回边，那么自己就是关节点。
	// 将自己删掉，自己的孩子就无法与先于自己访问的结点建立联系。

	private void DFSArticul(int index) {
		rexCount++;
		// 在每个结点开始的时候自增
		visitOrder[index] = mid = rexCount;
		// 访问次序数组和mid保存了当前顶点的访问次序
		for (int i = firstAdjVex(index); i != -1; i = nextAdjVex(index, i)) {
			if (visitOrder[i] == noVisited) {
				// 如果没有访问该邻接点，那么递归访问
				DFSArticul(i);
				// 访问结束后就有了该邻接点的low值，mid值取原值与low值中较小的
				mid = mid > low[i] ? low[i] : mid;
				// 如果该邻接点的low值在当前结点访问之后或相同，那么可判定当前结点为关节点
				if (low[i] >= visitOrder[index]) {
					System.out.println("关节点:" + vexs.get(index));
				}
			} else {
				// 如果访问过（一定是当前结点的祖宗或双亲），那么就更新mid值为该邻接点的访问次序
				mid = mid > visitOrder[i] ? visitOrder[i] : mid;
			}
		}
		// 如果当前结点的所有邻接点都访问完毕，那么此时mid值就一定为能取到的最小值，也就是能访问到的结点的最小序号值
		// 保存到low数组中
		low[index] = mid;
	}

	// Dijkstra算法求最短路径
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
		// 使用visited数组来表示每个顶点是否被选择
		// path数组默认值设置为源点到当前顶点，因为有可能这就是最终结果
		visited[sourceIndex] = true;
		// 此时distance存储的是从源点伸出的弧的权值，不存在的弧权值为infinity
		// 开始主循环，每次循环向结果中加入一个顶点
		int minWeight = infinity;
		int vexIndex = 0;// 指向新增的顶点
		// 每次循环都加入一个顶点，次数为vexNum-1
		// i只用于计数
		for (int i = 1; i < vexNum; i++) {
			// 内循环返回最小权值的弧指向的顶点索引
			// 注意每次都要重新给minWeight赋值
			minWeight = infinity;
			for (int j = 0; j < vexNum; j++) {
				if (!visited[j] && minWeight > distance[j]) {
					vexIndex = j;
					minWeight = distance[j];
				}
			}
			visited[vexIndex] = true;
			// path[vexIndex]+="-"+vexs.get(vexIndex);
			// 更新distance，将新加入顶点所连接的弧检查是否与之前的弧的权值之和小于distance
			// 如果小于，就更新为最小值
			// 要求新加入顶点所连接的顶点的弧是存在的，不能为infinity；并且是没有被加入结果集的顶点
			for (int j = 0; j < vexNum; j++) {
				if (!visited[j] && arcs[vexIndex][j].adj != infinity
						&& (minWeight + arcs[vexIndex][j].adj < distance[j])) {
					distance[j] = minWeight + arcs[vexIndex][j].adj;
					path[j] = path[vexIndex] + "-" + vexs.get(j);
					// 将path修改为之前建立的完整路径加上当前顶点的值，就是从源点到当前顶点的完整路径
				}
			}
		}
		// 在最后加上这条路径的权值
		for (int i = 0; i < vexNum; i++) {
			// 源点和自己由于默认值为infinity，所以修改为0
			path[i] += " 权值为" + (distance[i] != infinity ? distance[i] : 0);
		}
		return path;
	}

}
