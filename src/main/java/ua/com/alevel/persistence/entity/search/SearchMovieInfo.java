package ua.com.alevel.persistence.entity.search;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "search_movie_infos")
public class SearchMovieInfo extends BaseEntity {
    @Column(name = "count_director")
    private Long countDirector;

    @Column(name = "count_actor")
    private Long countActor;

    @Column(name = "count_movie_title")
    private Long countMovieTitle;

    @Column(unique = true)
    private String director;

    @Column(unique = true)
    private String actor;

    @Column(unique = true)
    private String movieTitle;

    public SearchMovieInfo() {
        super();
        this.countDirector = 0L;
        this.countActor = 0L;
        this.countMovieTitle = 0L;
    }
}
