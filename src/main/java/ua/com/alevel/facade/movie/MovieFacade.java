package ua.com.alevel.facade.movie;

import ua.com.alevel.facade.CrudFacade;
import ua.com.alevel.web.dto.request.MovieRequestDto;
import ua.com.alevel.web.dto.response.MovieResponseDto;

public interface MovieFacade extends CrudFacade<MovieRequestDto, MovieResponseDto> {
}
