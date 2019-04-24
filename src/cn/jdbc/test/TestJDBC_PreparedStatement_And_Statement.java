package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class TestJDBC_PreparedStatement_And_Statement {
	
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
			Statement s = c.createStatement();
			//����sql��䴴��PreparedStatement
			PreparedStatement ps = c.prepareStatement(sql);
			)
		{
			/**
			 * ʹ��Statement
			 */
			// Statement��Ҫ�����ַ���ƴ�ӣ��ɶ��Ժ�ά���ԱȽϲ�
			String sql0 = "insert into hero values(null," + "'ս�������ޣ�Statement��ʽ����'" + "," + 313.0f + "," + 50 + ")";
			s.execute(sql0);
			/**
			 * ʹ��PreparedStatement
			 */
			//���ò���
            // PreparedStatement ʹ�ò������ã��ɶ��Ժã����׷���
            // "insert into hero values(null,?,?,?)";
			ps.setString(1, "ս�������ޣ�PreparedStatement��ʽ����");
			ps.setFloat(2, 313.0f);
			ps.setInt(3, 50);
			//ִ��
			ps.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














