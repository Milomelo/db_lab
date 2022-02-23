package site.metacoding.jdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBEx01 {

	
    public static void main(String[] args) {
        try {
            // 1. connection ���� (���ǻ���) port, ip, id, password, protocal
            Connection conn = DriverManager.getConnection
                      ("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
            System.out.println("DB����Ϸ�");

            //2. ���� �޾Ƽ� ��� (ALL:SELECT * FROM emp)
            String sql = "SELECT empno, ename FROM emp"; // emp���� �����ݷ� �ʿ� ����.
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs =pstmt.executeQuery();// SELLECT
            //pstmt.executeUpdate();// INSERT, UPDATE, DELETE
            //rs.next();//Ŀ�� ��ĭ ������
            //System.out.println(rs.next());

            
            while (rs.next()) {
                System.out.println("empno : "+ rs.getInt("empno"));
                System.out.println("ename : "+rs.getString("ename"));
                System.out.println("==========");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}