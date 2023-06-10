package com.revature.daos;

import com.revature.models.Villain;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class VillainDAO implements VillainDAOInterface{
    @Override
    public ArrayList<Villain> getAllVillains() {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to retrieve all villains on DB
            String sql = "SELECT * FROM villains";

            //Statement because there are no variables to pass
            Statement s = conn.createStatement();

            //Execute query and save as result set
            ResultSet rs = s.executeQuery(sql);

            //Prepare empty array to hold data from DB
            ArrayList<Villain> villainList = new ArrayList<>();

            //Iterate through the results from DB until there are none
            while(rs.next()){

                //Create villain object from DB results
                Villain villain = new Villain(
                        rs.getInt("villain_id"),
                        rs.getString("villain_name"),
                        rs.getString("villain_weapon")
                );

                //Add newly created object to array
                villainList.add(villain);
            }

            //return array to service
            return villainList;

        } catch(SQLException e){
            System.out.println("Failed to get all villains");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Villain getVillainById(int id) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to retrieve one villain by id from DB
            String sql = "SELECT * FROM villains WHERE villain_id = ?";

            //Use a prepared Statement to pass requested villain_id to the db
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            //Execute query and save as result set
            ResultSet rs = ps.executeQuery();

            //if there is data in the result set
            if ( rs.next() ){

                //Create a new villain object from result set
                Villain villain = new Villain(
                        rs.getInt("villain_id"),
                        rs.getString("villain_name"),
                        rs.getString("villain_weapon")
                );

                //return villain to service
                return villain;
            }
        }
        catch(SQLException e){
            System.out.println("Error getting Villain!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Villain insertVillain(Villain villain) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to create new villain on DB
            String sql = "INSERT INTO villains (villain_name, villain_weapon) VALUES (?, ?)";

            //Use a prepared Statement to pass new villain variables to the DB then Execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, villain.getVillain_name());
            ps.setString(2, villain.getVillain_weapon());
            ps.executeUpdate();

            //Return created villain
            return villain;
            
        } catch(SQLException e){
            System.out.println("Insert villain failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Boolean removeVillain(int id) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to remove villain from DB
            String sql = "DELETE FROM villains WHERE villain_id = ?";

            //Use a prepared Statement to pass provided villain_id to the DB then execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Villain has been removed");
            return true;
            
        } catch(SQLException e){
            System.out.println("Remove villain failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return false;
    }

    @Override
    public Villain updateVillain(Villain villain) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to update villain in DB
            String sql = "UPDATE villains SET villain_name = ?, villain_weapon = ? WHERE villain_id = ?";

            //Use a prepared Statement to pass provided villain_id, villain_name, villain_weapon to the DB then execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, villain.getVillain_name());
            ps.setString(2, villain.getVillain_weapon());
            ps.setInt(3, villain.getVillain_id());
            ps.executeUpdate();

            //return provided villain to service
            return villain;
            
        } catch(SQLException e){
            System.out.println("Update villain failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }
}
