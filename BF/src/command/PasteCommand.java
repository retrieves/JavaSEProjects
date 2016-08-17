package command;

import javax.swing.JTextArea;

public class PasteCommand implements Command {
	private JTextArea ta;
	private boolean isRedo = false;
	private String content;
	private int pos;
	
	public PasteCommand(JTextArea ta,String content,int pos) {
		this.ta = ta;
		this.content = content;
		this.pos = pos;
	}
	
	@Override
	public void execute() {
		if(isRedo)
			ta.insert(content, pos);
		isRedo = true;
	}

	@Override
	public void undo() {
		ta.replaceRange("", pos, pos+content.length());
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
