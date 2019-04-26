package cn.jdbc.test;

import java.util.List;

import cn.jdbc.bean.Hero;
import cn.jdbc.dao.HeroDAO;

public class TestHeroDAO {

	public static void main(String[] args) {
		HeroDAO heroDAO = new HeroDAO();
		List<Hero> hs = heroDAO.list();
		
		for (Hero hero : hs) {
			System.out.println(hero);
		}
		
		System.out.println(hs.size());
		
	}

}
