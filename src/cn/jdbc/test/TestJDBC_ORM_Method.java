package cn.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.jdbc.bean.Hero;

/**
 * ���ֳ�����ORM������
 * 
 * @author Administrator
 *
 */
public class TestJDBC_ORM_Method {
	/**
	 * 1������id����һ��Hero����
	 * @param id
	 * @return
	 */
	public static Hero get(int id){
		Hero hero = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8","root", "admin");
	            Statement s = c.createStatement();)
		{
			String sql = "select * from hero where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()){
				hero = new Hero();
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				hero.name = name;
				hero.hp = hp;
				hero.id = id;
				hero.damage = damage;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hero;
	}
	
	/**
	 * 2����һ��Hero������뵽���ݿ���
	 * @param h
	 */
	public static void add(Hero h){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "insert into hero values(null,?,?,?)";
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin");
				PreparedStatement ps = c.prepareStatement(sql);)
		{
			ps.setString(1, h.name);
			ps.setFloat(2, h.hp);
			ps.setInt(3, h.damage);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3�������Hero�����Ӧ������ɾ����
	 * @param h
	 */
	public static void delete(Hero h){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "delete from hero where id = ?";
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin");
   
                PreparedStatement ps = c.prepareStatement(sql);)
		{
			//���ò���
			ps.setInt(1, h.id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 4����������Hero����
	 * @param args
	 */
	public static void update(Hero h){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String sql = "update hero set name = ? , hp =? , damage = ? where id = ?";
		try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin");
   
                PreparedStatement ps = c.prepareStatement(sql);)
		{
			//���ò���
			ps.setString(1, h.name);
            ps.setFloat(2, h.hp);
            ps.setInt(3, h.damage);
            ps.setInt(4, h.id);
   
            ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 5�������е�Hero���ݲ�ѯ������ת��ΪHero����󣬷���һ�������з���
	 * @param args
	 */
	public static List<Hero> list(){
		List<Hero> heros = new ArrayList<>();
		
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        try(Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8",
                "root", "admin"); Statement s = c.createStatement();)
        {
			String sql = "Select * from hero";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				Hero hero = new Hero();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
				hero.id = id;
				heros.add(hero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		return heros;
	}
	public static void main(String[] args) {
		Hero h = get(22);
		System.out.println(h);
		
		List<Hero> hs = list();
		System.out.println(hs.size());
	}
	
}
