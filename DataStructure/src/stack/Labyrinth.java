package stack;

public class Labyrinth {
	public static boolean mazePath(char[][] maze, Position start, Position end) throws CloneNotSupportedException {
		MyStack<Position> path = new MyStack<>();
		Position cur = start;
		do {
			//dir是当前这个位置的移动方向
			System.out.println("当前位置:" + cur.x + "," + cur.y);
			print(maze);
			if (pass(maze, cur)) {
				// 当前位置可以通过
				footPrint(maze,cur);
				cur.dir = 1;
				path.push((Position)cur.clone());
				// 方向 1表示东，2表示南，3表示西，4表示北
				// 先压栈保存上一步的位置，后移动
				if (cur.equals(end)) {
					return true;
				}
				cur = nextPos(cur);
			} else if (!path.isEmpty()) {
				// 当前位置不可通过，且栈非空，即可以继续求解
				cur = path.pop();
				// 退回一步
				while (cur.dir == 4 && !path.isEmpty()) {
					markTrace(maze, cur);
					cur = path.pop();
					System.out.println("回退至"+cur.x+","+cur.y+",方向为"+cur.dir);
				}
				// 如果方向为北，表示无路可走，那么持续回退，直至方向不为北（还有其他选择）或者栈空（无解）
				if (cur.dir < 4) {
					cur.dir++;
					path.push((Position)cur.clone());
					//先变换位置再入栈是为了下次仍需要变换位置时接着上次的位置继续
					cur = nextPos(cur);
					//设置了位置不代表可以通过，等待下次循环调用pass检测，如果不通过还要pop
				}
			}
		} while (!path.isEmpty());
		return false;
	}

	private static void footPrint(char[][] maze, Position cur) {
		maze[cur.x][cur.y] = '@';
		//@表示已经走过的足迹，不能重新再走（实际上避免了来回重复，也就是方向问题）
	}

	private static void markTrace(char[][] maze, Position cur) {
		maze[cur.x][cur.y] = 'S';
	//	print(maze);
		//S表示不可通过的地方
	}

	private static Position nextPos(Position pos) {
		switch (pos.dir) {
		case 1:
			pos.y++;
			break;
		case 2:
			pos.x++;
			break;
		case 3:
			pos.y--;
			break;
		case 4:
			pos.x--;
			break;
		}
		//pos.dir = 1;
		return pos;
	}

	public static void print(char[][] maze) {
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for (int i = 0; i < maze.length; i++) {
			System.out.print(i+" ");
			for (int j = 0; j < maze.length; j++) {
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static boolean pass(char[][] maze, Position pos) {
		int x = pos.x;
		int y = pos.y;
		if(x < 1 || x >= maze.length-1 || y < 1 ||y >= maze[0].length-1 ){
			return false;
		}
		//不可到边界
		if (maze[x][y] == '_' ) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		char[][] maze = { 
					{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
					{ '#', '_', '_', '#', '_', '_', '_', '#', '_', '#' },
					{ '#', '_', '_', '#', '_', '_', '_', '#', '_', '#' },
					{ '#', '_', '_', '_', '_', '#', '#', '_', '_', '#' },
					{ '#', '_', '#', '#', '#', '_', '_', '_', '_', '#' },
					{ '#', '_', '_', '_', '#', '_', '_', '_', '_', '#' },
					{ '#', '_', '#', '_', '_', '_', '#', '_', '_', '#' },
					{ '#', '_', '#', '#', '#', '_', '#', '#', '_', '#' },
					{ '#', '#', '_', '_', '_', '_', '_', '_', '_', '#' },
					{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' } 
				};
		boolean res = mazePath(maze, new Position(1, 1, 1), new Position(8, 8, 1));
		System.out.println(res);
	}
}

class Position implements Cloneable {
	int x;
	int y;
	int dir;

	public Position(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}

	public boolean equals(Position pos) {
		return x == pos.x && y == pos.y;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
