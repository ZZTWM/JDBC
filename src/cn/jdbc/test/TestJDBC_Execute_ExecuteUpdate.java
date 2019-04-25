package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * execute��executeUpdate����ͬ�㣺������ִ�����ӣ�ɾ�����޸�
 *
 */
public class TestJDBC_Execute_ExecuteUpdate {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
	            Statement s = c.createStatement();){
            String sqlInsert = "insert into Hero values (null,'����',616,100)";
            String sqlDelete = "delete from Hero where id = 7";
            String sqlUpdate = "update Hero set hp = 300 where id = 6";
            //��ͬ�㣺������ִ�����ӣ�ɾ�����޸�
            //s.execute(sqlInsert);
            //s.execute(sqlDelete);
            //s.execute(sqlUpdate);
            s.executeUpdate(sqlInsert);
            s.executeUpdate(sqlDelete);
            s.executeUpdate(sqlUpdate);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
