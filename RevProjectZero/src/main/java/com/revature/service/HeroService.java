package com.revature.service;

import com.revature.daos.HeroDAO;
import com.revature.daos.HeroDAOInterface;
import com.revature.models.Hero;

import java.util.ArrayList;
import java.util.Objects;

public class HeroService {

    // Create instance of Hero DAO to use in service
    // Follows Interface name = new Implementation()
    private HeroDAOInterface heroDAO;

    public HeroService(){}
    public HeroService(HeroDAOInterface heroDAO){
        this.heroDAO = heroDAO;
    }


    //Method to get all heroes
    public ArrayList<Hero> getAllHeroes() { return heroDAO.getAllHeroes(); }

    //Method to get one hero by id
    //validate provided hero_id
    public Hero getHeroById(int id){

        //Validate the id is > 0
        if (id > 0){
            return heroDAO.getHeroById(id);
        }

        //return null to the controller and appropriately respond
        return null;
    }

    //Method to create new Hero
    //validate provided hero
    public Hero insertHero(Hero hero){

        //Ensure hero_name and hero_weapon are not null or empty strings
        if (hero.getHero_name() == null || hero.getHero_weapon() == null || hero.getHero_name().equals("") || hero.getHero_weapon().equals("") ){
            return null;
        }
        //return results from DAO insert method and respond accordingly in controller
        return heroDAO.insertHero(hero);
    }

    //Method to remove Hero, returns true if hero was removed
    //validate provided hero_id
    public Boolean removeHero(int id) {

        //Validate the id is > 0
        if (id > 0){
            //will return true if hero is removed, respond accordingly in controller
            return heroDAO.removeHero(id);
        }
        return false;
    }

    //method to update existing hero
    //validate provided hero
    public Hero updateHero(Hero hero) {

        //validate hero_name and hero_weapon are not null or empty strings and hero_id is greater 0
        if ( hero.getHero_name() == null || hero.getHero_weapon() == null || hero.getHero_name().equals("") || hero.getHero_weapon().equals("")  || hero.getHero_id() <= 0 ){
            return null;
        }
        //return update method from DAO and
        return heroDAO.updateHero(hero);
    }

}
