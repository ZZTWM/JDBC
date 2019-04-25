package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ҳ��ѯ��ʹ��execute��ʽ
 * @author Administrator
 *
 */
public class TestJDBC_07 {
	
	public static void list(int start,int count){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin"); Statement s = c.createStatement();)
		{
			String sql = "select * from hero limit " + start + "," + count;;
			boolean isQuery = s.execute(sql);
			if(isQuery){
				// ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
				ResultSet rs = s.getResultSet();
				while(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString(2);
                    float hp = rs.getFloat("hp");
                    int damage = rs.getInt(4);
                    System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		list(10,5);
		
	}
	
}
