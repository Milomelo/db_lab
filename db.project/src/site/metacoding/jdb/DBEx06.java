package site.metacoding.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBEx06 {

	public static void main(String[] args) {
		try {
			// 1. connection 연결 (세션생성) port, ip, id, password, protocal
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB연결완료");

			// 2. 버퍼 달아서 통신 (ALL:SELECT * FROM emp)
			String sql = "INSERT INTO userTbl1(id,username,password,gender) VALUES(?,?,?,?)"; // emp끝에 세미콜론 필요 없음.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 8); // 물음표의 순서, 값
			pstmt.setString(2, "there");
			pstmt.setString(3, "1234");
			pstmt.setString(4, "남");

			// 실패 -1, 성공 row개수(생성,삭제,수정), 아무변화가 없으면 0
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("성공");

			} else {
				System.err.println("실패");
			}

			// pstmt.executeUpdate();// INSERT, UPDATE, DELETE(내부에 commit 존재)

			
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
