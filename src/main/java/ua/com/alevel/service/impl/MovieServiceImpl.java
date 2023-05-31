package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;

import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.movie.Movie;

import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.persistence.repository.review.ReviewRepository;
import ua.com.alevel.persistence.repository.user.SubscriberRepository;
import ua.com.alevel.service.MovieService;

import java.util.Collection;
import java.util.Optional;


@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CrudRepositoryHelper<Movie, MovieRepository> crudRepositoryHelper;
    private final ReviewRepository reviewRepository;
    private final SubscriberRepository subscriberRepository;

    public MovieServiceImpl(MovieRepository movieRepository, CrudRepositoryHelper<Movie, MovieRepository> crudRepositoryHelper, ReviewRepository reviewRepository, SubscriberRepository subscriberRepository) {
        this.movieRepository = movieRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.reviewRepository = reviewRepository;
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Movie entity) {
        crudRepositoryHelper.create(movieRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Movie entity) {
        crudRepositoryHelper.update(movieRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        crudRepositoryHelper.delete(movieRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findById(Long id) {
        return crudRepositoryHelper.findById(movieRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Movie> findAll(DataTableRequest request) {
        return crudRepositoryHelper.findAll(movieRepository, request);
    }

    @Override
    public Integer count() {
        return movieRepository.countMovies();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Movie> findBySubscriberId(Long id) {
        Subscriber subscriber = subscriberRepository.findById(id).orElseThrow(()->new RuntimeException("subscriber not found"));
        return subscriber.getMovies();
    }

    @Override
    @Transactional(readOnly = true)
    public Movie findMovieByReviewId(Long reviewId) {
        Review review = reviewRepository.getById(reviewId);
        return review.getMovie();
    }
}
