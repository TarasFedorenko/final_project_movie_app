package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.movie.Movie;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(Long id);
    Collection<Genre> findAll();
    List<Genre> findAllByMovie(Long movieId);
}
