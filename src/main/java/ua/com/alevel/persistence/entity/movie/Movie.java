package ua.com.alevel.persistence.entity.movie;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.listener.AverageRatingListener;

import javax.persistence.*;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movies")
@EntityListeners(AverageRatingListener.class)
public class Movie extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;
    @Column(name = "image_movie", nullable = false)
    private String imageMovie;
    @Column(name = "duration", nullable = false)
    private Time duration;
    @Column(name = "imdb_id", nullable = false, unique = true)
    private String imdbId;
    @Column(name = "trailer_url", nullable = false)
    private String trailerUrl;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL)
    private Set<Genre> genres;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "movies")
    private Set<Actor> actors;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", referencedColumnName = "id")
    private Director director;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Review> reviews;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_subscriber", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "subscriber_id"))
    private Set<Subscriber> subscribers;
    @Column(name = "average_rating")
    private Double averageRating;

    public Movie() {
        super();
        this.duration = new Time(0, 0, 0);
        this.genres = new HashSet<>();
        this.actors = new HashSet<>();
        this.averageRating = 0.0;
    }
}
