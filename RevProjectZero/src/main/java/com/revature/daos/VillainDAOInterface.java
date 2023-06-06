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
}
