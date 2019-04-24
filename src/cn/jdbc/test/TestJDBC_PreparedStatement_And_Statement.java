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
            // Statement执行10次，需要10次把SQL语句传输到数据库端
            // 数据库要对每一次来的SQL语句进行编译处理
			for (int i = 0; i < 100; i++) {
				String sql0 = "insert into hero values(null," + "'战斗暴龙兽：Statement方式插入'" + "," + 313.0f + "," + 50 + ")";
				s.execute(sql0);
			}
			s.close();
			/**
			 * 使用PreparedStatement
			 */
            // PreparedStatement 执行10次，只需要1次把SQL语句传输到数据库端
            // 数据库对带?的SQL进行预编译
            // 每次执行，只需要传输参数到数据库端
            // 1. 网络传输量比Statement更小
            // 2. 数据库不需要再进行编译，响应更快
			for (int i = 0; i < 10; i++) {
				//设置参数
				ps.setString(1, "战斗暴龙兽：PreparedStatement方式插入");
				ps.setFloat(2, 313.0f);
				ps.setInt(3, 50);
				//执行
				ps.execute();
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














