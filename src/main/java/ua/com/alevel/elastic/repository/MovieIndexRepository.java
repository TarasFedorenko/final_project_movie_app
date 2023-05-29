package ua.com.alevel.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.elastic.document.MovieIndex;

@Repository
public interface MovieIndexRepository extends ElasticsearchRepository<MovieIndex, String> {
}
