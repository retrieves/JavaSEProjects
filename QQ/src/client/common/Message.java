package client.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.view.ChatFrame;

public class Message {
	private Socket socket ;
	private boolean connected;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ChatFrame chatFrame;
	

	private String username;
	private String password;
	
	
	public Message(String username,String password) {
		this.username = username;
		this.password = password;
		try {
			socket = new Socket("127.0.0.1", 8888);
			connected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validate() {
		boolean isPass = false;
		try {
			dos.writeUTF(this.username);
			dos.writeUTF(this.password);
			isPass = dis.readBoolean();
			if(isPass) {
				new Thread(new Receiver()).start();
			}else {
				socket.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isPass;
	}
	
	
	public void send(String msg) {
		try {
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Receiver implements Runnable{
		
		@Override
		public void run() {
			String line = "";
			while(connected){
				try {
					line = dis.readUTF();
					chatFrame.setText(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}				
	}
	
	public ChatFrame getChatFrame() {
		return chatFrame;
	}

	public void setChatFrame(ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
	}
	
}	
