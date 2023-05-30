package ua.com.alevel.persistence.repository.review;


import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.repository.BaseRepository;
import java.util.Collection;


public interface ReviewRepository extends BaseRepository<Review> {
    Review findByMovieId(Long movieId);

    Collection<Review> findAllBySubscriberId(Long subId);


}
