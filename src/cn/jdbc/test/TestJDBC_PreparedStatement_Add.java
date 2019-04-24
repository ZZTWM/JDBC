package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class TestJDBC_PreparedStatement_Add {
	
	public static void main(String[] args) {
		/**
		 * ��ʼ������
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/**
		 * ���������ݿ������
		 * ����PreparedStatement
		 */
		String sql = "insert into hero values(null,?,?,?)";
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			//����sql��䴴��PreparedStatement
			PreparedStatement ps = c.prepareStatement(sql);
			)
		{
			//���ò���
			ps.setString(1, "ս��������");
			ps.setFloat(2, 313.0f);
			ps.setInt(3, 50);
			//ִ��
			ps.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














