package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC_Execute_ExecuteUpdate02 {
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
	            Statement s = c.createStatement();)
		{
            //��ͬ1��execute����ִ�в�ѯ���
            //Ȼ��ͨ��getResultSet���ѽ����ȡ����
			String sqlSelect = "select * from hero";
			s.execute(sqlSelect);
			ResultSet rs = s.getResultSet();
			while(rs.next()){
            	int id = rs.getInt("id");//����ʹ���ֶ�������Ӧ�����id
            	String name = rs.getString(2);//Ҳ����ʹ���ֶε�˳��
            	float hp = rs.getFloat("hp");
            	int damage = rs.getInt(4);
            	System.out.printf("%d\t%s\t%f\t%d%n",id,name,hp,damage);
			}
			// executeUpdate����ִ�в�ѯ���
            // s.executeUpdate(sqlSelect);
			
			// ��ͬ2:
            // execute����boolean���ͣ�true��ʾִ�е��ǲ�ѯ��䣬false��ʾִ�е���insert,delete,update�ȵ�
			boolean isSelect = s.execute(sqlSelect);
			System.out.println(isSelect);
			
			//executeUpdate���ص���int����ʾ�ж����������ܵ���Ӱ��
			String sqlUpdate = "update Hero set hp = 300 where id < 100";
			int number = s.executeUpdate(sqlUpdate);
			System.out.println(number);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
