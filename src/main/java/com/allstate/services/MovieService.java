package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private IMovieRepository repository;

    // Autowired Generate the class and send the repository object.
    // JPA knows that instead of interface, spring needs the class.
    @Autowired
    public void setRepository(IMovieRepository repository) {
        this.repository = repository;
    }

    // Controller receive the JSON string, JAX serialize it to String Object,
    // below "create" method is called by controller to pass the movie object
    public Movie create(Movie m) {
        return this.repository.save(m);
    }

    public Movie findById(int id) {
        return this.repository.findOne(id);
    }

    public Iterable<Movie> findAll() {
        return this.repository.findAll();
    }


    public Movie findByTitle(String title){
        return this.repository.findByTitle(title);
    }

    public void delete(int id) {
        this.repository.delete(id);
    }

    public Movie update(int id, Movie movie) {
        Movie oldMovie = this.findById(id);

        oldMovie.setTitle(movie.getTitle());
        oldMovie.setRating(movie.getRating());
        oldMovie.setReleased(movie.getReleased());
        oldMovie.setWatched(movie.isWatched());
        oldMovie.setLength(movie.getLength());

        return this.repository.save(oldMovie);
    }
}
