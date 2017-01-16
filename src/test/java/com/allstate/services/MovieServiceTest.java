package com.allstate.services;

import com.allstate.entities.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/seed.sql"})
public class MovieServiceTest {

    @Autowired
    private MovieService service;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void shouldCreateMovie() throws Exception {
        Movie before = new Movie();
        before.setTitle("The Matrix");
        Movie after = this.service.create(before);
        assertTrue(after.getId()>0);
        assertEquals(2, after.getId());
        assertEquals("The Matrix", after.getTitle());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void shouldNotCreateMovieNoTitle() throws Exception {
        Movie before = new Movie();
        Movie after = this.service.create(before);
        assertEquals(2, after.getId());
    }

    @Test
    public void shouldReturnMovie() throws Exception {
        Movie m = this.service.findById(1);
        assertEquals(1, m.getId());

    }

    @Test
    public void shouldNotReturnMovie() throws Exception {
        Movie m = this.service.findById(5);
        assertNull(m);

    }

    @Test
    public void shouldReturnAllMovies() throws Exception {
        ArrayList<Movie> movies = (ArrayList<Movie>) this.service.findAll();
        assertTrue(movies.size()>0);
    }

    @Test
    public void shouldReturnTheMovieByTitle() throws Exception {
        Movie movie = this.service.findByTitle("The Avenger");
        assertEquals("The Avenger", movie.getTitle());
    }

    @Test
    public void shouldReturnTheMovieByTitleNotInDB() throws Exception {
        Movie movie = this.service.findByTitle("XXXXXXXXX");
        assertNull(movie);
    }

    @Test
    public void shouldDeleteTheMovie() throws Exception {
        this.service.delete(1);
        Movie m =this.service.findById(1);
        assertNull(m);
    }

    @Test(expected = org.springframework.dao.EmptyResultDataAccessException.class)
    public void shouldNotDeleteMovieBadId() throws Exception {
        this.service.delete(5);
    }

    @Test
    public void shouldUpdateTheMovieTitleById() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("The Rock");

        Movie updatedMovie = this.service.update(1,movie);

        assertEquals(1,updatedMovie.getVersion());
        assertEquals(movie.getTitle(),updatedMovie.getTitle());


    }

    @Test(expected = java.lang.NullPointerException.class)
    public void shouldNotUpdateTheMovieTitleByBadId() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("The Rock");

        Movie updatedMovie = this.service.update(5,movie);

        assertNull(updatedMovie);

    }
}