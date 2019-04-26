package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ���ӳ�
 * @author Administrator
 *
 */
public class ConnectionPool {
	List<Connection> cs = new ArrayList<Connection>();
	
	int size;
	/**
	 * 1��ConnectionPool() ���췽��Լ����������ӳ�һ���ж�������
	 * @param size
	 */
	public ConnectionPool(int size){
		this.size = size;
		init();
	}
	
	/**
	 * 2����init() ��ʼ�������У�������size�����ӡ� 
	 * 	ע�⣬���ﲻ��ʹ��try-with-resource�����Զ��ر����ӵķ�ʽ��
	 * 	��Ϊ����ǡǡ��Ҫ���ֲ��ر�״̬��������ѭ��ʹ��
	 */
	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			for (int i = 0; i < size; i++) {
				 Connection c = DriverManager
	                        .getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root", "admin");
				 cs.add(c);
				 
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3��getConnection�� �ж��Ƿ�Ϊ�գ�����ǿյľ�wait�ȴ�������ͽ���һ�����ӳ�ȥ
	 * @return
	 */
	public synchronized Connection getConnection(){
		while(cs.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Connection c = cs.remove(0);
		return c;
	}
	
	/**
	 * 4��returnConnection�� ��ʹ����Ϻ󣬹黹������ӵ����ӳأ�
	 * 		�����ڹ黹��Ϻ󣬵���notifyAll��֪ͨ��Щ�ȴ����̣߳����µ����ӿ��Խ����ˡ�
	 * @param c
	 */
	public synchronized void returnConnection(Connection c){
		cs.add(c);
		this.notifyAll();
	}
	
}
