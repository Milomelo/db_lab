package site.metacoding.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// SQK ������ �������= (SQL ���� ����)
public class DBEx04 {
	public static void login(String username, String passwords) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");

			String sql = "SELECT * FROM usertbl1 WHERE username = '"+username+"'  AND password ='"+passwords+"' "; // emp����
																						// �����ݷ�
																						// �ʿ�
																						// ����.
			Statement pstmt = conn.prepareStatement(sql);
			// ? �� ���۹����� 1�̴�.
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