package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.person.Director;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DirectorService {
    Optional<Director> findById(Long id);
    Collection<Director> findAll();
    List<Director> findAllByMovie(Long movieId);

    Integer count();

}
