package command;

import javax.swing.JTextArea;

public class CutCommand implements Command {
	private JTextArea ta;
	private boolean isRedo = false;
	private int start;
	private int end;
	private String cuttedStr;
	
	public CutCommand(JTextArea ta,int start,int end,String cuttedStr) {
		this.ta = ta;
		this.start = start;
		this.end = end;
		this.cuttedStr = cuttedStr;
	}
	
	@Override
	public void execute() {
		if(isRedo){
			ta.replaceRange("", start, end);
		}
		isRedo = true;
	}

	@Override
	public void undo() {
		ta.insert(cuttedStr, start);
	}

	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public boolean canRedo() {
		return true;
	}

}
