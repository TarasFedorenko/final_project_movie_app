package ua.com.alevel.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.MovieIndex;
import ua.com.alevel.service.ElasticMovieSearchService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticMovieSearchServiceImpl implements ElasticMovieSearchService {

    private static final String MOVIE_INDEX = "movieindex";

    private final ElasticsearchOperations elasticsearchOperations;

    public ElasticMovieSearchServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<String> searchTitle(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("title", "*" + query.toLowerCase() + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<MovieIndex> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        MovieIndex.class,
                        IndexCoordinates.of(MOVIE_INDEX));
        final List<String> suggestions = new ArrayList<>();
        searchSuggestions.getSearchHits().forEach(searchHit -> suggestions.add(searchHit.getContent().getTitle()));
        return suggestions;
    }
}