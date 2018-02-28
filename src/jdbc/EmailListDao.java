package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class EmailListDao {
	
	public boolean insert( EmailListVo vo ) {
		
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. Statement 준비
			String sql = 
				"insert into emaillist" + 
				"     values(seq_emaillist.nextval, ?, ?, ?)"; 
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());
			
			//5. sql문 실행
			int count = pstmt.executeUpdate();
			
			//6. 성공 유무
			if( count == 1 ) {
				result = true;
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패" );
		} catch (SQLException e) {
			System.out.println( "실패:" + e );
		} finally {
			//7. 자원정리
			try {
				if( pstmt != null ) {
					pstmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public ArrayList<EmailListVo> fetchList() {
		
		ArrayList<EmailListVo> list = new ArrayList<EmailListVo>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			stmt = conn.createStatement();

			String sql = 
				"select no, " +
				"       last_name," +
				"       first_name," +
				"       email" +
				"  from emaillist" +
				" order by no desc";
			
			rs = stmt.executeQuery(sql);
			while( rs.next() ) {
				long no = rs.getLong( 1 );
				String lastName = rs.getString( 2 );
				String firstName = rs.getString( 3 );
				String email = rs.getString( 4 );
				
				EmailListVo vo = new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
				
				list.add( vo );
			}
		
		} catch (ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패" );
		} catch (SQLException e) {
			System.out.println( "실패:" + e );
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( stmt != null ) {
					stmt.close();
				}
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public EmailListVo fetch( long n ) {
		
		EmailListVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. JDBC 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			String sql = 
				"select no, " +
				"       last_name," +
				"       first_name," +
				"       email" +
				"  from emaillist" +
				" where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, n);
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				long no = rs.getLong( 1 );
				String lastName = rs.getString( 2 );
				String firstName = rs.getString( 3 );
				String email = rs.getString( 4 );
				
				vo = new EmailListVo();
				vo.setNo(no);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				vo.setEmail(email);
			}
		
		} catch (ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패" );
		} catch (SQLException e) {
			System.out.println( "실패:" + e );
		} finally {
			try {
				if( rs != null ) {
					rs.close();
				}
				if( pstmt != null ) {
					pstmt.close();
				}				
				if( conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
}
