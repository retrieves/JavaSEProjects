
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * ����ִ�дӿͻ����ύ�Ĵ��루�ַ�����������ֵ���ַ�����������һ���ַ������͵ķ���ֵ
 * ������־��ǽ���������Ҫʵ�ֵĽӿ�
 * 
 * @author Administrator
 *
 */
public interface ExecuteService extends Remote {
	
	public String execute(String code, String param) throws RemoteException, Exception;
}
