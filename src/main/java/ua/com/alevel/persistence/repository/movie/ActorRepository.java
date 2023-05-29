package ua.com.alevel.persistence.repository.movie;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.person.Actor;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface ActorRepository extends BaseRepository<Actor> {
    @Query("from Actor g join g.movies gm where gm.id =: movieId")
    List<Actor> findAllByMovie(@Param("movieId") Long movieId);

    @Query("SELECT COUNT(a) FROM Actor a")
    Integer countActors();
}
