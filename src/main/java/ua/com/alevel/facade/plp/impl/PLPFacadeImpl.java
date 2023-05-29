package ua.com.alevel.facade.plp.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.exception.BadRequestException;
import ua.com.alevel.facade.plp.PLPFacade;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.service.ElasticMovieSearchService;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.MoviePLPDto;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PLPFacadeImpl implements PLPFacade {

    private final PLPService plpService;
    private final ElasticMovieSearchService elasticMovieSearchService;

    public PLPFacadeImpl(PLPService plpService, ElasticMovieSearchService elasticMovieSearchService) {
        this.plpService = plpService;
        this.elasticMovieSearchService = elasticMovieSearchService;
    }

    @Override
    public List<MoviePLPDto> search(WebRequest webRequest) {
        Map<String, Object> queryMap = new HashMap<>();
        if (webRequest.getParameterMap().get(WebUtil.DIRECTOR_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.DIRECTOR_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            Long directorId = Long.parseLong(params[0]);
            queryMap.put(WebUtil.DIRECTOR_PARAM, directorId);
        }
        if (webRequest.getParameterMap().get(WebUtil.SEARCH_MOVIE_PARAM) != null) {
            String[] params = webRequest.getParameterMap().get(WebUtil.SEARCH_MOVIE_PARAM);
            if (StringUtils.isBlank(params[0])) {
                throw new BadRequestException("bad request");
            }
            String searchMovie = params[0];
            queryMap.put(WebUtil.SEARCH_MOVIE_PARAM, searchMovie);

        }
        List<Movie> movies = plpService.search(queryMap);
        List<MoviePLPDto> moviePLPDtos = movies.stream().map(MoviePLPDto::new).toList();
        return moviePLPDtos;
    }

    @Override
    public List<String> searchTitle(String query) {
        return elasticMovieSearchService.searchTitle(query);
    }
}