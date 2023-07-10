package com.binge.watchlist.controllers;

import com.binge.watchlist.models.Movie;
import com.binge.watchlist.models.Director;
import com.binge.watchlist.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("movie added", HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity<>("director added", HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String movieName) {
        // Retrieve the movie by name from the database here
        Movie movie = movieService.findMovie(movieName);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name") String directorName) {
        // Retrieve the director by name from the database here
        Director director = movieService.findDirector(directorName);
        return ResponseEntity.ok(director);
    }

    @PostMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName) {
        movieService.addPair(movieName, directorName);
        return new ResponseEntity<>("Pair added", HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movies-by-director-name")
    public ResponseEntity<ArrayList<Movie>> getMoviesByDirectorName(@RequestParam("director") String directorName) {
        ArrayList<Movie> movies = movieService.findDirectorMoviePair(directorName);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String directorName) {
        String message = movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors() {
        return new ResponseEntity<>("Directors deleted!", HttpStatus.OK);
    }

}
