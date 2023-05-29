package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.person.Actor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ActorService {
    Optional<Actor> findById(Long id);
    Collection<Actor> findAll();
    List<Actor> findAllByMovie(Long movieId);

    Integer count();
}
