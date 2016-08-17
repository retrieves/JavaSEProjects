package tree;

import java.util.Stack;


public class ExpressionTree {
	public static final char[][] prior = {
			{'>','>','<','<','>','>','>'},
			{'>','>','<','<','>','>','>'},
			{'>','>','>','>','>','>','>'},
			{'>','>','>','>','>','>','>'}, 
			{'<','<','<','<','>',' ','>'},
			{' ',' ',' ',' ','=',' ',' '},
			{' ',' ',' ',' ',' ',' ','='}
	};
	public static final char[] operands = {'+','-','*','/','(',')','#'};
	//�����ַ��Ƿ�Ϊ�����������ǣ�������operands�����е����������򷵻�-1
	private static int indexOf(char op){
		for (int i = 0; i < operands.length; i++) {
			if(op == operands[i]){
				return i;
			}
		}
		return -1;
	}
	//����������������λ���ҵ����ȼ��б��еĶ��ߵ����ȼ�
	private static char precede(char opA,char opB){
		return prior[indexOf(opA)][indexOf(opB)];
	}
	
	public static BiTree<Character> createExpressionTree(String exp){
		
		Stack<TreeNode<Character>> nodes = new Stack<>();
		Stack<Character> opnd = new Stack<>();
		opnd.push('#');
		//��Ϊ������ջ��ʼ�ͽ���ʱ�ı�־
		char op = 0;
		TreeNode<Character> Lchild = null;
		TreeNode<Character> Rchild = null;
		TreeNode<Character> root = null;
		char [] exps = exp.toCharArray();
		for (int i = 0; i < exps.length; i++) {
			if(indexOf(exps[i]) != -1){
				switch(precede(exps[i], opnd.peek())){
				case '>':
					opnd.push(exps[i]);
					break;
				//�����ǰ����������ȼ�����ջ�������������ջ
				case '<':
				//�����ǰ����������ȼ�����ջ������������ȳ�ջ����ջ
				//����ջ�����������ջ�е�ǰ������㽨���������ѹ����ջ
					op = opnd.pop();
					Lchild = nodes.pop();
					Rchild = nodes.pop();
					root = new TreeNode<>(op, Lchild, Rchild);
					nodes.push(root);
					opnd.push(exps[i]);
					break;
				case ' ':
					while(precede(exps[i], (op = opnd.pop())) != '='){
						Rchild = nodes.pop();
						Lchild = nodes.pop();
						root = new TreeNode<>(op, Lchild, Rchild);
						nodes.push(root);
					}
					//���ֿհ׷��������������һ���������������������ŵ��������ʱ�᲻�ϳ�ջֱ�������ų�ջ����ջ��ͽ���������<������Ĳ���
					//��һ��������#����ʾ���ʽ��������ʱ���ϳ�ջֱ�����������ջջ�׵�#����ջ���
					break;
				}
			}else{
				//�������ͽ�����㣬��ѹ����ջ����Ϊ��������
				Lchild = new TreeNode<>(exps[i]);
				nodes.push(Lchild);
			}
		}
		root = nodes.pop();
		//��������������������ջΪ�գ����ջֻ��һ��Ԫ�أ����Ǹ��ڵ�
		return new BiTree<>(root);
	}
	public static void main(String[] args) {
		String exp = "(a+b)*c-d/e#";
		//���еı��ʽҪ����#����
		BiTree<Character> tree = ExpressionTree.createExpressionTree(exp);
		tree.preOrder(tree.root());
		System.out.println();
		tree.inOrder(tree.root());
	}
}
