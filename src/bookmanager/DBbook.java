package bookmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bookmanager.book.BookList;
import bookmanager.member.MemberList;


public class DBbook {
	
	private Connection conn =null;
	private Statement stmt;
	private ResultSet rs = null;
	private PreparedStatement pstmt;
	
	public DBbook(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/world202045016?serverTimezone=UTC",
					"user", 
					"1234");
		
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("예외발생 : 해당 드라이버가 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("예외발생 : 접속 정보 확인이 필요합니다.");
			e.printStackTrace();
		}
	}
	
	//로그인 하기
	public void findadmin(String ID, String PW) {
		try {
			rs = stmt.executeQuery("SELECT id,pw FROM admin WHERE id = '" + ID + "'");
			if (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				
				if (ID.equals(id) && PW.equals(pw)) {
					BookManagement book = new BookManagement("도서 관리 프로그램");
					System.out.println("로그인 성공");
				}else {
					System.out.println("로그인 입력 실수");
				}
			}
			
			
		} catch (SQLException e) {
			System.out.println("FindAdmin 쿼리 문제");
			e.printStackTrace();
		}
		
	
	}
	
	//회원목록 보여주기
		public void showRank(MemberList memberlist) {
			memberlist.getModel().setNumRows(0);
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM member");
			
				while(rs.next()) {
					String[] imsi = {rs.getString("mb_name"),rs.getString("mb_num"),rs.getString("mb_phone"),rs.getString("mb_addr")};
					memberlist.getModel().addRow(imsi);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//회원 등록하기 
		public void memberRegister(String name, String num, String phone, String addr) {
			try {
				rs = stmt.executeQuery("SELECT mb_num FROM member");
				if (rs.next()) {
					String NUM = rs.getString("mb_num");
					if (num.equals(NUM)) {
						System.out.println("중복된 아이디 입니다");
						JOptionPane.showMessageDialog(null,"중복된 아이디가 있습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
					}else {
						pstmt = conn.prepareStatement("INSERT INTO member(mb_name,mb_num,mb_phone,mb_addr)"
								+ " VALUES ('"+name+"','"+num+"','"+phone+"','"+addr+"')");
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"처리가 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
						System.out.println("===회원 등록 성공===");	
					}
				}
				
			} catch (SQLException e) {
				System.out.println("=== 회원 등록 실패 ===");
				e.printStackTrace();
			}
			
		}
		
		//회원 조회하기 DB
		public void memberSearch(MemberList memberlist,String num) {
				memberlist.getModel().setNumRows(0);
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM member WHERE mb_num='"+num+"'");
				
					while(rs.next()) {
						String[] imsi = {rs.getString("mb_name"),rs.getString("mb_num"),rs.getString("mb_phone"),rs.getString("mb_addr")};
						memberlist.getModel().addRow(imsi);
					}
			} catch (SQLException e) {
				System.out.println("===회원 조회 실패===");
				e.printStackTrace();
			}
		}

		//도서목록 보여주기
		public void showBook(BookList booklist) {
			booklist.getModel().setNumRows(0);
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM lib");
			
				while(rs.next()) {
					String[] imsi = {rs.getString("lib_code"),rs.getString("lib_name"),rs.getString("lib_author"),rs.getString("lib_publisher"),rs.getString("lib_price"),rs.getString("lib_state")};
					booklist.getModel().addRow(imsi);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
