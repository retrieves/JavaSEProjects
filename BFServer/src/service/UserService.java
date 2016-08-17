
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 定义了与客户端进行登录验证的方法，返回的是boolean，表明登录成功或失败
 * @author Administrator
 *
 */
public interface UserService extends Remote{
	public boolean login(String username, String password) throws RemoteException;

	public boolean logout(String username) throws RemoteException;
}
