package registerManagement;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler 	extends DefaultHandler {
	private List<User> users ;
	private User user;
	private String tag;
	
	public List<User> getUser() {
		return users;
	}

	public void setUser(List<User> User) {
		this.users = User;
	}
	
	//文档
	@Override
	public void startDocument() throws SAXException {
//		System.out.println("文档处理开始");
		users = new ArrayList<User>();
	}

	@Override
	public void endDocument() throws SAXException {
//		System.out.println("文档处理结束");
	}
	
	//一个元素的标签
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName != null){
			tag = qName;
		}
		if(tag.equals("login")){
			user = new User();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName!= null && qName.equals("login")){
			this.users.add(user);
		}
		tag = null;
	}

	//一个字段的标签
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch,start,length).trim();
//System.out.println(str);
//System.out.println(user);
		if(tag != null && tag.equals("username")){
			user.setUsername(str);
		}else if(tag != null && tag.equals("password")){
			user.setPassword(str);
		}
	}
}
