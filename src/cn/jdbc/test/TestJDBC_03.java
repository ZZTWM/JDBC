package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQL����ж��˺������Ƿ���ȷ
 */
public class TestJDBC_03 {
	
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
		 * ����Statement
		 */
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			Statement s = c.createStatement();	
				)
		{
			String name = "dashen";
			//��ȷ�������ǣ�thisispassword
			String password = "thisispassword";
			//ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
			String sql = "select * from user where name = '" + name +"' and password = '" + password+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
            	System.out.println("�˺�������ȷ");
            }else{
            	System.out.println("�˺��������");
            }
            //��һ��Ҫ������ر�ReultSet����ΪStatement�رյ�ʱ�򣬻��Զ��ر�ResultSet
            //rs.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














