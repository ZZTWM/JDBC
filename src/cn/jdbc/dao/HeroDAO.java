package cn.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.jdbc.bean.Hero;

public class HeroDAO implements DAO{
	
	/**
	 *  把驱动的初始化放在了构造方法HeroDAO里
	 */
	public HeroDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 *  提供了一个getConnection方法返回连接
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", "root",
                "admin");
		
	}
	
	/**
	 * 1、获取总数
	 * @return
	 */
	public int getTotal(){
		int total = 0;
		try (
				Connection c = getConnection();
				Statement s = c.createStatement();
			)
		{
			String sql = "select count(*) from hero";
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				total = rs.getInt(1);
			}
			
			System.out.println("total:" + total);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return total;
	}
	
	/**
	 * 2、增：把一个Hero对象插入到数据库中
	 */
	@Override
	public void add(Hero hero) {
		String sql = "insert into hero values(null,?,?,?)";
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);)
		{
			ps.setString(1, hero.name);
			ps.setFloat(2, hero.hp);
			ps.setInt(3, hero.damage);
			
			// 执行插入语句
			ps.execute();
			
            // 在执行完插入语句后，MySQL会为新插入的数据分配一个自增长id
            // JDBC通过getGeneratedKeys获取该id
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				int id = rs.getInt(1);
				hero.id = id;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 3、改：更新这条Hero对象
	 */
	@Override
	public void update(Hero hero) {
		String sql = "update hero set name= ?, hp = ? , damage = ? where id = ?";
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);)
		{
			ps.setString(1, hero.name);
			ps.setFloat(2, hero.hp);
			ps.setInt(3, hero.damage);
			ps.setInt(4, hero.id);
			
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 4、删：把这个Hero对象对应的数据删除掉
	 */
	@Override
	public void delete(int id) {
		try(Connection c = getConnection();Statement s = c.createStatement();)
		{
			String sql = "delete from hero where id = " + id;
			s.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 5、查：根据id查找对象
	 */
	@Override
	public Hero get(int id) {
		Hero hero = null;
		try(Connection c = getConnection(); Statement s = c.createStatement();)
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
				hero.damage = damage;
				hero.id = id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hero;
	}
	
	/**
	 * 查询
	 */
	@Override
	public List<Hero> list() {
		return list(0,Short.MAX_VALUE);
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public List<Hero> list(int start, int count) {
		List<Hero> heros = new ArrayList<Hero>();
		String sql = "select * from hero order by id desc limit ?,? ";
		
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);)
		{
			ps.setInt(1, start);
			ps.setInt(2, count);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Hero hero = new Hero();
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float hp = rs.getFloat("hp");
				int damage = rs.getInt(4);
				
				hero.id = id;
				hero.name = name;
				hero.hp = hp;
				hero.damage = damage;
				heros.add(hero);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return heros;
	}

}
