package ua.com.alevel.persistence.entity.person;


import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Movie;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "actors")
public class Actor extends BasePerson {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "actor_movie",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;

    public Actor() {
        super();
        this.movies = new HashSet<>();
    }
}
