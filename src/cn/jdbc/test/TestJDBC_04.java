package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 获取总数
 */
public class TestJDBC_04 {
	
	public static void main(String[] args) {
		/**
		 * 初始化驱动
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/**
		 * 创建与数据库的连接
		 * 创建Statement
		 */
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			Statement s = c.createStatement();	
				)
		{
			String sql = "select count(*) from hero";
			ResultSet rs = s.executeQuery(sql);
			int total = 0;
			while(rs.next()){
				total = rs.getInt(1);
			}
			System.out.println("表Hero中总共有：" + total + "条数据");
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














