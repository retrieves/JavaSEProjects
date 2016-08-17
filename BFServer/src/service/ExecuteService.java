
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 用于执行从客户端提交的代码（字符串）和输入值（字符串），返回一个字符串类型的返回值
 * 这个部分就是解释器所需要实现的接口
 * 
 * @author Administrator
 *
 */
public interface ExecuteService extends Remote {
	
	public String execute(String code, String param) throws RemoteException, Exception;
}
