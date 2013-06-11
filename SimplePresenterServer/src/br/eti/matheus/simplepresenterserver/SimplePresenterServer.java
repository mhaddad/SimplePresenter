package br.eti.matheus.simplepresenterserver;

import java.awt.Font;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SimplePresenterServer extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimplePresenterServer()
	{
		super("Simple Presenter Server 1.0");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel lblTitle = new JLabel("Simple Presenter Server", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial",Font.BOLD,20));
		lblTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblIP = new JLabel("Server address: ", SwingConstants.CENTER);
		lblIP.setFont(new Font("Arial",Font.PLAIN,12));
		lblIP.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel lblCommand = new JLabel("", SwingConstants.CENTER);
		lblCommand.setFont(new Font("Arial",Font.PLAIN,12));
		lblCommand.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(lblTitle);	
		panel.add(lblIP);
		panel.add(lblCommand);
		
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(300,200);
		setVisible(true);
		
		Socket socket = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        ServerSocket serverSocket = null;
    try
    {  
        serverSocket = new ServerSocket(8383);
        lblIP.setText("Server address: " + serverSocket.getInetAddress() + ":8383");
    }
    catch (Exception e) 
    {               
        e.printStackTrace();
    }
    while(true)
    {
        try
        {
            socket = serverSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream()); 
            
            System.out.println("ip: " + socket.getInetAddress());        
            int command = Integer.parseInt(dataInputStream.readUTF());
            System.out.println("message Received: " + String.valueOf(command));
            
            Robot robot = new Robot();
           
            switch(command){
            case 0:
            	robot.keyPress(KeyEvent.VK_LEFT);
            	robot.keyRelease(KeyEvent.VK_LEFT);
            	dataOutputStream.writeUTF("Prev");
            	lblCommand.setText("Prev");
            	break;
            case 1:
            	robot.keyPress(KeyEvent.VK_RIGHT);
            	robot.keyRelease(KeyEvent.VK_RIGHT);
            	dataOutputStream.writeUTF("Next");
            	lblCommand.setText("Next");

            	break;
            case 8383:
            	dataOutputStream.writeUTF("true");
            	lblCommand.setText("Connected");
            	break;
            default:
            	dataOutputStream.writeUTF("false");
            }
            
            
        } 
        catch (Exception e) 
        {                   
            e.printStackTrace();
        }               
        finally
        {
            if( socket!= null)
            {
                try 
                {
                    socket.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            if( dataInputStream!= null)
            {
                try
                {
                    dataInputStream.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }                   
            if( dataOutputStream!= null)
            {
                try 
                {
                    dataOutputStream.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		new SimplePresenterServer();

	}

}
