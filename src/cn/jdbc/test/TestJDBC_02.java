package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 */
public class TestJDBC_02 {
	
	public static void  execute(String sql){
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
            s.execute(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		String sql = "delete from hero where id = 1";
		execute(sql);
	}
	
}














