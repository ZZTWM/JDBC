package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class TestJDBC {

	public static void main(String[] args) {
		Connection c = null;
		Statement s = null;
		/**
		 * 1����ʼ������
		 */
		try {
            //������com.mysql.jdbc.Driver
            //���� mysql-connector-java-5.0.8-bin.jar��
            //��������˵�һ������ĵ������ͻ��׳�ClassNotFoundException
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("���ݿ��������سɹ�");
		/**
		 * 2�����������ݿ������
		 */
	        // ���������ݿ��Connection����
	        // ������Ҫ�ṩ��
	        // ���ݿ������ڵ�ip:127.0.0.1 (����)
	        // ���ݿ�Ķ˿ںţ� 3306 ��mysqlר�ö˿ںţ�
	        // ���ݿ����� how2java
	        // ���뷽ʽ UTF-8
	        // �˺� root
	        // ���� admin
			
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root","admin");
			System.out.println("���ӳɹ�����ȡ���Ӷ���" + c);
			
		/**
		 * 3������Statement
		 */
			//Statement������ִ��SQL���ģ��������ӣ�ɾ��
			s = c.createStatement();
			System.out.println("��ȡStatement����" + s);
		/**
		 * 4��ִ��SQL���	
		 */
			//s.executeִ��sql���
			String sql = "insert into hero values(null,"+"'��Ī'"+","+313.0f+","+50+")";
			s.execute(sql);
			System.out.println("ִ�в������ɹ�");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//���ݿ������ʱ������Դ����ز������������ɹر����ݿ�ĺ�ϰ��
			//�ȹر�Statement
			if(s != null){
				try {
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//��ر�Connection
			if(c != null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
