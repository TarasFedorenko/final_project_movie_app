package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.movie.Movie;

import java.util.List;
import java.util.Map;

public interface PLPService {

    List<Movie> search(Map<String, Object> queryMap);
}
