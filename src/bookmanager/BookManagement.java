package bookmanager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bookmanager.book.BookList;
import bookmanager.member.MemberList;
import bookmanager.rent.RentInfo;
import bookmanager.rent.Rent_Return;
import bookmanager.rent.Rent_Return_List;

public class BookManagement extends JFrame implements ActionListener {
	private JButton btnMember;
	private JButton btnBook;
	private JButton btnRent;
	private JButton btnAll;
	private JButton btnExit;

	public BookManagement(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(1000, 700);
		setLayout(new BorderLayout());
		
		JPanel northpanel = new JPanel();
		btnMember = new JButton("회원 등록/삭제");
		btnMember.setBackground(Color.white);
		btnMember.addActionListener(this);
		northpanel.add(btnMember);
		
		btnBook = new JButton("도서 등록/삭제");
		btnBook.setBackground(Color.white);
		btnBook.addActionListener(this);
		northpanel.add(btnBook);
		
		btnRent = new JButton("도서 대여/반납");
		btnRent.setBackground(Color.white);
		btnRent.addActionListener(this);
		northpanel.add(btnRent);
		
		btnAll = new JButton("모든 대여 정보");
		btnAll.setBackground(Color.white);
		btnAll.addActionListener(this);
		northpanel.add(btnAll);
		
		btnExit = new JButton("종료");
		btnExit.setBackground(Color.white);
		btnExit.addActionListener(this);
		northpanel.add(btnExit);

		add(northpanel, BorderLayout.NORTH);
		
		JPanel imgpanel = new JPanel();
		ImageIcon img = new ImageIcon("img/Welcome.jpg");
		JLabel lbl = new JLabel(img);
		//lbl.setBounds(290, 260, 200, 30);
		imgpanel.add(lbl);
		add(imgpanel, BorderLayout.CENTER);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnMember) {
			MemberList list = new MemberList("회원 목록");
			dispose();
		}else if (obj == btnExit) {
			System.exit(0);
		}else if (obj == btnBook) {
			BookList booklist = new BookList("도서관리");
			dispose();
		}else if (obj ==btnRent) {
			Rent_Return_List rr = new Rent_Return_List("도서대여/반납");
			dispose();
		}else if (obj==btnAll) {
			RentInfo info = new RentInfo("대여정보");
			dispose();
		}
		
		
		
	}
	
	public static void main(String[] args) {
		BookManagement book = new BookManagement("도서 관리 프로그램");
	}

}
