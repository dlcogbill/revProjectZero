package com.revature.daos;

import com.revature.models.Movie;

import java.util.ArrayList;

public interface MovieDAOInterface {

    //Method to select all movies
    ArrayList<Movie> getAllMovies();

    //Method to get one movie by id
    Movie getMovieById(int id);

    //Method to insert movie
    Movie insertMovie(Movie movie);

    //Method to remove Movie
    Boolean removeMovie(int id);

    //Method to update movie
    Movie updateMovie(Movie movie);
}
