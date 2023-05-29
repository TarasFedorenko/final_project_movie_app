package ua.com.alevel.facade.director.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.director.DirectorFacade;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.service.DirectorService;
import ua.com.alevel.web.dto.request.DirectorRequestDto;
import ua.com.alevel.web.dto.response.DirectorResponseDto;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class DirectorFacadeImpl implements DirectorFacade {
    private final DirectorService directorService;

    public DirectorFacadeImpl(DirectorService directorService) {
        this.directorService = directorService;
    }


    public DirectorResponseDto findById(Long id) {
        Optional<Director> directorOptional = directorService.findById(id);
        if (directorOptional.isEmpty()) {
            throw new RuntimeException("director not exist");
        }
        Director director = directorOptional.get();
        return new DirectorResponseDto(director);
    }

    @Override
    public Collection<DirectorRequestDto> findAll(WebRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        if (MapUtils.isNotEmpty(map)) {
            String[] movies = map.get("movies");
            if (movies != null) {
                String movieId = movies[0];
                return directorService.findAllByMovie(Long.parseLong(movieId)).stream().map(DirectorRequestDto::new).toList();
            }
        }
        Collection<Director> directors = directorService.findAll();
        return directors.stream().map(DirectorRequestDto::new).toList();
    }
}
