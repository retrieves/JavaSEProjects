package stack;

public class NumberSystemConversion {
	public static int convert(int num,int destNS){
		MyStack<Integer> stack = new MyStack<>();
		StringBuilder sb = new StringBuilder();
		while(num != 0){
			stack.push(num%destNS);
			num /= destNS;
		}
		while(!stack.isEmpty()){
			sb.append(stack.pop()+"");
		}
		return Integer.parseInt(sb.toString());
	}
	
	public static void main(String[] args) {
		int num = 1348;
		int d = 8;
		System.out.println(NumberSystemConversion.convert(num,d));
	}
}
