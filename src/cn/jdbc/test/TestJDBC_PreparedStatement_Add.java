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
		 * 初始化驱动
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/**
		 * 创建与数据库的连接
		 * 创建PreparedStatement
		 */
		String sql = "insert into hero values(null,?,?,?)";
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			//根据sql语句创建PreparedStatement
			PreparedStatement ps = c.prepareStatement(sql);
			)
		{
			//设置参数
			ps.setString(1, "战斗暴龙兽");
			ps.setFloat(2, 313.0f);
			ps.setInt(3, 50);
			//执行
			ps.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














