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
			Statement s = c.createStatement();
			//根据sql语句创建PreparedStatement
			PreparedStatement ps = c.prepareStatement(sql);
			)
		{
			/**
			 * 使用Statement
			 */
			// Statement需要进行字符串拼接，可读性和维修性比较差
			String sql0 = "insert into hero values(null," + "'战斗暴龙兽：Statement方式插入'" + "," + 313.0f + "," + 50 + ")";
			s.execute(sql0);
			/**
			 * 使用PreparedStatement
			 */
			//设置参数
            // PreparedStatement 使用参数设置，可读性好，不易犯错
            // "insert into hero values(null,?,?,?)";
			ps.setString(1, "战斗暴龙兽：PreparedStatement方式插入");
			ps.setFloat(2, 313.0f);
			ps.setInt(3, 50);
			//执行
			ps.execute();
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














