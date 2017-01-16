package com.allstate.services;

import com.allstate.entities.Movie;
import com.allstate.repositories.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private IMovieRepository repository;

    // Autowired Generate the class and send the repository object.
    // JPA knows that instead of interface spring need class.
    @Autowired
    public void setRepository(IMovieRepository repository) {
        this.repository = repository;
    }

    // Controller receive the JSON string, JAX serialize it to String Object,
    // below "create" method is called by controller to pass the movie object
    public Movie create(Movie m) {
        return this.repository.save(m);
    }
}
