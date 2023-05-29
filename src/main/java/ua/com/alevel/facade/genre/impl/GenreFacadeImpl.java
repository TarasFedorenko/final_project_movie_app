package ua.com.alevel.facade.genre.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.genre.GenreFacade;
import ua.com.alevel.persistence.entity.movie.Genre;
import ua.com.alevel.service.GenreService;
import ua.com.alevel.web.dto.request.GenreRequestDto;
import ua.com.alevel.web.dto.response.GenreResponseDto;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;


@Service
public class GenreFacadeImpl implements GenreFacade {

    private final GenreService genreService;

    public GenreFacadeImpl(GenreService genreService) {
        this.genreService = genreService;
    }


    @Override
    public GenreResponseDto findById(Long id) {
        Optional<Genre> genreOptional = genreService.findById(id);
        if (genreOptional.isEmpty()) {
            throw new RuntimeException("genre not exist");
        }
        Genre genre = genreOptional.get();
        return new GenreResponseDto(genre);
    }

    @Override
    public Collection<GenreRequestDto> findAll(WebRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] movies = map.get("movies");
            if (movies != null) {
                String movieId = movies[0];
                return genreService.findAllByMovie(Long.parseLong(movieId)).stream().map(GenreRequestDto::new).toList();
            }
        }
        Collection<Genre> genres = genreService.findAll();
        return genres.stream().map(GenreRequestDto::new).toList();
    }
}

