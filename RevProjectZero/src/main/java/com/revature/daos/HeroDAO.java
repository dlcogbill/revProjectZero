package com.revature.daos;

import com.revature.models.Hero;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HeroDAO implements HeroDAOInterface{
    @Override
    public ArrayList<Hero> getAllHeroes() {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM heroes";

            Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(sql);

            ArrayList<Hero> heroList = new ArrayList<>();

            while(rs.next()){

                Hero hero = new Hero(
                        rs.getInt("hero_id"),
                        rs.getString("hero_name"),
                        rs.getString("hero_weapon")
                );

                heroList.add(hero);
            }

            return heroList;

        } catch(SQLException e){
            System.out.println("Failed to get all heroes");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Hero getHeroById(int id) {
        return null;
    }

    @Override
    public Hero insertHero(Hero hero) {
        return null;
    }
}
