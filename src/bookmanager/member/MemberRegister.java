package bookmanager.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookmanager.DBbook;

public class MemberRegister extends JFrame implements ActionListener{
	private JButton btnOK,btnCancle;
	private JTextField tfName;
	private JTextField tfnum;
	private JTextField tfphone;
	private JTextField tfaddress;


	public MemberRegister(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(215, 190);
		setLayout(new BorderLayout());
		
		//회원 등록 타이틀
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.DARK_GRAY);
		JLabel lbltitle = new JLabel("회원등록");
		lbltitle.setForeground(Color.white);
		panelTitle.add(lbltitle);
		add(panelTitle, BorderLayout.NORTH);
		
		
		//정보 입력란
		JPanel panelCenter = new JPanel();
		panelCenter.setLayout(null);
		panelCenter.setPreferredSize(new Dimension(200, 600));
		//이름
		JLabel lblname = new JLabel("이름 : ");
		lblname.setBounds(0, 0, 50, 20);
		panelCenter.add(lblname);
		tfName = new JTextField();
		tfName.setBounds(100, 0, 100, 20);
		tfName.addActionListener(this);
		panelCenter.add(tfName);
		//주민번호
		JLabel lblnum = new JLabel("주민번호 : ");
		lblnum.setBounds(0, 20, 90, 20);
		panelCenter.add(lblnum);
		tfnum = new JTextField();
		tfnum.setBounds(100, 20, 100, 20);
		tfnum.addActionListener(this);
		panelCenter.add(tfnum);
		//연락처
		JLabel lblphone = new JLabel("연락처 : ");
		lblphone.setBounds(0, 40, 50, 20);
		panelCenter.add(lblphone);
		tfphone = new JTextField();
		tfphone.setBounds(100, 40, 100, 20);
		tfphone.addActionListener(this);
		panelCenter.add(tfphone);
		//주소
		JLabel lbladdress = new JLabel("주소 : ");
		lbladdress.setBounds(0, 60, 50, 20);
		panelCenter.add(lbladdress);
		tfaddress = new JTextField();
		tfaddress.setBounds(100, 60, 100, 20);
		tfaddress.addActionListener(this);
		panelCenter.add(tfaddress);
		
		
		add(panelCenter);
		
		// 확인/취소 버튼
		JPanel panelButton = new JPanel();
		btnOK = new JButton("확인");
		btnOK.addActionListener(this);
		panelButton.add(btnOK);
		btnCancle = new JButton("취소");
		btnCancle.addActionListener(this);
		panelButton.add(btnCancle);
		add(panelButton,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		MemberRegister mb= new MemberRegister("회원등록");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnCancle) {
			dispose();
		}else if (obj == btnOK) {
			String name = tfName.getText();
			String num = tfnum.getText();
			String phone = tfphone.getText();
			String addr = tfaddress.getText();
			
			DBbook db = new DBbook();
			db.memberRegister(name, num, phone, addr);
			
		}
		
	}
}
