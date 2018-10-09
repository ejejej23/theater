package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection conn;
	
	private DBConn() {
		
	}
	
	public static Connection getConnection() {
		
		//String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";//가장 중요함
		String url = "jdbc:oracle:thin:@211.238.142.195:1521:xe";
		String user = "movie";
		String pwd = "movie";
		
		if(conn == null) {//singleton 패턴. 객체생성 1번만 함
			try {
				//OracleDriver 로딩. 자바 6이상은 생략가능
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//전화로 따지면 서로 연결되어 통화할 수 있는 상태가 됨
				
				//Connection 객체 생성
				conn = DriverManager.getConnection(url, user, pwd);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}
	
	public static void close() {
		if(conn != null) {
			try {
				if (! conn.isClosed())		//안 닫혀 있으면 true
					conn.close();			// 닫아라
				
			} catch (SQLException e) {
				
			}
			
		}
		
		conn = null;//커넥션이 null일 때만 닫음
	}
}
