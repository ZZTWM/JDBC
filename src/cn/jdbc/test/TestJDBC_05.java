package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ҳ��ѯ
 */
public class TestJDBC_05 {
	
	public static void list(int start,int count){
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
		 * ����Statement
		 */
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			Statement s = c.createStatement();	
				)
		{
			String sql = "select * from hero limit " +start + "," + count;
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
                int id = rs.getInt("id");// ����ʹ���ֶ���
                String name = rs.getString(2);// Ҳ����ʹ���ֶε�˳��
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		list(10,5);
	}
	
}














