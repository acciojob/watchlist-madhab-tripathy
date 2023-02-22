package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }
    public void addDirector(Director director){
        movieRepository.saveDirector(director);
    }
    public void addMovieDirectorPair(String movie, String director){
        movieRepository.makeMovieDirectorPair(movie,director);
    }
    public Movie getMovieByName(String movieName){
        return movieRepository.findMovieByName(movieName);
    }
    public Director getDirectorByName(String directorName){
        return movieRepository.findDirectorByName(directorName);
    }
    public List<String> getMoviesByDirectorName(String directorName){
        return movieRepository.findMoviesByDirectorName(directorName);
    }
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public void deleteDirectorByName(String directorName){
        movieRepository.deleteDirectorByName(directorName);
    }
    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }
}
