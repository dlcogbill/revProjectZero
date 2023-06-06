package com.revature.daos;

import com.revature.models.Villain;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class VillainDAO implements VillainDAOInterface{
    @Override
    public ArrayList<Villain> getAllVillains() {
        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM villains";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            ArrayList<Villain> villainList = new ArrayList<>();

            while(rs.next()){

                Villain villain = new Villain(
                        rs.getInt("villain_id"),
                        rs.getString("villain_name"),
                        rs.getString("villain_weapon")
                );

                villainList.add(villain);
            }

            return villainList;

        } catch(SQLException e){
            System.out.println("Failed to get all villains");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Villain getVillainById(int id) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "SELECT * FROM villains WHERE villain_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ){
                Villain villain = new Villain(
                        rs.getInt("villain_id"),
                        rs.getString("villain_name"),
                        rs.getString("villain_weapon")
                );
                return villain;
            }
        }
        catch(SQLException e){
            System.out.println("Error getting Villain!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Villain insertVillain(Villain villain) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "INSERT INTO villains (villain_name, villain_weapon) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, villain.getVillain_name());
            ps.setString(2, villain.getVillain_weapon());

            ps.executeUpdate();

            return villain;
            
        } catch(SQLException e){
            System.out.println("Insert villain failed!");
            e.printStackTrace();
        }
        return null;
    }
}
