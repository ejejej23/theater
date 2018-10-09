package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection conn;
	
	private DBConn() {
		
	}
	
	public static Connection getConnection() {
		
		//String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";//���� �߿���
		String url = "jdbc:oracle:thin:@211.238.142.195:1521:xe";
		String user = "movie";
		String pwd = "movie";
		
		if(conn == null) {//singleton ����. ��ü���� 1���� ��
			try {
				//OracleDriver �ε�. �ڹ� 6�̻��� ��������
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//��ȭ�� ������ ���� ����Ǿ� ��ȭ�� �� �ִ� ���°� ��
				
				//Connection ��ü ����
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
				if (! conn.isClosed())		//�� ���� ������ true
					conn.close();			// �ݾƶ�
				
			} catch (SQLException e) {
				
			}
			
		}
		
		conn = null;//Ŀ�ؼ��� null�� ���� ����
	}
}
