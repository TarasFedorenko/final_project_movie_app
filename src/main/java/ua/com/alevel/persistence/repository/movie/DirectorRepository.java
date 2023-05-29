package ua.com.alevel.persistence.repository.movie;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface DirectorRepository extends BaseRepository<Director> {
    @Query("from Director g join g.movies gm where gm.id =: movieId")
    List<Director> findAllByMovie(@Param("movieId") Long movieId);

    @Query("SELECT COUNT(d) FROM Director d")
    Integer countDirectors();
}
