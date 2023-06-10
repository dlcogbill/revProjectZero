package com.revature.daos;

import com.revature.models.Villain;

import java.util.ArrayList;

public interface VillainDAOInterface {

    //Method to select all villains
    ArrayList<Villain> getAllVillains();

    //Method to get one villain by id
    Villain getVillainById(int id);

    //Method to insert villain
    Villain insertVillain(Villain villain);

    //Method to remove Villain
    Boolean removeVillain(int id);

    //Method to update villain
    Villain updateVillain(Villain villain);
}
