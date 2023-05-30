package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.movie.Movie;

import java.util.Collection;
import java.util.List;

public interface MovieService extends BaseCrudService<Movie> {
    Integer count();

    Collection<Movie> findBySubscriberId(Long id);
    Movie findMovieByReviewId(Long reviewId);

}
