package com.revature.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controllers.HeroController;
import com.revature.controllers.MovieController;
import com.revature.controllers.VillainController;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinAppConfig {

    Gson gson = new GsonBuilder().create();

    JsonMapper gsonMapper = new JsonMapper() {
        @Override
        public String toJsonString(@NotNull Object obj, @NotNull Type type) {
            return gson.toJson(obj, type);
        }

        @Override
        public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
            return gson.fromJson(json, targetType);
        }
    };

    private Javalin app = Javalin.create(config -> config.jsonMapper(gsonMapper))
            .routes(() ->{
                path("/", () -> { get(MovieController::handleMapAll); });
                path("heroes", () ->{
                    get(HeroController::handleGetAll);
                    post(HeroController::handleCreate);
                    put(HeroController::handleUpdate);
                    path("{id}", () ->{
                        get(HeroController::handleGetOne);
                        delete(HeroController::handleDelete);
                    });
                });
                path("villains", () ->{
                    get(VillainController::handleGetAll);
                    post(VillainController::handleCreate);
                    put(VillainController::handleUpdate);
                    path("{id}", () -> {
                        get(VillainController::handleGetOne);
                        delete(VillainController::handleDelete);
                    });
                });
                path("movies", () ->{
                    get(MovieController::handleGetAll);
                    post(MovieController::handleCreate);
                    put(MovieController::handleUpdate);
                    path("{id}", () -> {
                        get(MovieController::handleGetOne);
                        delete(MovieController::handleDelete);
                    });
                });
            });

    public void start(int port){
        app.start(port);
    }


}
