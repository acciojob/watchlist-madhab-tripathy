package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/movies") //path begin with movies param
public class MovieController {
    @Autowired
    MovieService movieService; // movieService instance created

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam String movie,@RequestParam String director){
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity<>("Movie and director pair added",HttpStatus.CREATED);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName){
        Movie movie = null; //Assign movie by calling movieService;
        movie = movieService.getMovieByName(movieName);
        return new ResponseEntity<>(movie,HttpStatus.CREATED);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName){
        Director director = null; // Assign movie by calling movieService;
        director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director,HttpStatus.CREATED);
    }
    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("director") String directorName){
        List<String> movieList = null; // Assign  movie List by calling movieService
        movieList = movieService.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(movieList,HttpStatus.CREATED);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> allMovies = null;
        allMovies = movieService.findAllMovies();
        return new ResponseEntity<>(allMovies,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>("Director deleted successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted Successfully",HttpStatus.CREATED);
    }
}
