package ua.com.alevel.persistence.repository.movie;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Director;

import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MovieRepository extends BaseRepository<Movie> {

    List<Movie> findByDirector(Director director);

    List<Movie> findByTitleContaining(String movieTitle);

    @Query("SELECT COUNT(a) FROM Movie a")
    Integer countMovies();

    @Query("from Movie m join m.reviews mr where mr.id =: reviewId")
    Movie findMovieByReviewId(Long reviewId);



}