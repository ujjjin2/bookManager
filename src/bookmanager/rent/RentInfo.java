package bookmanager.rent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class RentInfo extends JFrame implements ActionListener{
	private String[] title = {"대여번호", "회원이름", "회원전화","도서이름","도서번호","날짜"};
	private String[][] datas = new String[0][6];
	private DefaultTableModel model = new DefaultTableModel(datas,title);
	private JTable table = new JTable(model);
	private JScrollPane ScrollPane;
	private JButton btnback;
	
	public RentInfo(String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(470, 505);
		setLayout(new BorderLayout());
		
		JPanel paneltop = new JPanel();
		btnback = new JButton("돌아가기");
		btnback.addActionListener(this);
		paneltop.add(btnback);
		add(paneltop,BorderLayout.NORTH);
		
		Table();
		setVisible(true);
		}

		private void Table() {
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
			RentInfo RR = new RentInfo("대여정보");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == btnback) {
				dispose();
			}
			
		}

}
