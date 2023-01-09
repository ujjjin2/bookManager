package bookmanager.member;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import bookmanager.member.MemberList;
import bookmanager.DBbook;

public class MemberSearch extends JFrame implements ActionListener{
	private JButton btnSearch,btnCancle;
	private JTextField tf;
	private MemberList memberlist;

	public MemberSearch(String title,MemberList memberlist) {
		this.memberlist = memberlist;
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(240, 190);
		setLayout(new BorderLayout());
		
		//회원 검색 타이틀
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.DARK_GRAY);
		JLabel lbltitle = new JLabel("회원검색");
		lbltitle.setForeground(Color.white);
		panelTitle.add(lbltitle);
		add(panelTitle, BorderLayout.NORTH);
		
		
		//정보 입력란
		JPanel panelCenter = new JPanel();
		JLabel lbl = new JLabel("회원주민번호:");
		panelCenter.add(lbl);
		tf = new JTextField(10);
		panelCenter.add(tf);
		add(panelCenter);
		
		// 확인/취소 버튼
		JPanel panelButton = new JPanel();
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		panelButton.add(btnSearch);
		btnCancle = new JButton("취소");
		panelButton.add(btnCancle);
		btnCancle.addActionListener(this);
		add(panelButton,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		MemberSearch mb= new MemberSearch("회원등록",null);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (btnCancle==obj) {
			dispose();
		}else if (btnSearch == obj) {
			String num = tf.getText();
			memberlist.SearchList(memberlist,num);
			JOptionPane.showMessageDialog(null,"검색이 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
			//MemberList ml = new MemberList("회원목록");
		}
		
	}
}
