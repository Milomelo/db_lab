package site.metacoding.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// SQK 인젝션 개념잡기= (SQL 주입 공격)
public class DBEx04 {
	public static void login(String username, String passwords) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			String sql = "SELECT * FROM usertbl1 WHERE username = '"+username+"'  AND password ='"+passwords+"' "; // emp끝에
																						// 세미콜론
																						// 필요
																						// 없음.
			Statement pstmt = conn.prepareStatement(sql);
			// ? 의 시작번지는 1이다.
			ResultSet rs = pstmt.executeQuery(sql);// SELLECT

			while (rs.next()) {
				Session.isLogin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		login("ssar", "ssar1234");
		System.out.println(Session.isLogin);
	}
}
