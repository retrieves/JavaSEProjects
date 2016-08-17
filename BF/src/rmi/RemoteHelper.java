package rmi;

import java.rmi.Remote;

import service.IOService;
import service.UserService;
/**
 * Java RMI ָ����Զ�̷������� (Remote Method Invocation)��
 * ����һ�ֻ��ƣ��ܹ�����ĳ�� Java ������ϵĶ��������һ�� Java ������еĶ����ϵķ�����
 * �����ô˷������õ��κζ������ʵ�ָ�Զ�̽ӿڡ�
 * RemoteHelper��ʵ��Զ�̷������õĹ�����
 * 
 * 
 * ����ģʽ
 * @author ������
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
