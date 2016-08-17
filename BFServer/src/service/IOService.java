
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 定义了与客户端进行数据交换的接口
 * write可以将一个文件保存到服务器的文件目录下
 * readFile可以从服务器文件目录下读取该用户指定的文件，并返回文件内容
 * readFileList可以返回一个用户的所有文件
 * @author Administrator
 *
 */
public interface IOService extends Remote{
	public boolean writeFile(String file, String userId, String fileName)throws RemoteException;
	
	public String readFile(String userId, String fileName)throws RemoteException;
	
	public String readFileList(String userId)throws RemoteException;
	public String execCode(String username, String fileName,String param) throws RemoteException, Exception;
}
