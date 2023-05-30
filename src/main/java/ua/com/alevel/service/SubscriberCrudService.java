package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.web.dto.SubscriberDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SubscriberCrudService extends BaseCrudService<Subscriber> {
    Optional<Subscriber> findById(Long id);

    Collection<Subscriber> findAll();

    List<Subscriber> findAllByMovie(Long movieId);

    Integer count();

    void banUser(Long userId);

    void unbanUser(Long userId);

    SubscriberDto findByEmail(String email);

    void addMovieToSubscriber(Long userId, Long movieId);

    void removeMovieFromSubscriber(Long subId, Long movieId);
    void removeReviewFromSubscriber(Long subId, Long reviewId);
}