package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 */
public class TestJDBC_01 {
	
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
            for (int i = 0; i < 100; i++) {
                String sql = "insert into hero values(null," + "'英雄"+i+"'"  + "," + 313.0f + "," + 50 + ")";
                s.execute(sql);
            }
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














