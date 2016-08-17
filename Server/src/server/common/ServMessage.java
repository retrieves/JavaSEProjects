package server.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServMessage {
	
	private ServerSocket server;
	private List<Client> clients;
	private boolean isRunning;
	
	public ServMessage() {
		
		try {
			server = new ServerSocket(8888);
			isRunning = true;
			connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private  void connect(){
		try {
			while(isRunning) {
				Socket s = server.accept();
				Client client = new Client(s);
				new Thread(client).start();
				clients.add(client);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class Client implements Runnable {
		Socket socket;
		boolean connected = false;
		DataInputStream dis;
		DataOutputStream dos;
		
		public Client(Socket socket) {
			try {
				this.socket = socket;
				connected = true;
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String line = "";
			while(connected){
				try {
					line = dis.readUTF();
					transmit(line);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		private void transmit(String line) {
			for (Client client : clients) {
				if(client.connected) {
					client.send(line);
				}
			}
		}

		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

