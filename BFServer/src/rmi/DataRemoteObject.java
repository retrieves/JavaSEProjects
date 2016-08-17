package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.IOService;
import service.UserService;
import serviceImpl.IOServiceImpl;
import serviceImpl.UserServiceImpl;
/**
 * ʵ������ͻ���ͨ�ŵ������ӿ�IOService��UserService�����Ե�����Щ��������ͻ��˽����ļ���������¼��֤
 * ��������ֱ��ʹ�ã�����Ķ�
 * @author Administrator
 *
 */
public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService{
	
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

	@Override
	public String execCode(String username, String fileName, String param) throws Exception {
		// TODO Auto-generated method stub
		return iOService.execCode(username, fileName, param);
	}

}
