
package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 定义了与服务器进行登录验证的接口
 * @author Administrator
 *
 */
public interface UserService extends Remote{
	public boolean login(String username, String password) throws RemoteException;

	public boolean logout(String username) throws RemoteException;
}
