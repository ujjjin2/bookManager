package bookmanager.book;

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

import bookmanager.DBbook;

public class BookDelete extends JFrame implements ActionListener{
	private JButton btnDelete,btnCancle;
	private JTextField tf;
	BookList booklist;

	public BookDelete(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(240, 190);
		setLayout(new BorderLayout());
		
		//회원 검색 타이틀
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.DARK_GRAY);
		JLabel lbltitle = new JLabel("도서삭제");
		lbltitle.setForeground(Color.white);
		panelTitle.add(lbltitle);
		add(panelTitle, BorderLayout.NORTH);
		
		
		//정보 입력란
		JPanel panelCenter = new JPanel();
		JLabel lbl = new JLabel("도서번호:");
		panelCenter.add(lbl);
		tf = new JTextField(10);
		panelCenter.add(tf);
		add(panelCenter);
		
		// 확인/취소 버튼
		JPanel panelButton = new JPanel();
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		panelButton.add(btnDelete);
		btnCancle = new JButton("취소");
		panelButton.add(btnCancle);
		btnCancle.addActionListener(this);
		add(panelButton,BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		BookDelete mb= new BookDelete("회원등록");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (btnCancle==obj) {
			dispose();
		}else if (btnDelete == obj) {
			
		}
		
	}
}
