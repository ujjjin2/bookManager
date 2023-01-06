package bookmanager.member;

import java.awt.BorderLayout;
import java.awt.Color;
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


public class MemberList extends JFrame implements ActionListener{
	private JButton btnrefresh;
	private JButton btnRegister;
	private JButton btnCheck;
	private JButton btnChange;
	private JButton btnDelete;
	private String[] title = {"이름", "주민번호", "전화번호","주소"};
	private String[][] datas = new String[0][4];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JButton btnBack;
	private JScrollPane ScrollPane;

	public MemberList(String title) {
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
		db.showRank(this);
		
		setVisible(true);
	}
	public DefaultTableModel getModel() {
		return model;
	}
	
	private void table() {
		//회원 목록
		JPanel listPanel = new JPanel();
		listPanel.setBorder(new TitledBorder(new LineBorder(Color.gray,1),"회원목록"));
		//listPanel.setBackground(Color.red);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		
		ScrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listPanel.add(ScrollPane);
		add(listPanel, BorderLayout.CENTER);
		
	}

	public static void main(String[] args) {
		MemberList book = new MemberList("회원목록");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnRegister) {
			MemberRegister MR = new MemberRegister("회원등록");
		}else if (obj ==btnCheck) {
			MemberSearch MS = new MemberSearch("회원검색");
		}else if (obj ==btnBack) {
			dispose();
			BookManagement bk = new BookManagement("도서관리프로그램");
		}else if (obj == btnChange) {
			MemberModify MM = new MemberModify("회원정보 수정");
		}else if (obj == btnDelete) {
			MemberDelete MD = new MemberDelete("회원삭제");
		}
		
	}
}
