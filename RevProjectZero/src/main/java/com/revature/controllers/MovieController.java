package com.revature.controllers;

import com.revature.models.Hero;
import com.revature.models.Movie;
import com.revature.service.MovieService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class MovieController {

    private static final MovieService movieService = new MovieService();
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


    public static void handleGetAll(Context ctx){

        //Use movie service to get all movies
        ArrayList<Movie> movies = movieService.getAllMovies();

        //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
        ctx.status(200);
        ctx.json(movies);
    }

    public static void handleMapAll(Context ctx){

        //Use movie service to get all movies
        ArrayList<Movie> movies = movieService.mapAllMovies();

        //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
        ctx.status(200);
        ctx.json(movies);
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

        //Call movie service to get movie
        Movie movie = movieService.getMovieById(id);
        if (movie != null){
            // Hero found
            //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(200);
            ctx.json(movie);
            logger.info("The following villain was found: " + movie);
        } else{
            //Hero not found, bad status
            ctx.status(404);
            logger.warn("Get one Movie failed");
        }
    }

    public static void handleCreate(Context ctx){

        //Context body will come in JSON, and must be converted to Java object
        Movie movie = ctx.bodyAsClass(Movie.class);

        //Call Hero service to create hero
        Movie returnedMovie = movieService.insertMovie(movie);

        if(returnedMovie != null){
            //Movie was created
            //Use the Context to send the status
            //Use the json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(201);
            ctx.json(returnedMovie);
            logger.info("The following villain was created: " + movie);
        } else {
            //Movie not created, bad status
            ctx.status(400);
            logger.warn("Movie creation failed");
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


        if ( movieService.removeMovie(id) ){
            //Movie deleted successfully
            //Use the Context to send the status response to http request
            ctx.status(200);
            logger.info("Movie was removed");
        } else{
            //Movie not removed, bad status
            ctx.status(404);
            logger.warn("Movie removal failed");
        }

    }

    public static void handleUpdate(Context ctx){

        //Context body will come in JSON, and must be converted to Java object
        Movie movie = ctx.bodyAsClass(Movie.class);

        //Call Hero service to update hero
        Movie returnedMovie = movieService.updateMovie(movie);

        if(returnedMovie != null){
            //Movie was updated successfully
            //Use the Context to send the status and json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(201);
            ctx.json(returnedMovie);
            logger.info("The following movie was updated: " + movie);
        } else {
            //Movie not updated, bad status
            ctx.status(400);
            logger.warn("Movie update failed");
        }
    }
}
