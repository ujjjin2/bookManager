package bookmanager.rent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bookmanager.BookManagement;
import bookmanager.DBbook;

public class Rent_Return_List extends JFrame implements ActionListener {
	private String[] title = {"대여번호", "제목", "저자","출판사","가격","대출여부"};
	private String[][] datas = new String[0][6];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	private JTextField tfbook;
	private JButton btnsearch;
	private JTextField tfcode,tftitle,tfauthor,tfpublisher,tfprice,tfRent;
	private JButton btnRR, btnReset, btnrefresh, btnback;
	
	public Rent_Return_List(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(475, 655);
		setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		
		JPanel panel1_1 = new JPanel();
		JLabel lbl = new JLabel("도서명");
		panel1_1.add(lbl);
		tfbook = new JTextField(10);
		tfbook.addActionListener(this);
		panel1_1.add(tfbook);
		btnsearch = new JButton("검색");
		btnsearch.addActionListener(this);
		panel1_1.add(btnsearch);
		
		JPanel panel1_2 = new JPanel();
		panel1_2.setLayout(null);
		panel1_2.setPreferredSize(new Dimension(140, 150));
		JLabel lblcode = new JLabel("도서번호");
		lblcode.setBounds(50, 0, 100, 20);
		panel1_2.add(lblcode);
		
		JLabel lbltitle = new JLabel("제목");
		lbltitle.setBounds(250, 0, 100, 20);
		panel1_2.add(lbltitle);
		
		JLabel lblauthor = new JLabel("저자");
		lblauthor.setBounds(50, 30, 100, 20);
		panel1_2.add(lblauthor);
		
		JLabel lblpublisher = new JLabel("출판사");
		lblpublisher.setBounds(250, 30, 100, 20);
		panel1_2.add(lblpublisher);
		
		JLabel lblprice = new JLabel("가격");
		lblprice.setBounds(50, 60, 100, 20);
		panel1_2.add(lblprice);
		JLabel lblRent = new JLabel("대출여부");
		lblRent.setBounds(250, 60, 100, 20);
		panel1_2.add(lblRent);
		
		tfcode = new JTextField(15);
		tfcode.setBounds(120, 0, 100, 20);
		tfcode.addActionListener(this);
		panel1_2.add(tfcode);
		
		tftitle = new JTextField(15);
		tftitle.setBounds(320, 0, 100, 20);
		tftitle.addActionListener(this);
		panel1_2.add(tftitle);
		
		tfauthor = new JTextField(15);
		tfauthor.setBounds(120, 30, 100, 20);
		tfauthor.addActionListener(this);
		panel1_2.add(tfauthor);
		
		tfpublisher = new JTextField(15);
		tfpublisher.setBounds(320, 30, 100, 20);
		tfpublisher.addActionListener(this);
		panel1_2.add(tfpublisher);
		
		tfprice = new JTextField(15);
		tfprice.setBounds(120, 60, 100, 20);
		tfprice.addActionListener(this);
		panel1_2.add(tfprice);
		
		tfRent = new JTextField(15);
		tfRent.setBounds(320, 60, 100, 20);
		tfRent.addActionListener(this);
		panel1_2.add(tfRent);
		
		
		btnRR = new JButton("대여/반납");
		btnRR.setBounds(5, 95, 90, 25);
		btnRR.addActionListener(this);
		panel1_2.add(btnRR);
		
		btnReset = new JButton("초기화");
		btnReset.setBounds(100, 95, 80, 25);
		btnReset.addActionListener(this);
		panel1_2.add(btnReset);
		
		btnrefresh = new JButton("새로고침");
		btnrefresh.setBounds(185, 95, 90, 25);
		btnrefresh.addActionListener(this);
		panel1_2.add(btnrefresh);
		
		btnback = new JButton("돌아가기");
		btnback.setBounds(280, 95, 90, 25);
		btnback.addActionListener(this);
		panel1_2.add(btnback);
		
		panel1.add(panel1_2);
		
		panel1.add(panel1_1,BorderLayout.NORTH);
		
		add(panel1,BorderLayout.NORTH);
		
		table();
		DBbook db = new DBbook();
		db.showBookRR(this);
		
		setVisible(true);
		}
	public DefaultTableModel getModel() {
		return model;
	}

		private void table() {
			JPanel listPanel = new JPanel();
			//listPanel.setBackground(Color.red);
			table.getColumnModel().getColumn(0).setPreferredWidth(30);
			table.getColumnModel().getColumn(1).setPreferredWidth(30);
			table.getColumnModel().getColumn(2).setPreferredWidth(30);
			table.getColumnModel().getColumn(3).setPreferredWidth(30);
			table.getColumnModel().getColumn(4).setPreferredWidth(30);
			table.getColumnModel().getColumn(5).setPreferredWidth(30);
			
			ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			listPanel.add(ScrollPane);
			add(listPanel, BorderLayout.CENTER);
		
	}
		

		public static void main(String[] args) {
			Rent_Return_List RR = new Rent_Return_List("도서대여/반납");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == btnRR) {
				Rent_Return rr = new Rent_Return("대여/반납");
			}else if (obj ==btnback) {
				dispose();
				BookManagement mg = new BookManagement("도서 관리 프로그램");
			}else if (obj ==btnrefresh) {
				DBbook db = new DBbook();
				db.showBookRR(this);
				System.out.println("도서 대여/반납 새로고침");
			}else if (obj == btnsearch) {
				String title = tfbook.getText();
				DBbook db = new DBbook();
				db.pullReturnInfo(title, tfcode, tftitle, tfauthor, tfpublisher, tfprice, tfRent);
				System.out.println("도서 대여/반납 도서명 검색");
			}else if (obj ==btnReset) {
				tfbook.setText("");
				tfcode.setText("");
				tftitle.setText("");
				tfauthor.setText("");
				tfpublisher.setText("");
				tfprice.setText("");
				tfRent.setText("");
			}
			
		}
}
