package runner;

import rmi.RemoteHelper;
/**
 * ��������Server����
 * ����Ķ�
 * @author Administrator
 *
 */
public class ServerRunner {
	
	public ServerRunner() {
		new RemoteHelper();
	}
	
	public static void main(String[] args) {
		new ServerRunner();
	}
}
