package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 不使用事务
 * 没有事务的前提下
	假设业务操作是：加血，减血各做一次
	结束后，英雄的血量不变
	而减血的SQL
	不小心写错写成了 updata(而非update)
	那么最后结果是血量增加了，而非期望的不变
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
            //没有事务的前提下
            //假设业务操作时，加血，减血各做一次
            //结束后，英雄的血量不变
            //加血的SQL
            String sql1 = "update hero set hp = hp +1 where id = 22";
            s.execute(sql1);
            
            //减血的SQL
            //不小心写错写成了 updata(而非update)
           
            //这里运行报错：因为update被写成updata了
            String sql2 = "updata hero set hp = hp -1 where id = 22";
            s.execute(sql2);
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
	}
	
}
