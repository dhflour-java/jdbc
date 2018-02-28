package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//1. JDBC 드라이버 로딩
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
		
			//2. Connection 가져오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			//3. Statement 객체 가져오기
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "select no, last_name, first_name, email from emaillist";
			rs = stmt.executeQuery( sql );
			
			//5. 결과 가져오기(사용하기)
			while( rs.next() ) {
				long no = rs.getLong( 1 );
				String lastName = rs.getString( 2 );
				String firstName = rs.getString( 3 );
				String email = rs.getString( 4 );
			
				System.out.println( 
					no + ":" + 
					lastName + ":" + 
					firstName + ":" + email );
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println( "드라이버 로딩 실패" );
		} catch (SQLException e) {
			System.out.println( "연결실패" );
		} finally {
			//6. 자원 정리
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
	}

}
