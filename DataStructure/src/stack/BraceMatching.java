package stack;

public class BraceMatching {
	public static boolean match(String str) {
		MyStack<Character> stack = new MyStack<>();
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case '(':
			case '[':
			case '{':
				stack.push(str.charAt(i));
				break;
			case ')':
				if(stack.peek().equals('(')){
					stack.pop();
					break;
				}else{
					return false;
				}
			case ']':
				if(stack.peek().equals('[')){
					stack.pop();
					break;
				}else{
					return false;
				}
			case '}':
				if(stack.peek().equals('{')){
					stack.pop();
					break;
				}else{
					return false;
				}
			default:
				break;	
			}
		}
		if(stack.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		System.out.println(BraceMatching.match("([]())"));
		System.out.println(BraceMatching.match("[([][])]"));
		System.out.println(BraceMatching.match("[(])"));
		System.out.println(BraceMatching.match("([())"));
		System.out.println(BraceMatching.match("(()])"));
	}
}
