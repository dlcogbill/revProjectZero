package com.revature.service;

import com.revature.daos.HeroDAO;
import com.revature.daos.HeroDAOInterface;
import com.revature.models.Hero;

import java.util.ArrayList;
import java.util.Objects;

public class HeroService {

    private final HeroDAOInterface heroDAO = new HeroDAO();

    public ArrayList<Hero> getAllHeroes() { return heroDAO.getAllHeroes(); }
    public Hero getHeroById(int id){

        //Validate the id is > 0
        if (id > 0){
            return heroDAO.getHeroById(id);
        }

        //return null to the controller and appropriately respond
        return null;
    }

    public Hero insertHero(Hero h){
        return heroDAO.insertHero(h);
    }

    public Boolean removeHero(int id) {

        //Validate the id is > 0
        if (id > 0){
            return heroDAO.removeHero(id);
        }
        return false;
    }

    public Hero updateHero(Hero hero) {

        if (hero.getHero_name() == null || hero.getHero_weapon() == null || hero.getHero_name().equals("") || hero.getHero_weapon().equals("") || hero.getHero_id() <= 0 ){
            return null;
        }
        return heroDAO.updateHero(hero);
    }

}
