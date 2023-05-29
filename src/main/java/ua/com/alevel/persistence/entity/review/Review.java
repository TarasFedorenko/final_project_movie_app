package ua.com.alevel.persistence.entity.review;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.user.Subscriber;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {
    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "stars", nullable = false)
    public Double stars;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "subscriber_id", referencedColumnName = "id")
    private Subscriber subscriber;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    public Review(){
        super();
        this.message="";
        this.stars = 0.0;
    }

}
