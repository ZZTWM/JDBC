package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * SQL语句判断账号密码是否正确
 */
public class TestJDBC_03 {
	
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
			String name = "dashen";
			//正确的密码是：thisispassword
			String password = "thisispassword";
			//执行查询语句，并把结果集返回给ResultSet
			String sql = "select * from user where name = '" + name +"' and password = '" + password+"'";
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
            	System.out.println("账号密码正确");
            }else{
            	System.out.println("账号密码错误");
            }
            //不一定要在这里关闭ReultSet，因为Statement关闭的时候，会自动关闭ResultSet
            //rs.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














