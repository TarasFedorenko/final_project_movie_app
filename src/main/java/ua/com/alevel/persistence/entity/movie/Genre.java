package ua.com.alevel.persistence.entity.movie;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "genres")
public class Genre extends BaseEntity {
    @Column(name = "genre_name", nullable = false)
    private String genreName;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "genre_movie",
            joinColumns = @JoinColumn(name = "genre_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private Set<Movie> movies;

}
