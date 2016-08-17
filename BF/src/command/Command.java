package command;
/**
 * 每种需要撤销和恢复的操作都必须实现Command接口
 * 当每次执行某种操作时（比如复制、粘贴），增加一个事件监听，自动地生成一个对应的Command对象，并传入到CommandManager中
 * @author Administrator
 *
 */
public interface Command {
	//execute是执行操作，并可以用来恢复被撤销了的执行的操作
	void execute();
	//撤销execute操作
	void undo();
	boolean canUndo();
	boolean canRedo();
}
