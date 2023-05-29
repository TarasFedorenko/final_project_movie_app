package ua.com.alevel.service;

import java.util.List;

public interface ElasticMovieSearchService {

    List<String> searchTitle(String query);
}