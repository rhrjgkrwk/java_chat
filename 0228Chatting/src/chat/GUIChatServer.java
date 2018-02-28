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
	
	//테스트1---------------------------------------
	ServerSocket ss=null;
	//테스트1---------------------------------------
	
	public GUIChatServer()
	{
		super("Chatting Server");
		
		txt_list = new TextArea();
		btn_exit = new JButton("서버종료");
		
		add(txt_list, "Center");
		add(btn_exit,"South");
		setSize(250,250);
		setVisible(true);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//이벤트처리-----------------------
		btn_exit.addActionListener(this);
		//----------------------------------
		
		serverStart();
	}
	
	//테스트1(준비단계:서버구동)-------------------------------------------
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
	//테스트1-----------------------------------------------------------------
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_exit)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GUIChatServer();
	}
}
