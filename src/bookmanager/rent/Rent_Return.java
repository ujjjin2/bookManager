package bookmanager.rent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import bookmanager.DBbook;

public class Rent_Return extends JFrame implements ActionListener{
	
	private JTextField tfnum;
	private JButton btnRent;
	private JButton btnReturn;
	private JButton btnCancle;
	private JTextField tfcode;
	private JTextField tftitle;
	private String[] title = {"대여번호", "회원이름", "회원전화","도서이름","도서번호","날짜"};
	private String[][] datas = new String[0][6];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	
	public Rent_Return(String title) {
	setTitle(title);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation(300, 100);
	setSize(470, 535);
	setLayout(new BorderLayout());
	
	JPanel panelUp = new JPanel();
	panelUp.setLayout(new BorderLayout());
	JPanel panel1 = new JPanel();
	JLabel lblnum = new JLabel("회원주민번호");
	panel1.add(lblnum);
	tfnum = new JTextField(15);
	tfnum.addActionListener(this);
	panel1.add(tfnum);
	btnRent = new JButton("대여");
	btnRent.addActionListener(this);
	panel1.add(btnRent);
	btnReturn = new JButton("반납");
	btnReturn.addActionListener(this);
	panel1.add(btnReturn);
	btnCancle = new JButton("취소");
	btnCancle.addActionListener(this);
	panel1.add(btnCancle);
	panelUp.add(panel1, BorderLayout.NORTH);
	
	//도서번호,제목
	JPanel panel2 = new JPanel();
	JLabel lblcode = new JLabel("도서번호");
	panel2.add(lblcode);
	tfcode = new JTextField(15);
	tfcode.addActionListener(this);
	panel2.add(tfcode);
	JLabel lbltt = new JLabel("제목");
	panel2.add(lbltt);
	tftitle = new JTextField(15);
	tftitle.addActionListener(this);
	panel2.add(tftitle);
	panelUp.add(panel2, BorderLayout.SOUTH);
	
	add(panelUp,BorderLayout.NORTH);
	table();
	
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
		
		ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listPanel.add(ScrollPane);
		add(listPanel, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		Rent_Return RR = new Rent_Return("대여/반납");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnCancle) {
			dispose();
		}else if (obj == btnRent) {
			String num = tfnum.getText();
			String code = tfcode.getText();
			String title = tftitle.getText();
			
			DBbook db = new DBbook();
			db.RentBook(num, code, title,this);
			//db.Rentlist(this,code);
			System.out.println("대여/반납 - 대여 버튼 누름");
			
		}else if (obj == btnReturn) {
			String num = tfnum.getText();
			String code = tfcode.getText();
			String title = tftitle.getText();
			
			DBbook db = new DBbook();
			db.ReturnBook(num,code,title);
			System.out.println("대여/반납 - 반납 버튼 누름");
		}
		
	}
}
