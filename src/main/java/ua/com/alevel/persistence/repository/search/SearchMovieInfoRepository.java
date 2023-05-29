package ua.com.alevel.persistence.repository.search;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.search.SearchMovieInfo;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface SearchMovieInfoRepository extends BaseRepository<SearchMovieInfo> {
    SearchMovieInfo findByDirector(String director);
}
