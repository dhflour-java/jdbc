package jdbc;

import java.util.ArrayList;

public class EmailListDaoTest {

	public static void main(String[] args) {
		//fetchTest();
		insertTest();
		fetchListTest();
	}	
	public static void insertTest() {
		EmailListDao dao = new EmailListDao();
		
		EmailListVo vo = new EmailListVo();
		vo.setFirstName( "둘리2" );
		vo.setLastName( "고" );
		vo.setEmail( "dooly2@gmail.com" );
		
		dao.insert(vo);
	}
	
	public static void fetchTest() {
		EmailListDao dao = new EmailListDao();
		EmailListVo vo = dao.fetch(21);
		System.out.println( vo );
	}
	
	public static void fetchListTest() {
		EmailListDao dao = new EmailListDao();
		ArrayList<EmailListVo> list = dao.fetchList();
		
		// 순회1
//		int count = list.size();
//		for( int i = 0; i < count; i++ ) {
//			EmailListVo vo = list.get( i );
//			System.out.println( vo );
//		}
		
		// 순회2
		for( EmailListVo vo : list ) {
			System.out.println( vo );
		}
	}
}
