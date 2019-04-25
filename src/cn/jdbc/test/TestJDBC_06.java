package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class TestJDBC_06 {
	
	/**
	 * 1��ͨ��PreparedStatement��ʽ����
	 * @param count
	 */
	public static void insertByPreparedStatement(int count){
		
		String sql = "insert into hero values(null,?,?,?)";
		/**
		 * ���������ݿ������
		 * ����PreparedStatement
		 */ 
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			PreparedStatement ps = c.prepareStatement(sql);
			)
		{
			/**
			 * ʹ��PreparedStatement
			 */
            // PreparedStatement ִ��10�Σ�ֻ��Ҫ1�ΰ�SQL��䴫�䵽���ݿ��
            // ���ݿ�Դ�?��SQL����Ԥ����
            // ÿ��ִ�У�ֻ��Ҫ������������ݿ��
            // 1. ���紫������Statement��С
            // 2. ���ݿⲻ��Ҫ�ٽ��б��룬��Ӧ����
			for (int i = 0; i < count; i++) {
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
	
	/**
	 * 2��ʹ��Statement��ʽ����
	 * @param count
	 */
	public static void insertByStatement(int count){
		/**
		 * ���������ݿ������
		 * ����Statement
		 */
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			Statement s = c.createStatement();
			)
		{
			/**
			 * ʹ��Statement
			 */
            // Statementִ��10�Σ���Ҫ10�ΰ�SQL��䴫�䵽���ݿ��
            // ���ݿ�Ҫ��ÿһ������SQL�����б��봦��
			for (int i = 0; i < count; i++) {
				String sql0 = "insert into hero values(null," + "'ս�������ޣ�Statement��ʽ����'" + "," + 313.0f + "," + 50 + ")";
				s.execute(sql0);
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	
	}
	
	public static void main(String[] args) {
		/**
		 * ��ʼ������
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		int count = 100;
		long start1 = System.currentTimeMillis();
		insertByPreparedStatement(count);
		long end1 = System.currentTimeMillis();
		System.out.printf("ʹ��Ԥ����PreparedStatement����%d�����ݣ���ʱ: %d����%n", count, end1 - start1);
		
		long start2 = System.currentTimeMillis();
		insertByStatement(count);
		long end2 = System.currentTimeMillis();
		System.out.printf("ʹ��Statement����%d�����ݣ���ʱ: %d ����%n", count, end2 - start2);
		
	}
	
}














