package ua.com.alevel.persistence.entity.person;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.persistence.entity.movie.Movie;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "directors")
public class Director extends BasePerson {

    @OneToMany(mappedBy = "director")
    Set<Movie> movies;
}
