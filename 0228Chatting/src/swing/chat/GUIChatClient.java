package swing.chat;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIChatClient extends JFrame implements ActionListener, Runnable{
	JPanel cardPane, connectPane, chatPane;
	JLabel  msg;
	JButton btn_connect, btn_send, btn_exit;
	JTextField txt_server_ip, txt_name, txt_input;
	TextArea txt_list;
	CardLayout card;
	
	//�׽�Ʈ1-------------------------------------------------
	String ip_txt;                             // IP�� �����Һ���
	Socket sock;
	final int PORT=7500;
	PrintWriter pw=null;                  //�۽Ž�Ʈ��
	BufferedReader br=null;             //���Ž�Ʈ��
	//�׽�Ʈ1-------------------------------------------------
	//�׽�Ʈ2-------------------------------------------------
	OutputStream os=null;
	//�׽�Ʈ2-------------------------------------------------
	
	
	public GUIChatClient()
	{
		super("Chatting Client(ver 1.0)");
		
		ConnectPane();
		ChatPane();
		
		//card-----------------------------
		cardPane = new JPanel();
		card = new CardLayout();
		cardPane.setLayout(card);
		cardPane.add(connectPane,"����â");
		cardPane.add(chatPane,"ä��â");
		card.show(cardPane, "����â");
		//----------------------------------
		add(cardPane);
		setSize(400,300);
		setVisible(true);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//�̺�Ʈó��-----------------------
		btn_connect.addActionListener(this);
		btn_exit.addActionListener(this);
		//----------------------------------
	}
	public void ConnectPane()
	{
		connectPane = new JPanel();
		JPanel pn=new JPanel();
		JPanel pn1 = new JPanel();
		JPanel pn2 = new JPanel();
		
		msg = new JLabel("IP�� ��ȭ���� �Է��Ͻÿ�");
		msg.setFont(new Font("����ü", Font.BOLD, 15));
		msg.setForeground(Color.magenta);
		
		JLabel lb1 = new JLabel("���� I P : ");
		txt_server_ip = new JTextField("localhost", 15);
		pn1.add(lb1);    pn1.add(txt_server_ip);
		
		JLabel lb2 = new JLabel("�� ȭ �� : ");
		txt_name = new JTextField("ĵ��",15);		
		pn2.add(lb2);    pn2.add(txt_name);
		
		pn.add(pn1);     pn.add(pn2);    pn.add(msg);
		
		btn_connect = new JButton("Connection");
		
		connectPane.setBorder(BorderFactory.createTitledBorder("����ä��ȭ��"));
		connectPane.setLayout(new BorderLayout());
		connectPane.add(pn,"Center");
		connectPane.add(btn_connect,"South");
	}
	public void ChatPane()
	{
		chatPane = new JPanel();
		JPanel  pn = new JPanel();
		txt_list = new TextArea();
		txt_input = new JTextField("",20);
		btn_send = new JButton("����");
		btn_exit = new JButton("����");

		pn.setBorder(BorderFactory.createTitledBorder("�ٴ�ȭ�ϱ��"));
		chatPane.setBorder(BorderFactory.createTitledBorder("��ä�ó����"));
		pn.add(txt_input);   pn.add(btn_send);  pn.add(btn_exit);
		
		chatPane.setLayout(new BorderLayout());
		chatPane.add(txt_list, "Center");
		chatPane.add(pn, "South");
	}

	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == btn_connect)
		{
			card.show(cardPane, "ä��â");
			//�׽�Ʈ1-----------------------------------------------
			ip_txt=txt_server_ip.getText();
			Thread th=new Thread(this);    // implements Runnable  �߰�
			th.start();
			//�׽�Ʈ1-----------------------------------------------
		}
		if(ob ==btn_exit)
			System.exit(0);
	}
	
	@Override
	public void run() {
		//�׽�Ʈ1-----------------------------------------------
		try{
			sock=new Socket(ip_txt, PORT);
			
			//�׽�Ʈ2(�۽�)---------------------------------------
			String nickname=txt_name.getText();
			os=sock.getOutputStream();
			pw=new PrintWriter(new OutputStreamWriter(os));
			pw.println(nickname);                 //����)pw.print();   -----X
			pw.flush();			
			//�׽�Ʈ2(�۽�)---------------------------------------
		}catch(IOException e){
			e.printStackTrace();
		}		
		//�׽�Ʈ1-----------------------------------------------
	}
	
	public static void main(String[] args) {
		new GUIChatClient();
	}
	
}








