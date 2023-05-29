package ua.com.alevel.facade.genre;


import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.web.dto.request.GenreRequestDto;
import ua.com.alevel.web.dto.response.GenreResponseDto;

import java.util.Collection;

public interface GenreFacade {
    GenreResponseDto findById(Long id);

    Collection<GenreRequestDto> findAll(WebRequest webRequest);
}
