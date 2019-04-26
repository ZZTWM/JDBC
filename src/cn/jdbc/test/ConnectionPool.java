package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 连接池
 * @author Administrator
 *
 */
public class ConnectionPool {
	List<Connection> cs = new ArrayList<Connection>();
	
	int size;
	/**
	 * 1、ConnectionPool() 构造方法约定了这个连接池一共有多少连接
	 * @param size
	 */
	public ConnectionPool(int size){
		this.size = size;
		init();
	}
	
	/**
	 * 2、在init() 初始化方法中，创建了size条连接。 
	 * 	注意，这里不能使用try-with-resource这种自动关闭连接的方式，
	 * 	因为连接恰恰需要保持不关闭状态，供后续循环使用
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
	 * 3、getConnection， 判断是否为空，如果是空的就wait等待，否则就借用一条连接出去
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
	 * 4、returnConnection， 在使用完毕后，归还这个连接到连接池，
	 * 		并且在归还完毕后，调用notifyAll，通知那些等待的线程，有新的连接可以借用了。
	 * @param c
	 */
	public synchronized void returnConnection(Connection c){
		cs.add(c);
		this.notifyAll();
	}
	
}
