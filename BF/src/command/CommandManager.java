package command;

import java.util.Stack;
@SuppressWarnings("all")
/**
 * 
 * ���е�undo��redo����CommandManagerִ��
 * ��ִ��ÿ��Commandʱ������Command����CommandManager�����为��ִ��
 * ע���Ǹ���Command����һ��CommandManager����Ϊ����ʲô������ѡ��undo���ǳ�����һ��������������ڶ�̬���������Command�͵�������Ӧ��undo��
 * 
 * @author Administrator
 *
 */
public class CommandManager {
	
	//��ſ��Գ����Ĳ���
	private Stack<Command> undoStack;
	//��ſ��Իָ��Ĳ���
	private Stack<Command> redoStack;
	public CommandManager() {
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}
	
	//ʵ�ʵ����ֱ��ִ�У�����ʹ��CommandManager��ִ��
	public void executeCommand(Command cmd){
		cmd.execute();
		this.undoStack.push(cmd);
	}
	
	//���ò�����Ҫ������ʱ�������Լ����ã����ǵ���CommandManager��undo����
	//����������һ�β�����������������뵽�ָ�ջ�У��Ա��ָ��������Ĳ���
	public void undo(){
		if(!undoStack.isEmpty()){
			Command cmd = undoStack.pop();

			if(cmd.canUndo()){
//System.out.println(cmd+"����");
				cmd.undo();
				this.redoStack.push(cmd);
			}
		}
	}
	
	//�ָ�����ͬ��
	public void redo(){
		if(!redoStack.isEmpty()){
			Command cmd = redoStack.pop();
			if(cmd.canRedo()){
//System.out.println(cmd+"�ָ�");
				cmd.execute();
				this.undoStack.push(cmd);
			}
		}
	}
	
} 
