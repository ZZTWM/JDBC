package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 查询
 */
public class TestJDBC_Query {
	
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
			//执行查询语句，并把结果集返回给ResultSet
            String sql = "select * from hero";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
            	int id = rs.getInt("id");//可以使用字段名，对应表里的id
            	String name = rs.getString(2);//也可以使用字段的顺序
            	float hp = rs.getFloat("hp");
            	int damage = rs.getInt(4);
            	System.out.printf("%d\t%s\t%f\t%d%n",id,name,hp,damage);
            }
            //不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
            //rs.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














