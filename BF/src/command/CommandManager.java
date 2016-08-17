package command;

import java.util.Stack;
@SuppressWarnings("all")
/**
 * 
 * 所有的undo和redo都由CommandManager执行
 * 当执行每个Command时，将该Command传入CommandManager，由其负责执行
 * 注意是各种Command共用一个CommandManager，因为无论什么操作，选择undo都是撤销上一个操作结果（存在多态，针对哪种Command就调用其相应的undo）
 * 
 * @author Administrator
 *
 */
public class CommandManager {
	
	//存放可以撤销的操作
	private Stack<Command> undoStack;
	//存放可以恢复的操作
	private Stack<Command> redoStack;
	public CommandManager() {
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}
	
	//实际的命令不直接执行，而是使用CommandManager来执行
	public void executeCommand(Command cmd){
		cmd.execute();
		this.undoStack.push(cmd);
	}
	
	//当该操作需要被撤销时，不是自己调用，而是调用CommandManager的undo方法
	//将保存的最后一次操作撤销，并将其加入到恢复栈中，以备恢复被撤销的操作
	public void undo(){
		if(!undoStack.isEmpty()){
			Command cmd = undoStack.pop();

			if(cmd.canUndo()){
//System.out.println(cmd+"撤销");
				cmd.undo();
				this.redoStack.push(cmd);
			}
		}
	}
	
	//恢复操作同理
	public void redo(){
		if(!redoStack.isEmpty()){
			Command cmd = redoStack.pop();
			if(cmd.canRedo()){
//System.out.println(cmd+"恢复");
				cmd.execute();
				this.undoStack.push(cmd);
			}
		}
	}
	
} 
