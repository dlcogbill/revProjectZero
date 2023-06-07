package com.revature;

import com.revature.daos.HeroDAO;
import com.revature.daos.VillainDAO;
import com.revature.models.Hero;
import com.revature.models.Villain;
import com.revature.util.JavalinAppConfig;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        // Create a new instance of the Javalin Config class
        JavalinAppConfig app = new JavalinAppConfig();

        // Start the app with app.start
        app.start(7070);
    }
}
