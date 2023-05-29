package ua.com.alevel.persistence.repository.review;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Collection;
import java.util.List;

public interface ReviewRepository extends BaseRepository<Review> {
    Review findByMovieId (Long movieId);
    Collection<Review> findAllBySubscriberId (Long subId);

}
