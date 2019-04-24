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
            // Statementִ��10�Σ���Ҫ10�ΰ�SQL��䴫�䵽���ݿ��
            // ���ݿ�Ҫ��ÿһ������SQL�����б��봦��
			for (int i = 0; i < 100; i++) {
				String sql0 = "insert into hero values(null," + "'ս�������ޣ�Statement��ʽ����'" + "," + 313.0f + "," + 50 + ")";
				s.execute(sql0);
			}
			s.close();
			/**
			 * ʹ��PreparedStatement
			 */
            // PreparedStatement ִ��10�Σ�ֻ��Ҫ1�ΰ�SQL��䴫�䵽���ݿ��
            // ���ݿ�Դ�?��SQL����Ԥ����
            // ÿ��ִ�У�ֻ��Ҫ������������ݿ��
            // 1. ���紫������Statement��С
            // 2. ���ݿⲻ��Ҫ�ٽ��б��룬��Ӧ����
			for (int i = 0; i < 10; i++) {
				//���ò���
				ps.setString(1, "ս�������ޣ�PreparedStatement��ʽ����");
				ps.setFloat(2, 313.0f);
				ps.setInt(3, 50);
				//ִ��
				ps.execute();
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














