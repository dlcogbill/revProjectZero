package com.revature.controllers;

import com.revature.models.Hero;
import com.revature.service.HeroService;
import io.javalin.http.Context;

import java.util.ArrayList;

public class HeroController {

    private static final HeroService heroService = new HeroService();

    public static void handleGetAll(Context ctx){
        ArrayList<Hero> heroes = heroService.getAllHeroes();
        ctx.status(200);
        ctx.json(heroes);
    }

    public static void handleGetOne(Context ctx){

        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in their path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }

        Hero hero = heroService.getHeroById(id);
        if (hero != null){
            // This is good, it found the roll
            ctx.status(200);
            ctx.json(hero);
        } else{
            ctx.status(404);
        }
    }

    public static void handleCreate(Context ctx){
        Hero hero = ctx.bodyAsClass(Hero.class);
        Hero returnedHero = heroService.insertHero(hero);

        if(returnedHero != null){
            ctx.status(201);
            ctx.json(returnedHero);
        } else {
            ctx.status(400);
        }
    }

    public static void handleDelete(Context ctx){

        int id;
        try{
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in their path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }


        if ( heroService.removeHero(id) ){
            // This is good, it found the hero to be deleted
            ctx.status(200);
        } else{
            ctx.status(404);
        }

    }

    public static void handleUpdate(Context ctx){

        Hero hero = ctx.bodyAsClass(Hero.class);
        Hero returnedHero = heroService.updateHero(hero);

        if(returnedHero != null){
            ctx.status(201);
            ctx.json(returnedHero);
        } else {
            ctx.status(400);
        }
    }
}
