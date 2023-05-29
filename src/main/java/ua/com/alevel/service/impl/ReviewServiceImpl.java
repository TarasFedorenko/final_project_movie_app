package ua.com.alevel.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
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
import ua.com.alevel.service.ReviewService;
import ua.com.alevel.util.SecurityUtil;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final SubscriberRepository subscriberRepository;


    private final MovieRepository movieRepository;
    private final CrudRepositoryHelper<Review, ReviewRepository> crudRepositoryHelper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, SubscriberRepository subscriberRepository,  MovieRepository movieRepository, CrudRepositoryHelper<Review, ReviewRepository> crudRepositoryHelper) {
        this.reviewRepository = reviewRepository;
        this.subscriberRepository = subscriberRepository;
        this.movieRepository = movieRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    @PreAuthorize("hasRole('ROLE_SUBSCRIBER')")
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Review review, Long movieId)  {
        Subscriber subscriber = subscriberRepository
                .findByEmail(SecurityUtil.getUsername())
                .orElseThrow(() -> new RuntimeException("Subscriber not found"));
        review.setSubscriber(subscriber);
        Movie movie = movieRepository.getById(movieId);
        review.setMovie(movie);
        reviewRepository.save(review);
        movieRepository.save(movie);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Review entity)  {
        crudRepositoryHelper.update(reviewRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id)  {
        crudRepositoryHelper.delete(reviewRepository, id);
    }//TODO delete

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> findById(Long id)  {
        return crudRepositoryHelper.findById(reviewRepository, id);
    }//TODO findById

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Review> findAll(DataTableRequest request)  {
        return crudRepositoryHelper.findAll(reviewRepository, request);
    }//TODO findAll

    @Override
    public Collection<Review> findAll() {
        return reviewRepository.findAll();
    }


    @Override
    public Review findByMovie(Long movieId) {
        return reviewRepository.findByMovieId(movieId);
    }

    @Override
    public Collection<Review> findBySubscriberId(Long id) {
        return reviewRepository.findAllBySubscriberId(id);
    }


}
