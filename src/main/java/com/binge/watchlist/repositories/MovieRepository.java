package com.binge.watchlist.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import com.binge.watchlist.models.Movie;
import com.binge.watchlist.models.Director;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

    private HashMap<String, Movie> movies;
    private HashMap<String, Director> directors;
    private HashMap<String, ArrayList<Movie>> moviesAndDirectors;

    public MovieRepository() {
        movies = new HashMap<>();
        directors = new HashMap<>();
        moviesAndDirectors = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getName(), movie);
    }

    public Movie getMovie(String movieName) {
        return movies.get(movieName);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(), director);
    }

    public Director getDirector(String directorName) {
        return directors.get(directorName);
    }

    public void setMovieAndDirector(String directorName, Movie movie) {
        if (moviesAndDirectors.containsKey(directorName)) {
            moviesAndDirectors.get(directorName).add(movie);

        } else {
            moviesAndDirectors.put(directorName, new ArrayList<Movie>());
            ArrayList<Movie> movieList = moviesAndDirectors.get(directorName);
            movieList.add(movie);
            moviesAndDirectors.put(directorName, movieList);
        }
    }

    public ArrayList<Movie> getMoviesByDirectorName(String directorName) {
        return moviesAndDirectors.get(directorName);
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> movieList = new ArrayList<>();
        movies.forEach((movieName, movie)-> movieList.add(movie));
        return movieList;
    }

    public String removeDirector(String directorName) {
        if (directors.containsKey(directorName)) {
            ArrayList<Movie> moviesOfDirector = moviesAndDirectors.get(directorName);
            moviesOfDirector.forEach(movie -> movies.remove(movie.getName()));
            directors.remove(directorName);
            moviesAndDirectors.remove(directorName);
            return "Director deleted!";

        } else {
            return "Director not found!";
        }
    }

    public void removeAllDirectors() {
        movies.clear();
        directors.clear();
        moviesAndDirectors.clear();
    }

}
