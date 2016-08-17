package runner;

import rmi.RemoteHelper;
/**
 * 用来启动Server的类
 * 不需改动
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
