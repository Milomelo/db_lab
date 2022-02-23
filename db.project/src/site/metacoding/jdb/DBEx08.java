package site.metacoding.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBEx08 {

	public static void main(String[] args) {
		try {
			// 1. connection ���� (���ǻ���) port, ip, id, password, protocal
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			System.out.println("DB����Ϸ�");

			// 2. ���� �޾Ƽ� ��� (ALL:SELECT * FROM emp)
			String sql = "DELETE FROM userTbl1 WHERE id = ?"; // emp���� �����ݷ� �ʿ� ����.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 7);

			// ���� -1, ���� row����(����,����,����), �ƹ���ȭ�� ������ 0
			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("����");

			} else {
				System.err.println("����");
			}

			// pstmt.executeUpdate();// INSERT, UPDATE, DELETE(���ο� commit ����)

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}