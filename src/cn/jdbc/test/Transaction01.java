package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * ��ʹ������
 * û�������ǰ����
	����ҵ������ǣ���Ѫ����Ѫ����һ��
	������Ӣ�۵�Ѫ������
	����Ѫ��SQL
	��С��д��д���� updata(����update)
	��ô�������Ѫ�������ˣ����������Ĳ���
 * @author Administrator
 *
 */
public class Transaction01 {
	
	public static void main(String[] args) {
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
                Statement s = c.createStatement();)
        {
            //û�������ǰ����
            //����ҵ�����ʱ����Ѫ����Ѫ����һ��
            //������Ӣ�۵�Ѫ������
            //��Ѫ��SQL
            String sql1 = "update hero set hp = hp +1 where id = 22";
            s.execute(sql1);
            
            //��Ѫ��SQL
            //��С��д��д���� updata(����update)
           
            //�������б�����Ϊupdate��д��updata��
            String sql2 = "updata hero set hp = hp -1 where id = 22";
            s.execute(sql2);
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}
	
}
