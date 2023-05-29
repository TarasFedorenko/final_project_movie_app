package ua.com.alevel.cron;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.document.MovieIndex;
import ua.com.alevel.elastic.repository.MovieIndexRepository;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.repository.movie.MovieRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SyncElasticCronService {
    private final ElasticsearchOperations elasticsearchOperations;
    private final MovieRepository movieRepository;
    private final MovieIndexRepository movieIndexRepository;

    public SyncElasticCronService(ElasticsearchOperations elasticsearchOperations, MovieRepository movieRepository, MovieIndexRepository movieIndexRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.movieRepository = movieRepository;
        this.movieIndexRepository = movieIndexRepository;
    }


    @Scheduled(fixedDelay = 60000)
    public void syncToMovie() {
        elasticsearchOperations.indexOps(MovieIndex.class).refresh();
        movieIndexRepository.deleteAll();
        movieIndexRepository.saveAll(prepareDataset());
    }

    private Collection<MovieIndex> prepareDataset() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieIndex> movieIndices = new ArrayList<>();
        movies.forEach(movie -> {
            MovieIndex movieIndex = new MovieIndex();
            movieIndex.setTitle(movie.getTitle());
            movieIndices.add(movieIndex);
        });
        return movieIndices;
    }
}
