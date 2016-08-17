package command;

import javax.swing.JTextArea;

/**
 * 针对于输入的操作
 * @author Administrator
 *
 */
public class TypeCommand implements Command{
	private JTextArea ta;
	private boolean isRedo = false;
	private String prev ;
	private int lastEditLength ;
	
	public TypeCommand(JTextArea ta,int lastEditLength) {
		this.ta = ta;
		this.prev = ta.getText();
		this.lastEditLength = lastEditLength;
	}
	
	@Override
	public void execute() {
		if(isRedo)
			ta.setText(prev);
		isRedo = true;
	}

	@Override
	public void undo() {
		ta.setText(prev.substring(0, prev.length()-1));
	}
	
	public boolean canUndo(){
		return (prev.length()- lastEditLength) > 0;
	}
	
	public boolean canRedo(){
		return !ta.getText().equals(prev);
	}
	
}
