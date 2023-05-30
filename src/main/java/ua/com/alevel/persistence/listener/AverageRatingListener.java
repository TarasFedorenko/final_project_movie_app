package ua.com.alevel.persistence.listener;


import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;


import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;

public class AverageRatingListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    @PostRemove
    public static void countAverageRating(Movie movie) {
        double rate = 0.0;
        int count = 0;
        for (Review review : movie.getReviews()) {
            rate += review.getStars();
            count++;
        }
        if (count > 0) {
            double averageRating = rate / count;
            movie.setAverageRating(averageRating);
        } else {
            movie.setAverageRating(0.0);
        }
    }
}
