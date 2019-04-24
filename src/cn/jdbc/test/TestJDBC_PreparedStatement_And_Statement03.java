package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class TestJDBC_PreparedStatement_And_Statement03 {
	
	public static void main(String[] args) {
		/**
		 * ��ʼ������
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		/**
		 * ���������ݿ������
		 * ����PreparedStatement
		 */
		String sql = "select * from hero where name = ?";
		try(
			Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			Statement s = c.createStatement();
			//����sql��䴴��PreparedStatement
			PreparedStatement ps = c.prepareStatement(sql);
			)
		{
            // ����name���û��ύ��������
            String name = "'����' OR 1=1";
            String sql0 = "select * from hero where name = " + name;
            // ƴ�ӳ�����SQL������
            // select * from hero where name = '����' OR 1=1
            // ��Ϊ��OR 1=1�����Ժ����
            // ��ô�ͻ�����е�Ӣ�۶������������ֻ�Ǹ���
            // ���Hero����������Ǻ����ģ����缸����������������������ȫ�������
            // �������ݿ⸺�ر�ߣ�CPU100%���ڴ����Ĺ⣬��Ӧ��ü��仺��
            System.out.println(sql0);
            ResultSet rs0 = s.executeQuery(sql0);
            while (rs0.next()) {
                String heroName = rs0.getString("name");
                System.out.println(heroName);
            }
            
            System.out.println("-----------------------------");
            
            //ʹ��Ԥ����Statement�Ϳ��Զž�SQLע��
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            //�鲻�����ݳ���
            while (rs.next()) {
                String heroName = rs.getString("name");
                System.out.println(heroName);
            }
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	
}














