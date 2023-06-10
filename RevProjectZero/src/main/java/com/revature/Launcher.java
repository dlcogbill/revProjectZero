package com.revature;

import com.revature.util.JavalinAppConfig;

public class Launcher {
    public static void main(String[] args) {
        // Create a new instance of the Javalin Config class
        JavalinAppConfig app = new JavalinAppConfig();

        // Start the app with app.start
        app.start(7070);
    }
}
