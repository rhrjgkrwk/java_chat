package swing.chat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIChatServer extends JFrame implements ActionListener{
	TextArea txt_list;
	JButton btn_exit;
	
	public GUIChatServer()
	{
		super("Chatting Server");
		
		txt_list = new TextArea();
		btn_exit = new JButton("��������");
		
		add(txt_list, "Center");
		add(btn_exit,"South");
		setSize(250,250);
		setVisible(true);
		//�̺�Ʈó��-----------------------
		addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				}
			);
		btn_exit.addActionListener(this);
		//----------------------------------
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn_exit)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		new GUIChatServer();
	}
}
