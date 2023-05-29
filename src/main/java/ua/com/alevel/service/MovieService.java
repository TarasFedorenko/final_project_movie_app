package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.movie.Movie;

public interface MovieService extends BaseCrudService<Movie> {
    Integer count();
}
