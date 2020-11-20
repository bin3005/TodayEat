package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	//1.연결
		public static Connection getConnection() {
			Connection con = null;
			
			String driver = "";
			String url = "";
			String id = "";
			String pw = "";
			
			try {
				Properties prop = new Properties();
				prop.load(new FileReader("driver.properties"));

				driver = prop.getProperty("driver");
				url = prop.getProperty("url");
				id = prop.getProperty("user");
				pw = prop.getProperty("password");
				
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("[ERROR] 드라이버 등록 실패");
				e.printStackTrace();
			}
						
			try {
				con = DriverManager.getConnection(url,id,pw);
				con.setAutoCommit(false);
			} catch (SQLException e) {
				System.out.println("[ERROR] 오라클 연결 실패");
				e.printStackTrace();
			}
			
			
			return con;
		}
		
		//2.해제
		public static void close(Connection con) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("[ERROR] connection close 에러");
				e.printStackTrace();
			}
		}
		public static void close(Statement stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("[ERROR] statement close 에러");
				e.printStackTrace();
			}
		}
		public static void close(ResultSet rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println("[ERROR] resultset close 에러");
				e.printStackTrace();
			}
		}
		//3.저장
		public static void commit(Connection con) {
			try {
				con.commit();
			} catch (SQLException e) {
				System.out.println("[ERROR] commit 에러");
				e.printStackTrace();
			}
		}
		//4.취소
		public static void rollback(Connection con) {
			try {
				con.rollback();
			} catch (SQLException e) {
				System.out.println("[ERROR] rollback 에러");
				e.printStackTrace();
			}
		}
}
