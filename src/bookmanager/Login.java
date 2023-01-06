package bookmanager;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	private JButton btnExit;
	private JButton btnlogin;
	private JTextField tfID;
	private JPasswordField tfPW;

	public Login(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(900, 430);
		setLayout(new BorderLayout());
		
		//상단 이미지
		JPanel image = new JPanel();
		ImageIcon img = new ImageIcon("img/Intro.jpg");
		JLabel lbl = new JLabel(img);
		//lbl.setBounds(290, 260, 200, 30);
		image.add(lbl);
		add(image, BorderLayout.NORTH);
		
		//하단 로그인
		JPanel login = new JPanel();
		JLabel lblID = new JLabel("ID:");
		login.add(lblID);
		tfID = new JTextField(10);
		tfID.addActionListener(this);
		login.add(tfID);
		
		JLabel lblPW = new JLabel("PW:");
		login.add(lblPW);
		tfPW = new JPasswordField(10);
		tfPW.addActionListener(this);
		login.add(tfPW);
		
		btnlogin =new JButton("로그인");
		btnlogin.addActionListener(this);
		login.add(btnlogin);
		btnExit =new JButton("종료");
		btnExit.addActionListener(this);
		login.add(btnExit);
		
		
		add(login, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Login book = new Login("관리자 로그인");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (btnExit == obj) {
			System.exit(0);
		}else if (obj == btnlogin) {
			String ID = tfID.getText();
			String PW = tfPW.getText();
			
			DBbook db = new DBbook();
			db.findadmin(ID, PW);
			dispose();
			
			
		}
		
	}
}
