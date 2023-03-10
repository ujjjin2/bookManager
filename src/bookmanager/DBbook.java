package bookmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bookmanager.book.BookList;
import bookmanager.member.MemberList;
import bookmanager.rent.RentInfo;
import bookmanager.rent.Rent_Return;
import bookmanager.rent.Rent_Return_List;


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
					System.out.println("조회 적용");
					while(rs.next()) {
						String[] imsi = {rs.getString("mb_name"),rs.getString("mb_num"),rs.getString("mb_phone"),rs.getString("mb_addr")};
						memberlist.getModel().addRow(imsi);
					}
			} catch (SQLException e) {
				System.out.println("===회원 조회 실패===");
				e.printStackTrace();
			}
		}
		//회원 삭제하기
		public void memberDelete(String num) {
			try {
				pstmt = conn.prepareStatement("DELETE FROM member where mb_num='"+num+"'");
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null,"삭제가 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("===회원 삭제 성공===");	
			} catch (SQLException e) {
				System.out.println("회원 삭제 실패");
				e.printStackTrace();
			}
		}
		//수정하기-정보받아오기
		public void pullMemberInfo(String num, JTextField name,  JTextField code,  JTextField phone, JTextField addr) {
			try {
				rs = stmt.executeQuery("Select mb_name FROM member WHERE mb_num='"+num+"'");
				if (rs.next()) {
					name.setText(rs.getString("mb_name"));
				}
				rs = stmt.executeQuery("Select mb_num FROM member WHERE mb_num='"+num+"'");
				if (rs.next()) {
					code.setText(rs.getString("mb_num"));
				}
				rs = stmt.executeQuery("Select mb_phone FROM member WHERE mb_num='"+num+"'");
				if (rs.next()) {
					phone.setText(rs.getString("mb_phone"));
				}
				rs = stmt.executeQuery("Select mb_addr FROM member WHERE mb_num='"+num+"'");
				if (rs.next()) {
					addr.setText(rs.getString("mb_addr"));
				}
			} catch (SQLException e) {
				System.out.println("수정-회원정보를 받아오지 못함");
				e.printStackTrace();
			}
		}
		//수정하기 - 업데이트
		public void memberUpdate(String num,String name,  String phone, String addr) {
			try {
				pstmt = conn.prepareStatement("UPDATE member SET mb_name='"+name+"',mb_phone='"+phone+"',mb_addr='"+addr+"'"
						+ "WHERE mb_num='"+num+"'");
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null,"수정이 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("===회원 수정 성공===");	
			} catch (SQLException e) {
				System.out.println("회원 수정 실패");
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
		//도서 조회하기 DB
				public void bookSearch(BookList bookList,String title) {
					bookList.getModel().setNumRows(0);
						try {
							stmt = conn.createStatement();
							rs = stmt.executeQuery("SELECT * FROM lib WHERE lib_name='"+title+"'");
							System.out.println("조회 적용");
							while(rs.next()) {
								String[] imsi = {rs.getString("lib_code"),rs.getString("lib_name"),rs.getString("lib_author"),rs.getString("lib_publisher"),rs.getString("lib_price"),rs.getString("lib_state")};
								bookList.getModel().addRow(imsi);
							}
					} catch (SQLException e) {
						System.out.println("===도서 조회 실패===");
						e.printStackTrace();
					}
				}
		//도서 대여/반납 보여주기
		public void showBookRR(Rent_Return_List rrl) {
			rrl.getModel().setNumRows(0);
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM lib");
				
				while(rs.next()) {
					String[] imsi = {rs.getString("lib_code"),rs.getString("lib_name"),rs.getString("lib_author"),rs.getString("lib_publisher"),rs.getString("lib_price"),rs.getString("lib_state")};
					rrl.getModel().addRow(imsi);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//도서 등록하기 
				public void BookRegister(String code, String title, String author, String publisher, String price,String rent) {
					try {
						rs = stmt.executeQuery("SELECT lib_code,lib_state FROM lib");
						if (rs.next()) {
							String CODE = rs.getString("lib_code");
							if (code.equals(CODE)) {
								System.out.println("중복된 도서번호가 있습니다");
								JOptionPane.showMessageDialog(null,"중복된 도서번호가 있습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
							}else {
								if (rent.equals("Y") | rent.equals("N")) {
									pstmt = conn.prepareStatement("INSERT INTO lib(lib_code,lib_name,lib_author,lib_publisher,lib_price,lib_state)"
											+ " VALUES ('"+code+"','"+title+"','"+author+"','"+publisher+"','"+price+"','"+rent+"')");
									pstmt.executeUpdate();
									JOptionPane.showMessageDialog(null,"입력이 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
									System.out.println("===도서 등록 성공===");	
								}else {
									JOptionPane.showMessageDialog(null,"대여정보는 Y/N만 적어주세요.","메세지",JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
						
					} catch (SQLException e) {
						System.out.println("=== 도서 등록 실패 ===");
						e.printStackTrace();
					}
				}
				
				//도서 정보 삭제하기
				public void bookDelete(String code) {
					try {
						pstmt = conn.prepareStatement("DELETE FROM lib where lib_code='"+code+"'");
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"삭제가 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
						System.out.println("===도서 삭제 성공===");	
					} catch (SQLException e) {
						System.out.println("도서 삭제 실패");
						e.printStackTrace();
					}
				}
				//수정하기-정보받아오기(책)
				public void pullBookInfo(String code, JTextField tfcode,  JTextField tftt,  JTextField tfauthor, JTextField tfpublisher,
						JTextField tfPrice,JTextField tfRent) {
					try {
						rs = stmt.executeQuery("Select lib_code FROM lib WHERE lib_code='"+code+"'");
						if (rs.next()) {
							tfcode.setText(rs.getString("lib_code"));
						}
						rs = stmt.executeQuery("Select lib_name FROM lib WHERE lib_code='"+code+"'");
						if (rs.next()) {
							tftt.setText(rs.getString("lib_name"));
						}
						rs = stmt.executeQuery("Select lib_author FROM lib WHERE lib_code='"+code+"'");
						if (rs.next()) {
							tfauthor.setText(rs.getString("lib_author"));
						}
						rs = stmt.executeQuery("Select lib_publisher FROM lib WHERE lib_code='"+code+"'");
						if (rs.next()) {
							tfpublisher.setText(rs.getString("lib_publisher"));
						}
						rs = stmt.executeQuery("Select lib_price FROM lib WHERE lib_code='"+code+"'");
						if (rs.next()) {
							tfPrice.setText(rs.getString("lib_price"));
						}
						rs = stmt.executeQuery("Select lib_state FROM lib WHERE lib_code='"+code+"'");
						if (rs.next()) {
							tfRent.setText(rs.getString("lib_state"));
						}
					} catch (SQLException e) {
						System.out.println("수정-도서정보를 받아오지 못함");
						e.printStackTrace();
					}
				}
				//수정하기 - 업데이트(책)
				public void BookUpdate(String CODE,  String title, String author,String publisher,String price,String rent) {
					try {
						pstmt = conn.prepareStatement("UPDATE lib SET lib_name='"+title+"',lib_author='"+author+"'"
								+ ",lib_publisher='"+publisher+"',lib_price='"+price+"',lib_state='"+rent+"'"
								+ "WHERE lib_code='"+CODE+"'");
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"수정이 완료되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
						System.out.println("===도서 수정 성공===");	
					} catch (SQLException e) {
						System.out.println("도서 수정 실패");
						e.printStackTrace();
					}
				}
				
				//대여 정보 리스트
				public void rentinfo(RentInfo rentInfo) {
					rentInfo.getModel().setNumRows(0);
					try {
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM rent");
					
						while(rs.next()) {
							String[] imsi = {rs.getString("rent_no"),rs.getString("mem_name"),rs.getString("mem_phone"),rs.getString("lib_name"),rs.getString("lib_code"),rs.getString("rent_date")};
							rentInfo.getModel().addRow(imsi);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
				
				//수정하기-정보받아오기(책) - 도서대여/반납
				public void pullReturnInfo(String title, JTextField tfcode,  JTextField tftt,  JTextField tfauthor, JTextField tfpublisher,
						JTextField tfPrice,JTextField tfRent) {
					try {
						rs = stmt.executeQuery("Select lib_code FROM lib WHERE lib_name='"+title+"'");
						if (rs.next()) {
							tfcode.setText(rs.getString("lib_code"));
						}
						rs = stmt.executeQuery("Select lib_name FROM lib WHERE lib_name='"+title+"'");
						if (rs.next()) {
							tftt.setText(rs.getString("lib_name"));
						}
						rs = stmt.executeQuery("Select lib_author FROM lib WHERE lib_name='"+title+"'");
						if (rs.next()) {
							tfauthor.setText(rs.getString("lib_author"));
						}
						rs = stmt.executeQuery("Select lib_publisher FROM lib WHERE lib_name='"+title+"'");
						if (rs.next()) {
							tfpublisher.setText(rs.getString("lib_publisher"));
						}
						rs = stmt.executeQuery("Select lib_price FROM lib WHERE lib_name='"+title+"'");
						if (rs.next()) {
							tfPrice.setText(rs.getString("lib_price"));
						}
						rs = stmt.executeQuery("Select lib_state FROM lib WHERE lib_name='"+title+"'");
						if (rs.next()) {
							tfRent.setText(rs.getString("lib_state"));
						}
					} catch (SQLException e) {
						System.out.println("수정-도서정보를 받아오지 못함");
						e.printStackTrace();
					}
				}
				//책 대여
				public void RentBook(String num, String code, String title,Rent_Return rr) {
					String mb_name= null;
					String phone= null;
					String TITLE = null;
					String CODE= null;
					String rent= null;
					
					try {
						rs = stmt.executeQuery("SELECT mb_name,mb_phone FROM member Where mb_num='"+num+"'");
						if (rs.next()) {
							mb_name = rs.getString("mb_name");
							phone = rs.getString("mb_phone");
						}
						rs = stmt.executeQuery("SELECT lib_name,lib_code,lib_state FROM lib Where lib_code='"+code+"' OR lib_name='"+title+"'");
						if (rs.next()) {
							TITLE = rs.getString("lib_name");
							CODE = rs.getString("lib_code");
							rent = rs.getString("lib_state");
						}
						if (rent.equals("N")) {
							JOptionPane.showMessageDialog(null,"대여중인 도서입니다.","메세지",JOptionPane.ERROR_MESSAGE);
						}else {
							pstmt = conn.prepareStatement("INSERT INTO rent(mem_name,mem_phone,lib_code,lib_name,rent_date)"
									+ " VALUES ('"+mb_name+"','"+phone+"','"+CODE+"','"+TITLE+"',Date_Format(now(),'%y-%m-%d'))");
							pstmt.executeUpdate();
							//Y->N으로 변경
							pstmt = conn.prepareStatement("UPDATE lib SET lib_state='"+"N"+"' Where lib_code='"+CODE+"' OR lib_name='"+TITLE+"'");
							pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null,"대여했습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
							System.out.println("===대여 등록 성공===");	
							DBbook db = new DBbook();
							db.Rentlist(rr, CODE,mb_name);
						}
					} catch (SQLException e) {
						System.out.println("책 대여 실패");
						e.printStackTrace();
					}
				}
				//책 반납
				public void ReturnBook(String num,String code, String title) {
					String mb_name= null;
					try {
						rs = stmt.executeQuery("SELECT mb_name FROM member Where mb_num='"+num+"'");
						if (rs.next()) {
							mb_name = rs.getString("mb_name");
						}
						pstmt = conn.prepareStatement("DELETE FROM rent where (lib_code='"+code+"' OR lib_name='"+title+"') AND mem_name='"+mb_name+"'");
						pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null,"반납되었습니다.","메세지",JOptionPane.INFORMATION_MESSAGE);
						//N->Y으로 변경
						pstmt = conn.prepareStatement("UPDATE lib SET lib_state='"+"Y"+"' Where lib_code='"+code+"' OR lib_name='"+title+"'");
						pstmt.executeUpdate();
						System.out.println("===반납 성공===");	
					} catch (SQLException e) {
						System.out.println("반납 실패");
						e.printStackTrace();
					}
				}
				//대여/반납 리스트
				public void Rentlist(Rent_Return rr, String code,String name) {
					rr.getModel().setNumRows(0);
					try {
						stmt = conn.createStatement();
						rs = stmt.executeQuery("SELECT * FROM rent Where lib_code='"+code+"' AND mem_name='"+name+"'");
					
						while(rs.next()) {
							String[] imsi = {rs.getString("rent_no"),rs.getString("mem_name"),rs.getString("mem_phone"),rs.getString("lib_name"),rs.getString("lib_code"),rs.getString("rent_date")};
							rr.getModel().addRow(imsi);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
}
