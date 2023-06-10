package com.revature.service;

import com.revature.daos.VillainDAO;
import com.revature.daos.VillainDAOInterface;
import com.revature.models.Villain;

import java.util.ArrayList;

public class VillainService {

    // Create instance of Villain DAO to use in service
    // Follows Interface name = new Implementation()
    private final VillainDAOInterface villainDAO = new VillainDAO();

    //Method to get all villains
    public ArrayList<Villain> getAllVillains() { return villainDAO.getAllVillains(); }

    //Method to get one villain by id
    //validate provided villain_id
    public Villain getVillainById(int id){

        //Validate the id is > 0
        if (id > 0){
            return villainDAO.getVillainById(id);
        }

        //return null to the controller and appropriately respond
        return null;
    }

    //Method to create new Villain
    //validate provided villain
    public Villain insertVillain(Villain villain){

        //Ensure villain_name and villain_weapon are not null or empty strings
        if (villain.getVillain_name() == null || villain.getVillain_weapon() == null || villain.getVillain_name().equals("") || villain.getVillain_weapon().equals("") ){
            return null;
        }
        //return results from DAO insert method and respond accordingly in controller
        return villainDAO.insertVillain(villain);
    }

    //Method to remove Villain, returns true if villain was removed
    //validate provided villain_id
    public Boolean removeVillain(int id) {

        //Validate the id is > 0
        if (id > 0){
            //will return true if villain is removed, respond accordingly in controller
            return villainDAO.removeVillain(id);
        }
        return false;
    }

    //method to update existing villain
    //validate provided villain
    public Villain updateVillain(Villain villain) {

        //validate villain_name and villain_weapon are not null or empty strings and villain_id is greater 0
        if ( villain.getVillain_name() == null || villain.getVillain_weapon() == null || villain.getVillain_name().equals("") || villain.getVillain_weapon().equals("")  || villain.getVillain_id() <= 0 ){
            return null;
        }
        //return update method from DAO and
        return villainDAO.updateVillain(villain);
    }
}
