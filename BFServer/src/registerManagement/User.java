package registerManagement;

@SuppressWarnings("all")
public class User {
	private String username;
	private String password;
	private String docPath;
	
	public User() {
	}

	public User(String username, String password, String docPath) {
		super();
		this.username = username;
		this.password = password;
		this.docPath = docPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	
}
