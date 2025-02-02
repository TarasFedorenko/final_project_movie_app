package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.review.Review;


import java.util.Collection;

import java.util.Optional;

public interface ReviewService {
    void create(Review review, Long movieId);

    void update(Review review);

    void delete(Long id);

    Optional<Review> findById(Long id);

    DataTableResponse<Review> findAll(DataTableRequest request);

    Collection<Review> findAll();

    Review findByMovie(Long movieId);

    Collection<Review> findBySubscriberId(Long subId);


}
