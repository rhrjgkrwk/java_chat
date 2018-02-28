package swing.chat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIChatClient extends JFrame implements ActionListener{
	JPanel cardPane, connectPane, chatPane;
	JLabel  msg;
	JButton btn_connect, btn_send, btn_exit;
	JTextField txt_server_ip, txt_name, txt_input;
	JTextArea txt_list;
	CardLayout card;
	
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
		addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			}
		);
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
		txt_list = new JTextArea();
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
			card.show(cardPane, "ä��â");
		if(ob ==btn_exit)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GUIChatClient();
	}
}








