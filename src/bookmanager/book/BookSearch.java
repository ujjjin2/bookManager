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

public class BookSearch extends JFrame implements ActionListener{
	private JButton btnSearch,btnCancle;
	private JTextField tf;
	BookList booklist;

	public BookSearch(String title) {
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(300, 100);
		setSize(240, 190);
		setLayout(new BorderLayout());
		
		//도서 검색 타이틀
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.DARK_GRAY);
		JLabel lbltitle = new JLabel("도서검색");
		lbltitle.setForeground(Color.white);
		panelTitle.add(lbltitle);
		add(panelTitle, BorderLayout.NORTH);
		
		
		//정보 입력란
		JPanel panelCenter = new JPanel();
		JLabel lbl = new JLabel("도서제목:");
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
		BookSearch mb= new BookSearch("도서검색");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (btnCancle==obj) {
			dispose();
		}else if (btnSearch == obj) {
			String num = tf.getText();
			DBbook db = new DBbook();
			//db.memberSearch(booklist, num);
			JOptionPane.showMessageDialog(null,"검색이 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
}
