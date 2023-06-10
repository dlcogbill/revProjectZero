package com.revature.service;

import com.revature.daos.*;
import com.revature.models.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    private final MovieDAOInterface movieDAO = new MovieDAO();
    private final HeroDAOInterface heroDAO = new HeroDAO();
    private final VillainDAOInterface villainDAO = new VillainDAO();

    //Method to get all movies
    public ArrayList<Movie> getAllMovies() { return movieDAO.getAllMovies(); }

    //map foreign keys to all movies
    public ArrayList<Movie> mapAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();

        for(Movie m : movieDAO.getAllMovies() ){

            Movie mappedMovie = new Movie(
                    m.getMovie_id(),
                    m.getMovie_name(),
                    m.getMovie_release_year(),
                    m.getHero_id_fk(),
                    heroDAO.getHeroById(m.getHero_id_fk()),
                    m.getVillain_id_fk(),
                    villainDAO.getVillainById(m.getVillain_id_fk())
            );

            movieList.add(mappedMovie);

        }

        return movieList; }

    //Method to get one movie by id
    //validate provided movie_id
    public Movie getMovieById(int id){

        //Validate the id is > 0
        if (id > 0){ return movieDAO.getMovieById(id); }

        //return null to the controller and appropriately respond
        logger.warn("Get Movie failed. Provided id was invalid");
        return null;
    }

    //Method to create new Movie
    //validate provided movie
    public Movie insertMovie(Movie movie){
        //Ensure movie_name is not null or empty
        // movie_release_year > 1950 and < 2024
        //foreign keys are greater than zero
        String log = "";
        if ( movie.getMovie_name() == null
                || movie.getMovie_name().isEmpty() ){
            log += " Movie name is invalid.";
        } else if ( movie.getHero_id_fk() < 0
                || movie.getVillain_id_fk() < 0
                || heroDAO.getHeroById(movie.getHero_id_fk()) == null
                || villainDAO.getVillainById(movie.getVillain_id_fk()) == null){
            log += " Hero or Villain key is invalid.";
        } else if ( movie.getMovie_release_year() <= 1950
                || movie.getMovie_release_year() > 2024 ){
            log += " Movie release year is invalid.";
        }

        if (log != ""){
            logger.warn("Movie creation failed." + log);
            return null;
        }


        /*if (movie.getMovie_name() == null
                || movie.getMovie_name().isEmpty()
                || movie.getMovie_release_year() <= 1950
                || movie.getMovie_release_year() > 2024
                || movie.getHero_id_fk() < 0
                || movie.getVillain_id_fk() < 0 ){
            logger.warn("Movie creation failed. Provided movie was invalid");
            return null;
        }*/
        //return results from DAO insert method and respond accordingly in controller
        return movieDAO.insertMovie(movie);
    }

    public Boolean removeMovie(int id){

        //Validate id is > 0
        if( id > 0 ){
            //Will return true if movie is removed, respond appropriately in controller
            return movieDAO.removeMovie(id);
        }

        return false;
    }

    //method to update existing movie
    //validate provided movie
    public Movie updateMovie(Movie movie) {

        //validate hero_name and hero_weapon are not null or empty strings and hero_id is greater 0
        if (movie.getMovie_name() == null
                || movie.getMovie_name().isEmpty()
                || movie.getMovie_release_year() <= 1950
                || movie.getMovie_release_year() > 2024
                || movie.getHero_id_fk() < 0
                || movie.getVillain_id_fk() < 0
                || movie.getMovie_id() < 0 ){
            return null;
        }
        //return update method from DAO and
        return movieDAO.updateMovie(movie);
    }
}
