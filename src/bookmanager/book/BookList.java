package bookmanager.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import bookmanager.BookManagement;
import bookmanager.DBbook;
import bookmanager.member.MemberList;


public class BookList extends JFrame implements ActionListener{
	private JButton btnrefresh;
	private JButton btnRegister;
	private JButton btnCheck;
	private JButton btnChange;
	private JButton btnDelete;
	private String[] title = {"도서번호", "제목", "저자","출판사","가격","대출여부"};
	private String[][] datas = new String[0][6];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JButton btnBack;
	private JScrollPane ScrollPane;

	public BookList(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(480, 525);
		setLayout(new BorderLayout());
		
		JPanel panelButton = new JPanel();
		//새로고침
		btnrefresh = new JButton("새로고침");
		btnrefresh.addActionListener(this);
		panelButton.add(btnrefresh);
		//등록
		btnRegister = new JButton("등록");
		btnRegister.addActionListener(this);
		panelButton.add(btnRegister);
		//조회
		btnCheck = new JButton("조회");
		btnCheck.addActionListener(this);
		panelButton.add(btnCheck);
		//수정
		btnChange = new JButton("수정");
		btnChange.addActionListener(this);
		panelButton.add(btnChange);
		//삭제
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		panelButton.add(btnDelete);
		//돌아가기
		btnBack = new JButton("돌아가기");
		btnBack.addActionListener(this);
		panelButton.add(btnBack);
		add(panelButton, BorderLayout.NORTH);
		
		table();
		DBbook db = new DBbook();
		db.showBook(this);
		
		setVisible(true);
	}
	public DefaultTableModel getModel() {
		return model;
	}
	
	private void table() {
		//회원 목록
		JPanel listPanel = new JPanel();
		listPanel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"도서목록"));
		//listPanel.setBackground(Color.red);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		ScrollPane.setPreferredSize(new Dimension(450,430));
		listPanel.add(ScrollPane);
		add(listPanel, BorderLayout.CENTER);
		
	}

//	public static void main(String[] args) {
//		BookList book = new BookList("회원목록");
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnRegister) {
			BookRegister BR = new BookRegister("도서등록");
		}else if (obj ==btnCheck) {
			BookSearch BS = new BookSearch("도서검색",this);
		}else if (obj ==btnBack) {
			dispose();
			BookManagement bk = new BookManagement("도서관리프로그램");
		}else if (obj == btnChange) {
			BookModify BM = new BookModify("도서정보 수정");
		}else if (obj == btnDelete) {
			BookDelete BD = new BookDelete("도서삭제");
		}else if (obj == btnrefresh) {
			DBbook db = new DBbook();
			db.showBook(this);
			System.out.println("도서 리프레쉬 성공");
		}
		
	}
	
	public void SearchBookList(BookList bookList, String title) {
		DBbook db = new DBbook();
		db.bookSearch(this, title);
	}
}
