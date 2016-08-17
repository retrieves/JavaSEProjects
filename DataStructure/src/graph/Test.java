package graph;

public class Test {
	public static void main(String[] args) {
		// 有向图测试
		/*
		 * String [] data = {"v1","v2","v3","v4"}; String [] tail =
		 * {"v1","v1","v3","v4"}; String [] head = {"v2","v3","v4","v1"};
		 * MetrixGraph<String> graph = MetrixGraph.createGraph(GraphKind.DG,
		 * data, tail, head,null, null); graph.display();
		 * System.out.println("--------------------");
		 */
		// graph.DFSTraverse();
		/*
		 * graph.deleteVex(new Vertex<>("v3")); graph.display();
		 * graph.insertVex(new Vertex<>("v5")); graph.insertArc(null, new
		 * Vertex<>("v4"), 0, null);
		 * 
		 * graph.destroy(); graph.display();
		 * 
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v1")));
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v2")));
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v4")));
		 * System.out.println("------------------");
		 * System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new
		 * Vertex<>("v3"))); System.out.println(graph.nextAdjVex(new
		 * Vertex<>("v1"), new Vertex<>("v2")));
		 * System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new
		 * Vertex<>("v4")));
		 * 
		 * graph.putVertex(new Vertex<>("v3"), "v5"); graph.display();
		 * System.out.println(graph.getVertex(new Vertex<>("v3")));
		 * graph.deleteArc(new Vertex<>("v1"),new Vertex<>("v3"));
		 * graph.display(); graph.deleteArc(new Vertex<>("v2"),new
		 * Vertex<>("v4")); graph.display();
		 */
		/*
		 * //有向网测试 String [] data = {"v1","v2","v3","v4"}; String [] tail =
		 * {"v1","v1","v3","v4"}; String [] head = {"v2","v3","v4","v1"}; int []
		 * weight = {3,5,6,9}; MetrixGraph<String> graph =
		 * MetrixGraph.createGraph(GraphKind.DN, data, tail, head,weight, null);
		 * graph.display(); System.out.println("--------------------");
		 * graph.deleteVex(new Vertex<>("v3")); graph.display();
		 * graph.insertVex(new Vertex<>("v5")); graph.insertArc(new
		 * Vertex<>("v5"), new Vertex<>("v4"), 23, null); graph.insertArc(new
		 * Vertex<>("v4"), new Vertex<>("v5"), 12, null); // graph.destroy();
		 * graph.display();
		 * 
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v1")));
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v2")));
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v5")));
		 * System.out.println("------------------");
		 * System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new
		 * Vertex<>("v3"))); System.out.println(graph.nextAdjVex(new
		 * Vertex<>("v4"), new Vertex<>("v1")));
		 * System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new
		 * Vertex<>("v4"))); System.out.println("-------------------");
		 * graph.putVertex(new Vertex<>("v5"), "v7"); graph.display();
		 * System.out.println(graph.getVertex(new Vertex<>("v7")));
		 * graph.deleteArc(new Vertex<>("v1"),new Vertex<>("v3"));
		 * graph.display(); graph.deleteArc(new Vertex<>("v2"),new
		 * Vertex<>("v4")); graph.display();
		 */

		// 无向图测试
		/*
		 * String [] data = {"v1","v2","v3","v4","v5"}; String [] tail =
		 * {"v1","v1","v2","v2","v3","v3"}; String [] head =
		 * {"v2","v4","v3","v5","v4","v5"}; MetrixGraph<String> graph =
		 * MetrixGraph.createGraph(GraphKind.UDG, data, tail, head,null, null);
		 * graph.display(); System.out.println("--------------------");
		 */
		/*
		 * graph.deleteVex(new Vertex<>("v3")); graph.display();
		 * graph.insertVex(new Vertex<>("v6")); graph.insertArc(new
		 * Vertex<>("v6"), new Vertex<>("v4"), 0, null); graph.display();
		 * graph.destroy(); graph.display();
		 */
		/*
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v1")));
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v2")));
		 * System.out.println(graph.firstAdjVex(new Vertex<>("v4")));
		 * System.out.println("------------------");
		 * System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new
		 * Vertex<>("v3"))); System.out.println(graph.nextAdjVex(new
		 * Vertex<>("v1"), new Vertex<>("v2")));
		 * System.out.println(graph.nextAdjVex(new Vertex<>("v1"), new
		 * Vertex<>("v4")));
		 */
		// graph.insertVex(new Vertex<>("v3"));
		// System.out.println(graph.putVertex(new Vertex<>("v3"), "v5"));;
		// graph.display();

		/*
		 * System.out.println(graph.getVertex(new Vertex<>("v3")));
		 * graph.deleteArc(new Vertex<>("v1"),new Vertex<>("v4"));
		 * graph.display(); graph.deleteArc(new Vertex<>("v3"),new
		 * Vertex<>("v5")); graph.display();
		 */
		// graph.DFSTraverse();

		// 课本示例，无向图
		/*
		 * String [] data = {"A","B","C","D","E","F","G"}; String [] tail =
		 * {"A","A","A","A","A","A","B","D","F"}; String [] head =
		 * {"B","C","D","E","F","G","C","E","G"}; MetrixGraph<String> graph =
		 * MetrixGraph.createGraph(GraphKind.UDG, data, tail, head,null, null);
		 * graph.display(); System.out.println("--------------------");
		 * graph.BFSTraverse(); graph.searchPath(new Vertex<>("F"), new
		 * Vertex<>("C"));
		 */

		/*
		 * //测试求最短路径 String [] data = {"1","2","3","4","5","6","7","8","9"};
		 * String [] tail = {"1","1","1","1","2","4","4","5","6","7","7","8"};
		 * String [] head = {"2","3","4","7","3","5","6","6","8","8","9","9"};
		 * MetrixGraph<String> graph = MetrixGraph.createGraph(GraphKind.UDG,
		 * data, tail, head,null, null); graph.display();
		 * graph.searchMinPath(new Vertex<>("9"),new Vertex<>("1"));
		 */

		/*
		 * // 测试最小生成树 String[] data = { "A", "B", "C", "D", "E", "F", "G", "H"
		 * }; String[] tail = { "A", "A", "B", "B", "B", "C", "C", "D", "D",
		 * "D", "D", "E", "F", "G" }; String[] head = { "B", "C", "C", "D", "E",
		 * "D", "H", "E", "F", "G", "H", "F", "G", "H" }; int[] weight = { 4, 2,
		 * 3, 5, 9, 5, 5, 7, 6, 5, 4, 3, 2, 6 }; MetrixGraph<String> graph =
		 * MetrixGraph.createGraph(GraphKind.UDN, data, tail, head, weight,
		 * null); graph.display(); System.out.println("--------------------");
		 * // graph.miniSpanTreeOfPrim(); graph.miniSpanTreeOfKruskal();
		 */

		/*
		 * // 测试求关节点 String[] data = { "A", "B", "C", "D", "E", "F", "G", "H" };
		 * String[] tail = { "A", "A", "B", "C", "C","C", "A", "A" ,"G"};
		 * String[] head = { "B", "F", "C", "D", "E", "F","G", "H" ,"H"};
		 * MetrixGraph<String> graph = MetrixGraph.createGraph(GraphKind.UDG,
		 * data, tail, head, null, null); graph.findArticul();
		 */

		// 测试Dijkstra算法求最短路径
		String[] data = { "A", "B", "C", "D", "E", "F", "G", };
		String[] tail = { "A", "A", "A", "B", "C", "C", "D", "E", "F", "F", "G" };
		String[] head = { "B", "C", "D", "E", "E", "F", "G", "G", "D", "G", "B" };
		int [] weight = {15,2,10,6,7,4,4,9,5,10,4};
		MetrixGraph<String> graph = MetrixGraph.createGraph(GraphKind.DN, data, tail, head, weight, null);
		String[] path = graph.shortestPathOfDijkstra(new Vertex<>("A"));
		for(String step:path){
			System.out.println(step);
		}
	}
}
