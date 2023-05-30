package ua.com.alevel.facade.movie.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;


import ua.com.alevel.facade.movie.MovieFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;

import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.service.MovieService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.MovieRequestDto;
import ua.com.alevel.web.dto.response.MovieResponseDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.ReviewResponseDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieFacadeImpl implements MovieFacade {

    private final MovieService movieService;

    public MovieFacadeImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void create(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setReleaseYear(movieRequestDto.getReleaseYear());
        movie.setDescription(movieRequestDto.getDescription());
        movie.setDuration(movieRequestDto.getDuration());
        movie.setImageMovie(movieRequestDto.getImageMovie());
        movieService.create(movie);
    }

    @Override
    public void update(MovieRequestDto movieRequestDto, Long id) {
        Optional<Movie> optionalMovie = movieService.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            movie.setTitle(movieRequestDto.getTitle());
            movie.setReleaseYear(movieRequestDto.getReleaseYear());
            movie.setDescription(movieRequestDto.getDescription());
            movie.setDuration(movieRequestDto.getDuration());
            movie.setImageMovie(movieRequestDto.getImageMovie());
            movieService.update(movie);
        }
    }

    @Override
    public void delete(Long id) {
        movieService.delete(id);
    }

    @Override
    public MovieResponseDto findById(Long id) {
        Movie movie = movieService.findById(id).get();
        return new MovieResponseDto(movie);
    }

    @Override
    public PageData<MovieResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Movie> tableResponse = movieService.findAll(dataTableRequest);
        List<MovieResponseDto> movies = tableResponse.getItems().stream().
                map(MovieResponseDto::new).
                collect(Collectors.toList());

        PageData<MovieResponseDto> pageData = (PageData<MovieResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(movies);
        return pageData;
    }

    @Override
    public Collection<MovieResponseDto> findAllBySubscriber(Long id) {
        Collection<Movie> movies = movieService.findBySubscriberId(id);
        return movies.stream().map(MovieResponseDto::new).toList();
    }
}