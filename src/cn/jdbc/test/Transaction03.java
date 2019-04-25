package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 练习：事务
 * 当c.setAutoCommit(false);时，事务是不会提交的
	只有执行使用 c.commit(); 才会提交进行
	
	设计一个代码，删除表中前10条数据，但是删除前会在控制台弹出一个提示：
	是否要删除数据(Y/N)
	如果用户输入Y，则删除
	如果输入N则不删除。
	如果输入的既不是Y也不是N，则重复提示
 * @author Administrator
 *
 */
public class Transaction03 {
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin");
                Statement st4Query = c.createStatement();
                Statement st4Delete = c.createStatement();
				Scanner s = new Scanner(System.in);
		   )
		{
			//把自动提交关闭
			c.setAutoCommit(false);
			//查出前10条
			ResultSet rs = st4Query.executeQuery("select id from Hero order by id asc limit 0,10 ");
			while(rs.next()){
				int id = rs.getInt(1);
				System.out.println("试图删除id=" + id + "的数据");
				st4Delete.execute("delete from Hero where id = " +id);
			}
			//是否删除这10条
			while(true){
				System.out.println("是否要删除数据(Y/N)");
				String str = s.next();
				if("Y".equals(str)){
					//如果输入的是Y,则提交删除操作
					c.commit();
					System.out.println("提交删除");
					break;
				}else if("N".equals(str)){
					System.out.println("放弃删除");
					break;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
