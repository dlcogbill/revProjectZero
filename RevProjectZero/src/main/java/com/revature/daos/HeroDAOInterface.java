package com.revature.daos;

import com.revature.models.Hero;

import java.util.ArrayList;

public interface HeroDAOInterface {

    //Method to select all heroes
    ArrayList<Hero> getAllHeroes();

    //Method to get one hero by id
    Hero getHeroById(int id);

    //Method to insert hero
    Hero insertHero(Hero hero);

    //Method to remove Hero
    Boolean removeHero(int id);

    //Method to update hero
    Hero updateHero(Hero hero);
}
