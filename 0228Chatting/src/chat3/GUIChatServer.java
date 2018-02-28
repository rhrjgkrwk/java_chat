package chat3;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUIChatServer extends JFrame implements ActionListener{
	TextArea txt_list;
	JButton btn_exit;
	
	//�׽�Ʈ1---------------------------------------
	ServerSocket ss=null;
	//�׽�Ʈ1---------------------------------------
	//�׽�Ʈ2---------------------------------------
	Vector<ChatHandle> inwon=null;          //�ο����� ī��Ʈ�� ����
	//�׽�Ʈ2---------------------------------------
	
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
		inwon=new Vector<>();   //����)�ݵ�� serverStart()���� ���� ����ؾ���
		serverStart();               //       �׷��� ������ NullPointException �߻� 
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
				//�׽�Ʈ2----------------------------------------------------
				// ����ó���� �ϱ����� Ŭ���̾�Ʈ ��ü����(����� ����Ŭ����)
				ChatHandle ch=new ChatHandle(this, sock);
				inwon.add(ch);        //����������  Vector<ChatHandle> inwon;   ����
				ch.start();                // serverStart()�Լ� ������ inwon=new Vector<>(); �߰�
				//�׽�Ʈ2----------------------------------------------------
			}			
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	//�׽�Ʈ1-----------------------------------------------------------------
	//�׽�Ʈ2-----------------------------------------------------------------
	public void setMsg(String str)
	{
		txt_list.append(str);
	}
	//�׽�Ʈ2-----------------------------------------------------------------
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_exit)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GUIChatServer();
	}
}
//===================================================
class ChatHandle extends Thread{
	GUIChatServer server;
	Socket sock;
	PrintWriter pw=null;
	BufferedReader br=null;
	
	public ChatHandle(GUIChatServer server, Socket sock) {
		this.server=server;
		this.sock=sock;
		
		try{
			InputStream is=sock.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			
			OutputStream os=sock.getOutputStream();
			pw=new PrintWriter(new OutputStreamWriter(os));
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		//�׽�Ʈ2---------------------------------------------------
		String nickname=null;
		
		try{
			nickname=br.readLine();
			server.setMsg("[" + nickname + "]���� ���� �ϼ̽��ϴ�\n");  //setMsg()�޼��� �����Ұ�
			//�׽�Ʈ3-------------------------------------------------
			broadcast("[" + nickname + "]���� �����ϼ̽��ϴ�\n");
			//�׽�Ʈ3-------------------------------------------------
		}catch(IOException e){
			e.printStackTrace();
		}		
		//�׽�Ʈ2---------------------------------------------------
	}
	
	/* ��� �����ڿ��Ե� �޼����� ������*/
	public void broadcast(String string)
	{
		int s=server.inwon.size();   //������ ��ü��
		for(int i=0; i<s; i++)
		{
			ChatHandle ch=(ChatHandle)server.inwon.elementAt(i);
			ch.pw.println(string);
			ch.pw.flush();
		}
	}	
}













