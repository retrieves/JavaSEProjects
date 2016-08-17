
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * ��������ͻ��˽������ݽ����Ľӿ�
 * write���Խ�һ���ļ����浽���������ļ�Ŀ¼��
 * readFile���Դӷ������ļ�Ŀ¼�¶�ȡ���û�ָ�����ļ����������ļ�����
 * readFileList���Է���һ���û��������ļ�
 * @author Administrator
 *
 */
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;
	public String execCode(String username, String fileName,String param) throws RemoteException, Exception;
}
