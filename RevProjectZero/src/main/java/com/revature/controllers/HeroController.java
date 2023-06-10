package com.revature.controllers;

import com.revature.models.Hero;
import com.revature.service.HeroService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class HeroController {

    private static final HeroService heroService = new HeroService();
    private static final Logger logger = LoggerFactory.getLogger(HeroController.class);

    public static void handleGetAll(Context ctx){
        //Use Hero service to get all heroes
        ArrayList<Hero> heroes = heroService.getAllHeroes();

        //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
        ctx.status(200);
        ctx.json(heroes);
    }

    public static void handleGetOne(Context ctx){

        //empty integer to hold path parameter
        int id;
        try{
            //parse path parameter to integer
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in the path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }

        //Call Hero service to get hero
        Hero hero = heroService.getHeroById(id);

        if (hero != null){
            // Hero found
            //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(200);
            ctx.json(hero);
            logger.info("The following hero was found: " + hero);
        } else{
            //Hero not found, bad status
            ctx.status(404);
            logger.warn("Get one Hero failed");
        }
    }

    public static void handleCreate(Context ctx){

        //Context body will come in JSON, and must be converted to Java object
        Hero hero = ctx.bodyAsClass(Hero.class);

        //Call Hero service to create hero
        Hero returnedHero = heroService.insertHero(hero);

        if(returnedHero != null){
            //Hero was created
            //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(201);
            ctx.json(returnedHero);
            logger.info("The following villain was created: " + returnedHero);
        } else {
            //Hero not created, bad status
            ctx.status(400);
            logger.warn("Hero creation failed");
        }
    }

    public static void handleDelete(Context ctx){

        //empty integer to hold path parameter
        int id;
        try{
            //parse path parameter to integer
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in their path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }


        if ( heroService.removeHero(id) ){
            // This is good, it found the hero to be deleted
            //Use the Context to send the status response to http request
            ctx.status(200);
            logger.info("Hero was removed.");
        } else{
            //Hero not removed, bad status
            ctx.status(404);
            logger.warn("Hero removal failed");
        }

    }

    public static void handleUpdate(Context ctx){

        //Context body will come in JSON, and must be converted to Java object
        Hero hero = ctx.bodyAsClass(Hero.class);

        //Call Hero service to update hero
        Hero returnedHero = heroService.updateHero(hero);

        if(returnedHero != null){
            //Hero was updated
            //Use the Context to send the status and json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(201);
            ctx.json(returnedHero);
            logger.info("The following villain was updated: " + returnedHero);
        } else {
            //Hero not updated, bad status
            ctx.status(400);
            logger.warn("Hero update failed");
        }
    }
}
