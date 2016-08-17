
package service;
/**
 * 定义了与服务器进行IO操作的接口
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;
	public String execCode(String username, String fileName,String param);
}
