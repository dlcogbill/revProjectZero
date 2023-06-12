package com.revature.daos;


import com.revature.models.Movie;
import com.revature.util.ConnectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class MovieDAO implements MovieDAOInterface{

    private static final Logger logger = LoggerFactory.getLogger(MovieDAO.class);
    @Override
    public ArrayList<Movie> getAllMovies() {
        
        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to retrieve all movies on DB
            String sql = "SELECT * FROM movies";

            //Statement because there are no variables to pass
            Statement s = conn.createStatement();

            //Execute query and save as result set
            ResultSet rs = s.executeQuery(sql);

            //Prepare empty array to hold data from DB
            ArrayList<Movie> movieList = new ArrayList<>();

            //Iterate through the results from DB until there are none
            while(rs.next()){

                //Create movie object from DB results
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getInt("movie_release_year"),
                        rs.getInt("hero_id_fk"),
                        rs.getInt("villain_id_fk")
                );

                //Add newly created object to array
                movieList.add(movie);
            }

            //return array to service
            return movieList;

        } catch(SQLException e){
            logger.warn("Failed to get all movies");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Movie getMovieById(int id) {
        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to retrieve one movie by id from DB
            String sql = "SELECT * FROM movies WHERE movie_id = ?";

            //Use a prepared Statement to pass requested movie_id to the db
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            //Execute query and save as result set
            ResultSet rs = ps.executeQuery();

            //if there is data in the result set
            if ( rs.next() ){

                //Create a new movie object from result set
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getInt("movie_release_year"),
                        rs.getInt("hero_id_fk"),
                        rs.getInt("villain_id_fk")
                );

                //return movie to service
                return movie;
            }

        }
        catch(SQLException e){
            logger.warn("Failed getting Movie!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Movie insertMovie(Movie movie) {
        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to create new hero on DB
            String sql = "INSERT INTO movies (movie_name, movie_release_year, hero_id_fk, villain_id_fk) VALUES (?, ?, ?, ?)";

            //Use a prepared Statement to pass new hero variables to the DB then Execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie.getMovie_name());
            ps.setInt(2, movie.getMovie_release_year());
            ps.setInt(3, movie.getHero_id_fk());
            ps.setInt(4, movie.getVillain_id_fk());
            ps.executeUpdate();

            //Return created movie
            return movie;

        } catch(SQLException e){
            System.out.println("Insert Movie failed!");
            e.printStackTrace();//detailed info in our console about what went wrong
        }
        return null;
    }

    @Override
    public Boolean removeMovie(int id) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to remove movie from DB
            String sql = "DELETE FROM movies WHERE movie_id = ?";

            //Use a prepared Statement to pass provided hero_id to the DB then execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Movie has been removed");
            return true;

        } catch(SQLException e){
            System.out.println("Remove movie failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }

        return false;
    }

    @Override
    public Movie updateMovie(Movie movie) {

        //Use a try-with-resources block to Create a connection to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //SQL query to update hero in DB
            String sql = "UPDATE movies SET movie_name = ?, movie_release_year = ?, hero_id_fk = ?, villain_id_fk = ? WHERE movie_id = ?";

            //Use a prepared Statement to pass provided hero_id, hero_name, hero_weapon to the DB then execute query
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie.getMovie_name());
            ps.setInt(2, movie.getMovie_release_year());
            ps.setInt(3, movie.getHero_id_fk());
            ps.setInt(4, movie.getVillain_id_fk());
            ps.setInt(5, movie.getMovie_id());
            ps.executeUpdate();

            //return provided hero to service
            return movie;

        } catch(SQLException e){
            System.out.println("Update movie failed!");
            e.printStackTrace(); //detailed info in our console about what went wrong
        }

        return null;
    }
}
