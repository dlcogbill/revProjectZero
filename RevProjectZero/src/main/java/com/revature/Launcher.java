package com.revature;

import com.revature.daos.HeroDAO;
import com.revature.models.Hero;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        HeroDAO heroDAO = new HeroDAO();
        for(Hero hero : heroDAO.getAllHeroes()){
            System.out.println(hero);
        }

        List<String> names = new ArrayList<>();
        names.add("Ali");
        names.add("David");
        names.add("Rafael");
        names.add("Leon the Professional");

        var app = Javalin.create(/*config*/)
                .get("/", ctx -> {
                    String response = "";
                    for(String s : names){
                        response += s + "\n";
                    }
                    ctx.result(response);
                })
                .post("/", ctx -> {
                    String body = ctx.body();
                    names.add(body);
                    ctx.result("Name successfully added");
                    ctx.status(201);
                })
                .put("/sample-put", ctx -> {})
                .delete("/", ctx -> {
                    String name = ctx.body();
                    if(names.contains(name)){
                        names.remove(name);
                        ctx.status(200);
                        ctx.result("Successully deleted: " + name);
                    } else {
                        ctx.status(400);
                        ctx.result("Name is not in list.");
                    }
                })
                .start(7070);
    }
}
