package command;
/**
 * ÿ����Ҫ�����ͻָ��Ĳ���������ʵ��Command�ӿ�
 * ��ÿ��ִ��ĳ�ֲ���ʱ�����縴�ơ�ճ����������һ���¼��������Զ�������һ����Ӧ��Command���󣬲����뵽CommandManager��
 * @author Administrator
 *
 */
public interface Command {
	//execute��ִ�в����������������ָ��������˵�ִ�еĲ���
	void execute();
	//����execute����
	void undo();
	boolean canUndo();
	boolean canRedo();
}
