package stack;

public class Hanoi {
	public static void main(String[] args) {
		int n = 5;
		hanoi(n, 'A', 'B', 'C');
	}

	public static void hanoi(int n, char from, char via, char to) {
		if (1 == n) {
			System.out.printf("move %d from %c to %c\n", n, from, to);
		} else {
			hanoi(n - 1, from, to, via);
			System.out.printf("move %d from %c to %c\n", n, from, to);
			hanoi(n - 1, via, from, to);
		}
	}
}
