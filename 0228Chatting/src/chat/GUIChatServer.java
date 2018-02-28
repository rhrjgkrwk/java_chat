package chat;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class GUIChatServer extends JFrame implements ActionListener{
	TextArea txt_list;
	JButton btn_exit;
	
	//�׽�Ʈ1---------------------------------------
	ServerSocket ss=null;
	//�׽�Ʈ1---------------------------------------
	
	public GUIChatServer()
	{
		super("Chatting Server");
		
		txt_list = new TextArea();
		btn_exit = new JButton("��������");
		
		add(txt_list, "Center");
		add(btn_exit,"South");
		setSize(250,250);
		setVisible(true);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//�̺�Ʈó��-----------------------
		btn_exit.addActionListener(this);
		//----------------------------------
		
		serverStart();
	}
	
	//�׽�Ʈ1(�غ�ܰ�:��������)-------------------------------------------
	public void serverStart(){
		final int PORT=7500;
		
		try{
			ss=new ServerSocket(PORT);
			while(true)
			{
				Socket sock=ss.accept();
				String str=sock.getInetAddress().getHostAddress();
				txt_list.append(str);
				
			}			
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	//�׽�Ʈ1-----------------------------------------------------------------
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_exit)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GUIChatServer();
	}
}
