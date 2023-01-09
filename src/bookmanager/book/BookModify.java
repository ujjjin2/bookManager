package bookmanager.book;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookmanager.DBbook;

public class BookModify extends JFrame implements ActionListener{
	
	private JTextField tfcode;
	private JTextField tftt;
	private JTextField tfauthor;
	private JTextField tfpublisher;
	private JButton btnBack;
	private JButton btnModify;
	private JTextField tfprice;
	private JTextField tfRent;
	private JButton btnSearch;
	private JTextField tfNum;

	public BookModify(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(275, 240);
		setLayout(new BorderLayout());
		
		//회원 주민번호 입력하는 부분
		JPanel panel1 = new JPanel();
		JLabel lblNum = new JLabel("도서번호 : ");
		panel1.add(lblNum);
		tfNum = new JTextField(13);
		tfNum.addActionListener(this);
		panel1.add(tfNum);
		btnSearch = new JButton("검색");
		btnSearch.addActionListener(this);
		panel1.add(btnSearch);
		add(panel1, BorderLayout.NORTH);
		
		//정보 입력 창
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new Dimension(200, 600));
		//도서번호
		JLabel lblcode = new JLabel("도서번호 : ");
		lblcode.setBounds(0, 0,100, 20);
		panel2.add(lblcode);
		tfcode = new JTextField();
		tfcode.setBounds(130, 0, 130, 20);
		tfcode.addActionListener(this);
		panel2.add(tfcode);
		//제목
		JLabel lbltt = new JLabel("제목 : ");
		lbltt.setBounds(0, 20, 100, 20);
		panel2.add(lbltt);
		tftt = new JTextField();
		tftt.setBounds(130, 20, 130, 20);
		tftt.addActionListener(this);
		panel2.add(tftt);
		//저자
		JLabel lblauthor = new JLabel("저자 : ");
		lblauthor.setBounds(0, 40, 100, 20);
		panel2.add(lblauthor);
		tfauthor = new JTextField();
		tfauthor.setBounds(130, 40, 130, 20);
		tfauthor.addActionListener(this);
		panel2.add(tfauthor);
		//출판사
		JLabel lblpublisher = new JLabel("출판사 : ");
		lblpublisher.setBounds(0, 60, 100, 20);
		panel2.add(lblpublisher);
		tfpublisher = new JTextField();
		tfpublisher.setBounds(130, 60, 130, 20);
		tfpublisher.addActionListener(this);
		panel2.add(tfpublisher);
		//가격
		JLabel lblprice = new JLabel("가격 : ");
		lblprice.setBounds(0, 80, 100, 20);
		panel2.add(lblprice);
		tfprice = new JTextField();
		tfprice.setBounds(130, 80, 130, 20);
		tfprice.addActionListener(this);
		panel2.add(tfprice);
		//대여여부
		JLabel lblRent = new JLabel("대여여부 : ");
		lblRent.setBounds(0, 100, 100, 20);
		panel2.add(lblRent);
		tfRent = new JTextField();
		tfRent.setBounds(130, 100, 130, 20);
		tfRent.addActionListener(this);
		panel2.add(tfRent);
		
		
		
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
		BookModify MM = new BookModify("회원 정보 수정");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnBack) {
			dispose();
		}else if (obj == btnModify) {
			String CODE = tfNum.getText();
			String title = tftt.getText();
			String author = tfauthor.getText();
			String publisher = tfpublisher.getText();
			String price = tfprice.getText();
			String rent = tfRent.getText();
			DBbook db = new DBbook();
			db.BookUpdate(CODE, title, author, publisher, price, rent);
			
		}else if (obj == btnSearch) {
			String code = tfNum.getText();
			DBbook db = new DBbook();
			db.pullBookInfo(code, tfcode, tftt, tfauthor, tfpublisher, tfprice, tfRent);
			System.out.println("도서 수정-검색누름");
		}
		
	}
}
