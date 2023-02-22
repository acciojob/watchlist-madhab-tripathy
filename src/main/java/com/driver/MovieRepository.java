package com.driver;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
@Repository
public class MovieRepository {
    HashMap<String, Movie> movieHashMap = new HashMap<>(); //key - movie name, value - movie object
    HashMap<String, Director> directorHashMap = new HashMap<>(); //key - director name, value - director object
    HashMap<String, List<String>> directorMovieHashMap = new HashMap<>(); // key - director name, value - List of Movies

    // add movie to movie hashmap
    public void saveMovie(Movie movie){
        String movieName = movie.getName();
        movieHashMap.put(movieName,movie);
    }
    // add director to director hashmap
    public void saveDirector(Director director){
        String directorName = director.getName();
        directorHashMap.put(directorName,director);
    }
    // Pair an existing movie and director
    public void makeMovieDirectorPair(String movieName, String directorName){
        // director and movie is present or not in the respected maps
        if(directorHashMap.containsKey(directorName) && movieHashMap.containsKey(movieName)){
            List<String> moviesList = new ArrayList<>();
            if(directorMovieHashMap.containsKey(directorName)) { // And map the director with their movie
                moviesList = directorMovieHashMap.get(directorName); // get the existing movie list with the respected director
            }
            moviesList.add(movieName); // add the movie to the list
            directorMovieHashMap.put(directorName,moviesList);
        }
    }
    // Get Movie by movie name
    public Movie findMovieByName(String movieName){
        return movieHashMap.get(movieName);
    }
    // Get Director by director name
    // Pass director name as path parameter
    // Return Director object wrapped in a ResponseEntity object
    public Director findDirectorByName(String directorName){
        return directorHashMap.get(directorName);
    }
    // Get List of movies name for a given director name
    // Pass director name as path parameter
    // Return List of movies name(List()) wrapped in a ResponseEntity object
    public List<String> findMoviesByDirectorName(String directorName){
        List<String> movies = new ArrayList<>();
        if(directorMovieHashMap.containsKey(directorName)){
            movies = directorMovieHashMap.get(directorName);
        }
        return movies;
    }
    // Get List of all movies added: GET /movies/get-all-movies
    // No params or body required
    // Return List of movies name(List()) wrapped in a ResponseEntity object
    public List<String> findAllMovies(){
        return new ArrayList<>(movieHashMap.keySet());
    }
    // Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    // Pass director’s name as request parameter
    // Return success message wrapped in a ResponseEntity object
    public void deleteDirectorByName(String directorName){
        List<String> movieList;
        if(directorMovieHashMap.containsKey(directorName)) {
            movieList = directorMovieHashMap.get(directorName);
            for (String movie : movieList) {
                if(movieHashMap.containsKey(movie)) movieHashMap.remove(movie);
            }
            if(directorHashMap.containsKey(directorName)) directorHashMap.remove(directorName);
            directorMovieHashMap.remove(directorName);
        }
    }
    // Delete all directors and all movies by them from the records: DELETE /movies/delete-all-directors
    // No params or body required
    // Return success message wrapped in a ResponseEntity object
    // Controller Name - deleteAllDirectors
    // (Note that there can be some movies on your watchlist that aren’t mapped to any of the director.
    // Make sure you do not remove them.)
    public void deleteAllDirectors(){
        for(String director : directorHashMap.keySet()){
            // delete directors and movies from directorMovie map
            // if both are common in the director map and directorMovie map
            if(directorMovieHashMap.containsKey(director)){
                directorMovieHashMap.remove(director);
            }
        }
    }
}
