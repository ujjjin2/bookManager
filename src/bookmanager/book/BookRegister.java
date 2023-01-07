package bookmanager.book;

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

public class BookRegister extends JFrame implements ActionListener{
	private JButton btnOK,btnCancle;
	private JTextField tfcode;
	private JTextField tftitle;
	private JTextField tfauthor;
	private JTextField tfpublisher;
	private JTextField tfprice;
	private JTextField tfRent;


	public BookRegister(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(215, 230);
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
		//도서번호
		JLabel lblbookcode = new JLabel("도서번호 : ");
		lblbookcode.setBounds(0, 0, 100, 20);
		panelCenter.add(lblbookcode);
		tfcode = new JTextField();
		tfcode.setBounds(100, 0, 100, 20);
		tfcode.addActionListener(this);
		panelCenter.add(tfcode);
		//제목
		JLabel lbltt = new JLabel("제목 : ");
		lbltt.setBounds(0, 20, 100, 20);
		panelCenter.add(lbltt);
		tftitle = new JTextField();
		tftitle.setBounds(100, 20, 100, 20);
		tftitle.addActionListener(this);
		panelCenter.add(tftitle);
		//저자
		JLabel lblauthor = new JLabel("저자 : ");
		lblauthor.setBounds(0, 40, 100, 20);
		panelCenter.add(lblauthor);
		tfauthor = new JTextField();
		tfauthor.setBounds(100, 40, 100, 20);
		tfauthor.addActionListener(this);
		panelCenter.add(tfauthor);
		//출판사
		JLabel lblpublisher = new JLabel("출판사 : ");
		lblpublisher.setBounds(0, 60, 100, 20);
		panelCenter.add(lblpublisher);
		tfpublisher = new JTextField();
		tfpublisher.setBounds(100, 60, 100, 20);
		tfpublisher.addActionListener(this);
		panelCenter.add(tfpublisher);
		//가격
		JLabel lblprice = new JLabel("가격 : ");
		lblprice.setBounds(0, 80, 100, 20);
		panelCenter.add(lblprice);
		tfprice = new JTextField();
		tfprice.setBounds(100, 80, 100, 20);
		tfprice.addActionListener(this);
		panelCenter.add(tfprice);
		//대여정보
		JLabel lblRent = new JLabel("대여정보 : ");
		lblRent.setBounds(0, 100, 100, 20);
		panelCenter.add(lblRent);
		tfRent = new JTextField();
		tfRent.setBounds(100, 100, 100, 20);
		tfRent.addActionListener(this);
		panelCenter.add(tfRent);
		
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
		BookRegister mb= new BookRegister("도서등록");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnCancle) {
			dispose();
		}else if (obj == btnOK) {
			String code = tfcode.getText();
			String title = tftitle.getText();
			String author = tfauthor.getText();
			String publisher = tfpublisher.getText();
			String price = tfprice.getText();
			String rent = tfRent.getText();
			
			DBbook db = new DBbook();
			db.BookRegister(code, title, author, publisher, price, rent);
			
			
		}
		
	}
}
