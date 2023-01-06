package bookmanager.member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MemberModify extends JFrame implements ActionListener{
	
	private JTextField tfName;
	private JTextField tfnum;
	private JTextField tfphone;
	private JTextField tfaddress;
	private JButton btnBack;
	private JButton btnModify;

	public MemberModify(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(280, 190);
		setLayout(new BorderLayout());
		
		//회원 주민번호 입력하는 부분
		JPanel panel1 = new JPanel();
		JLabel lblNum = new JLabel("회원 주민번호 : ");
		panel1.add(lblNum);
		JTextField tfNum = new JTextField(10);
		panel1.add(tfNum);
		JButton btnSearch = new JButton("검색");
		panel1.add(btnSearch);
		add(panel1, BorderLayout.NORTH);
		
		//정보 입력 창
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new Dimension(200, 600));
		//이름
		JLabel lblname = new JLabel("이름 : ");
		lblname.setBounds(0, 0, 50, 20);
		panel2.add(lblname);
		tfName = new JTextField();
		tfName.setBounds(130, 0, 130, 20);
		tfName.addActionListener(this);
		panel2.add(tfName);
		//주민번호
		JLabel lblnum = new JLabel("주민번호 : ");
		lblnum.setBounds(0, 20, 90, 20);
		panel2.add(lblnum);
		tfnum = new JTextField();
		tfnum.setBounds(130, 20, 130, 20);
		tfnum.addActionListener(this);
		panel2.add(tfnum);
		//연락처
		JLabel lblphone = new JLabel("연락처 : ");
		lblphone.setBounds(0, 40, 50, 20);
		panel2.add(lblphone);
		tfphone = new JTextField();
		tfphone.setBounds(130, 40, 130, 20);
		tfphone.addActionListener(this);
		panel2.add(tfphone);
		//주소
		JLabel lbladdress = new JLabel("주소 : ");
		lbladdress.setBounds(0, 60, 50, 20);
		panel2.add(lbladdress);
		tfaddress = new JTextField();
		tfaddress.setBounds(130, 60, 130, 20);
		tfaddress.addActionListener(this);
		panel2.add(tfaddress);
		add(panel2, BorderLayout.CENTER);
		
		//버튼 부분
		JPanel panel3 = new JPanel();
		btnModify= new JButton("수정");
		btnModify.addActionListener(this);
		panel3.add(btnModify);
		btnBack= new JButton("돌아가기");
		btnBack.addActionListener(this);
		panel3.add(btnBack);
		add(panel3, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		MemberModify MM = new MemberModify("회원 정보 수정");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnBack) {
			dispose();
		}else if (obj == btnModify) {
			
		}
		
	}
}
