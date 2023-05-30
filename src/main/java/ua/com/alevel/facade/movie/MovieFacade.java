package ua.com.alevel.facade.movie;

import ua.com.alevel.facade.CrudFacade;
import ua.com.alevel.web.dto.request.MovieRequestDto;
import ua.com.alevel.web.dto.response.MovieResponseDto;
import ua.com.alevel.web.dto.response.ReviewResponseDto;

import java.util.Collection;
import java.util.List;

public interface MovieFacade extends CrudFacade<MovieRequestDto, MovieResponseDto> {
    Collection<MovieResponseDto> findAllBySubscriber(Long id);
}
