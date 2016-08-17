package rmi;

import java.rmi.Remote;

import service.IOService;
import service.UserService;
/**
 * Java RMI 指的是远程方法调用 (Remote Method Invocation)。
 * 它是一种机制，能够让在某个 Java 虚拟机上的对象调用另一个 Java 虚拟机中的对象上的方法。
 * 可以用此方法调用的任何对象必须实现该远程接口。
 * RemoteHelper是实现远程方法调用的工具类
 * 
 * 
 * 单例模式
 * @author 宋欣建
 *
 */

public class RemoteHelper {
	private Remote remote;
	private static RemoteHelper remoteHelper = new RemoteHelper();
	
	public static RemoteHelper getInstance(){
		return remoteHelper;
	}
	
	private RemoteHelper() {
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public IOService getIOService(){
		return (IOService)remote;
	}
	
	public UserService getUserService(){
		return (UserService)remote;
	}
}
