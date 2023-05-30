package ua.com.alevel.service.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.persistence.repository.review.ReviewRepository;
import ua.com.alevel.persistence.repository.user.SubscriberRepository;
import ua.com.alevel.service.SubscriberCrudService;
import ua.com.alevel.web.dto.SubscriberDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriberCrudServiceImpl implements SubscriberCrudService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SubscriberRepository subscriberRepository;
    private final CrudRepositoryHelper<Subscriber, SubscriberRepository> crudRepositoryHelper;
    private final MovieRepository movieRepository;

    private final ReviewRepository reviewRepository;
    public SubscriberCrudServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, SubscriberRepository subscriberRepository, CrudRepositoryHelper<Subscriber, SubscriberRepository> crudRepositoryHelper, MovieRepository movieRepository, ReviewRepository reviewRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.subscriberRepository = subscriberRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void create(Subscriber subscriber) {
        if (subscriberRepository.existsByEmail(subscriber.getEmail())) {
            throw new EntityExistException("this subscriber is exist");
        }
        subscriber.setPassword(bCryptPasswordEncoder.encode(subscriber.getPassword()));
        crudRepositoryHelper.create(subscriberRepository, subscriber);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void update(Subscriber subscriber) {
        crudRepositoryHelper.update(subscriberRepository, subscriber);
    }

    @Override
    public void delete(Long id) {
        crudRepositoryHelper.delete(subscriberRepository,id);
    }

    @Override
    public Optional<Subscriber> findById(Long id) {
        return subscriberRepository.findById(id);
    }

    @Override
    public Collection<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public List<Subscriber> findAllByMovie(Long movieId) {
        return subscriberRepository.findAllByMovie(movieId);
    }

    @Override
    public DataTableResponse<Subscriber> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public Integer count() {
        return subscriberRepository.countSubscribers();
    }
    @Override
    public void banUser(Long userId) {
        Subscriber subscriber = subscriberRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not exist"));
        subscriber.setEnabled(false);
        subscriberRepository.save(subscriber);
    }
    @Override
    public void unbanUser(Long userId) {
        Subscriber subscriber = subscriberRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not exist"));
        subscriber.setEnabled(true);
        subscriberRepository.save(subscriber);
    }

    @Override
    public SubscriberDto findByEmail(String email) {
        Optional<Subscriber>subscriberOptional = subscriberRepository.findByEmail(email);
        if(subscriberOptional.isPresent()){
            return new SubscriberDto(subscriberOptional.get());
        }
        throw new RuntimeException("subscriber not found");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void addMovieToSubscriber(Long userId, Long movieId) {
        Subscriber subscriber = subscriberRepository.findById(userId).orElseThrow(()->new RuntimeException("subscriber not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("movie not found"));
        subscriber.getMovies().add(movie);
        movie.getSubscribers().add(subscriber);
        subscriberRepository.save(subscriber);
        movieRepository.save(movie);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ, rollbackFor = Exception.class)
    public void removeMovieFromSubscriber(Long subId, Long movieId) {
        Subscriber subscriber = subscriberRepository.findById(subId).orElseThrow(()->new RuntimeException("subscriber not found"));
        Movie movie = movieRepository.findById(movieId).orElseThrow(()->new RuntimeException("movie not found"));
        subscriber.getMovies().remove(movie);
        movie.getSubscribers().remove(subscriber);
        subscriberRepository.save(subscriber);
        movieRepository.save(movie);
    }

    @Override
    public void removeReviewFromSubscriber(Long subId, Long reviewId) {
        Subscriber subscriber = subscriberRepository.findById(subId).orElseThrow(()->new RuntimeException("subscriber not found"));
        Review review = reviewRepository.findById(reviewId).orElseThrow(()->new RuntimeException("review not found"));
        Movie movie = movieRepository.findMovieByReviewId(reviewId);
        subscriber.getReviews().remove(review);
        movie.getReviews().remove(review);
        movieRepository.save(movie);
        subscriberRepository.save(subscriber);
        reviewRepository.save(review);
    }

}