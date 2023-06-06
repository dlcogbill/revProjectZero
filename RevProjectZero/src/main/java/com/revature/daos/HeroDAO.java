package com.revature.daos;

import com.revature.models.Hero;
import com.revature.util.ConnectionUtil;

import java.sql.*;
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
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM heroes WHERE hero_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if ( rs.next() ){
                Hero hero = new Hero(
                        rs.getInt("hero_id"),
                        rs.getString("hero_name"),
                        rs.getString("hero_weapon")
                );
                System.out.println(hero);
                return hero;
            }

        }
        catch(SQLException e){
            System.out.println("Error getting Hero!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Hero insertHero(Hero hero) {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO heroes (hero_name, hero_weapon) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, hero.getHero_name());
            ps.setString(2, hero.getHero_weapon());

            ps.executeUpdate();

            return hero;

        } catch(SQLException e){
            System.out.println("Insert Hero failed!");
            e.printStackTrace();
        }
        return null;
    }
}
