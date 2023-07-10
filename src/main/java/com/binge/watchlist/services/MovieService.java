package com.binge.watchlist.services;

import com.binge.watchlist.models.Director;
import com.binge.watchlist.models.Movie;
import com.binge.watchlist.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director) {
        movieRepository.addDirector(director);
    }

    public Movie findMovie(String movieName) {
        return movieRepository.getMovie(movieName);
    }

    public Director findDirector(String directorName) {
        return movieRepository.getDirector(directorName);
    }

    public void addPair(String movieName, String directorName) {
        Movie movie = movieRepository.getMovie(movieName);
        movieRepository.setMovieAndDirector(directorName, movie);
    }

    public ArrayList<Movie> findDirectorMoviePair(String directorName) {
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public ArrayList<Movie> findAllMovies() {
        return movieRepository.getAllMovies();
    }

    public String deleteDirectorByName(String directorName) {
        return movieRepository.removeDirector(directorName);
    }

    public void deleteDirectors() {
        movieRepository.removeAllDirectors();
    }

}
