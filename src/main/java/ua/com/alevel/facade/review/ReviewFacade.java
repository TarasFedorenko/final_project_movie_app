package ua.com.alevel.facade.review;

import org.springframework.web.context.request.WebRequest;

import ua.com.alevel.web.dto.request.ReviewRequestDto;

import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.ReviewResponseDto;

import java.util.Collection;

public interface ReviewFacade {
    void create(ReviewRequestDto reviewRequestDto, Long id);

    void update(ReviewRequestDto requestDto, Long id);

    void delete(Long id);

    ReviewResponseDto findById(Long id);

    PageData<ReviewResponseDto> findAll(WebRequest request);

    Collection<ReviewResponseDto> findAllBySubscriber(Long id);
}
