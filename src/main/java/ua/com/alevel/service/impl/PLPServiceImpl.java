package ua.com.alevel.service.impl;


import org.springframework.stereotype.Service;


import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.repository.movie.DirectorRepository;
import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;


import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class PLPServiceImpl implements PLPService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final CrudRepositoryHelper<Director, DirectorRepository> crudRepositoryHelper;

    public PLPServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository, CrudRepositoryHelper<Director, DirectorRepository> crudRepositoryHelper) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.crudRepositoryHelper = crudRepositoryHelper;
    }

    @Override
    public List<Movie> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebUtil.DIRECTOR_PARAM) != null) {
            Long directorId = Long.parseLong(String.valueOf(queryMap.get(WebUtil.DIRECTOR_PARAM)));
            Optional<Director> director = crudRepositoryHelper.findById(directorRepository, directorId);
            return movieRepository.findByDirector(director.get());
        }
        if (queryMap.get(WebUtil.SEARCH_MOVIE_PARAM) != null) {
            String searchMovie = (String) queryMap.get(WebUtil.SEARCH_MOVIE_PARAM);
            return movieRepository.findByTitleContaining(searchMovie);
        }
        return movieRepository.findAll();
    }
}