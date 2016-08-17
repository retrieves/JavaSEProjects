package serviceImpl;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import registerManagement.User;
import registerManagement.UserHandler;
import service.UserService;
/**
 * 实现登录验证操作
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService{
	
	@Override
	public boolean login(String username, String password) throws RemoteException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			UserHandler handler = new UserHandler();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.xml"),handler );
			List<User> users = handler.getUser();
			for (User user : users) {
				if(user.getUsername().equals(username)&& user.getPassword().equals(password)){
					File file = new File("Codes",username);
					if(!file.exists()){
						file.mkdir();
//						System.out.println(file.getAbsolutePath());
					}
					return true;
				}
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			UserHandler handler = new UserHandler();
			parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("admin.xml"),handler );
			List<User> users = handler.getUser();
			for (User user : users) {
				if(user.getUsername().equals(username)){
					return true;
				}
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
/*	public static void main(String[] args) throws RemoteException {
		UserServiceImpl user = new UserServiceImpl();
		System.out.println(user.login("software", "engineering"));
	}*/
}
