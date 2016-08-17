package stack;

public class Labyrinth {
	public static boolean mazePath(char[][] maze, Position start, Position end) throws CloneNotSupportedException {
		MyStack<Position> path = new MyStack<>();
		Position cur = start;
		do {
			//dir�ǵ�ǰ���λ�õ��ƶ�����
			System.out.println("��ǰλ��:" + cur.x + "," + cur.y);
			print(maze);
			if (pass(maze, cur)) {
				// ��ǰλ�ÿ���ͨ��
				footPrint(maze,cur);
				cur.dir = 1;
				path.push((Position)cur.clone());
				// ���� 1��ʾ����2��ʾ�ϣ�3��ʾ����4��ʾ��
				// ��ѹջ������һ����λ�ã����ƶ�
				if (cur.equals(end)) {
					return true;
				}
				cur = nextPos(cur);
			} else if (!path.isEmpty()) {
				// ��ǰλ�ò���ͨ������ջ�ǿգ������Լ������
				cur = path.pop();
				// �˻�һ��
				while (cur.dir == 4 && !path.isEmpty()) {
					markTrace(maze, cur);
					cur = path.pop();
					System.out.println("������"+cur.x+","+cur.y+",����Ϊ"+cur.dir);
				}
				// �������Ϊ������ʾ��·���ߣ���ô�������ˣ�ֱ������Ϊ������������ѡ�񣩻���ջ�գ��޽⣩
				if (cur.dir < 4) {
					cur.dir++;
					path.push((Position)cur.clone());
					//�ȱ任λ������ջ��Ϊ���´�����Ҫ�任λ��ʱ�����ϴε�λ�ü���
					cur = nextPos(cur);
					//������λ�ò��������ͨ�����ȴ��´�ѭ������pass��⣬�����ͨ����Ҫpop
				}
			}
		} while (!path.isEmpty());
		return false;
	}

	private static void footPrint(char[][] maze, Position cur) {
		maze[cur.x][cur.y] = '@';
		//@��ʾ�Ѿ��߹����㼣�������������ߣ�ʵ���ϱ����������ظ���Ҳ���Ƿ������⣩
	}

	private static void markTrace(char[][] maze, Position cur) {
		maze[cur.x][cur.y] = 'S';
	//	print(maze);
		//S��ʾ����ͨ���ĵط�
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
		//���ɵ��߽�
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
