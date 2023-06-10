package com.revature.daos;

import com.revature.models.Hero;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class HeroDAO implements HeroDAOInterface{
    @Override
    public ArrayList<Hero> getAllHeroes() {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to retreive all heroes on DB
            String sql = "SELECT * FROM heroes";

            //Statement because there are no variables to pass
            Statement s = conn.createStatement();

            //Execute query and save as result set
            ResultSet rs = s.executeQuery(sql);

            //Prepare empty array to hold data from DB
            ArrayList<Hero> heroList = new ArrayList<>();

            //Iterate through the results from DB until there are none
            while(rs.next()){

                //Create hero object from DB results
                Hero hero = new Hero(
                        rs.getInt("hero_id"),
                        rs.getString("hero_name"),
                        rs.getString("hero_weapon")
                );

                //Add newly created object to array
                heroList.add(hero);
            }

            //return array to service
            return heroList;

        } catch(SQLException e){
            System.out.println("Failed to get all heroes");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Hero getHeroById(int id) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to retrieve one hero by id from DB
            String sql = "SELECT * FROM heroes WHERE hero_id = ?";

            //Use a prepared Statement to pass requested hero_id to the db
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            //Execute query and save as result set
            ResultSet rs = ps.executeQuery();

            //if there is data in the result set
            if ( rs.next() ){

                //Create a new hero object from result set
                Hero hero = new Hero(
                        rs.getInt("hero_id"),
                        rs.getString("hero_name"),
                        rs.getString("hero_weapon")
                );

                //return hero to service
                return hero;
            }

        }
        catch(SQLException e){
            System.out.println("Failed getting Hero!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Hero insertHero(Hero hero) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to create new hero on DB
            String sql = "INSERT INTO heroes (hero_name, hero_weapon) VALUES (?, ?)";

            //Use a prepared Statement to pass new hero variables to the DB then Execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hero.getHero_name());
            ps.setString(2, hero.getHero_weapon());
            ps.executeUpdate();

            //Return created hero
            return hero;

        } catch(SQLException e){
            System.out.println("Insert Hero failed!");
            e.printStackTrace();//detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Boolean removeHero(int id) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to remove hero from DB
            String sql = "DELETE FROM heroes WHERE hero_id = ?";

            //Use a prepared Statement to pass provided hero_id to the DB then execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Hero has been removed");
            return true;

        } catch(SQLException e){
            System.out.println("Remove hero failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }

        return false;
    }

    @Override
    public Hero updateHero(Hero hero) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to update hero in DB
            String sql = "UPDATE heroes SET hero_name = ?, hero_weapon = ? WHERE hero_id = ?";

            //Use a prepared Statement to pass provided hero_id, hero_name, hero_weapon to the DB then execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hero.getHero_name());
            ps.setString(2, hero.getHero_weapon());
            ps.setInt(3, hero.getHero_id());
            ps.executeUpdate();

            //return provided hero to service
            return hero;

        } catch(SQLException e){
            System.out.println("Update hero failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }

        return null;
    }

}
