package br.eti.matheus.simplepresenter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimplePresenterClient {
	
	static final Integer PORT = 8383;
	
	private String simplePresenterServerIP;
	private Socket simplePresenterSocket;
	private DataOutputStream dataOutputStream;
	private DataInputStream dataInputStream;
	private String content;
	private Boolean connected;
	
	public Boolean getConnected() {
		return connected;
	}

	public String getContent() {
		return content;
	}

	
	public SimplePresenterClient(String simplePresenterServerIP){
		this.simplePresenterServerIP = simplePresenterServerIP;
		this.simplePresenterSocket = null;
		this.dataOutputStream = null;
		this.dataInputStream = null;
		this.connected = false;
	}
	
	public void connect(){
		this.send(PORT.toString());
		this.connected = Boolean.valueOf(this.content);
	}
	
	public void send(String command){
		try{
			this.content = "";
			simplePresenterSocket = new Socket(this.simplePresenterServerIP, PORT);
			dataOutputStream = new DataOutputStream(simplePresenterSocket.getOutputStream());
			dataInputStream = new DataInputStream(simplePresenterSocket.getInputStream());	
			this.dataOutputStream.writeUTF(command);
			this.content = this.dataInputStream.readUTF();
		} 
		catch (UnknownHostException e) { e.printStackTrace();} 
		catch (IOException e) {	e.printStackTrace();}	
		finally{
			if (simplePresenterSocket != null){
				try { simplePresenterSocket.close();} catch (IOException e) { e.printStackTrace();}
			}
			
			if (dataOutputStream != null){
				try { dataOutputStream.close();} catch (IOException e) { e.printStackTrace();}
			}
			
			if (dataInputStream != null){
				try {dataInputStream.close();} catch (IOException e) { e.printStackTrace();	}
			}
		}
	}

}
