package site.metacoding.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//PrepareStatement 변수 바인딩하기
public class DBEx03 {

	public static void login(String username, String passwords) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			String sql = "SELECT * FROM usertbl1 WHERE username = ?  AND password =?"; // emp끝에
																						// 세미콜론
																						// 필요
																						// 없음.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// ? 의 시작번지는 1이다.
			pstmt.setString(1, username);
			pstmt.setString(2, passwords);
			ResultSet rs = pstmt.executeQuery();// SELLECT

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