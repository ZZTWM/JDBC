package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 	如果觉得上一步的关闭连接的方式很麻烦，可以参考关闭流 的方式，
 * 	使用try-with-resource的方式自动关闭连接，
 * 	因为Connection和Statement都实现了AutoCloseable接口
 *
 */
public class TestJDBC_TryWithResource {
	
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
			String sql = "insert into hero values(null," + "'提莫'" + "," + 313.0f + "," + 50 + ")";;
			s.execute(sql);
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














