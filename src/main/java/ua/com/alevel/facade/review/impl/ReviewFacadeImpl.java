package ua.com.alevel.facade.review.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.review.ReviewFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.service.ReviewService;
import ua.com.alevel.service.SubscriberCrudService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.ReviewRequestDto;
import ua.com.alevel.web.dto.response.PageData;
import ua.com.alevel.web.dto.response.ReviewResponseDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewFacadeImpl implements ReviewFacade {

    private final ReviewService reviewService;
    private final SubscriberCrudService subscriberCrudService;

    public ReviewFacadeImpl(ReviewService reviewService, SubscriberCrudService subscriberCrudService) {
        this.reviewService = reviewService;
        this.subscriberCrudService = subscriberCrudService;
    }

    @Override
    public void create(ReviewRequestDto reviewRequestDto, Long movieId) {
        Review review = new Review();
        review.setMessage(reviewRequestDto.getMessage());
        review.setStars(reviewRequestDto.getStars());
        reviewService.create(review, movieId);
    }

    @Override
    public void update(ReviewRequestDto reviewRequestDto, Long id) {
        Optional<Review> optionalReview = reviewService.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setMessage(reviewRequestDto.getMessage());
            review.setStars(reviewRequestDto.getStars());
            reviewService.update(review);
        }
    }

    @Override
    public void delete(Long id) {
        reviewService.delete(id);
    }

    @Override
    public ReviewResponseDto findById(Long id) {
        Review review = reviewService.findById(id).orElseThrow(() -> new RuntimeException("review not found"));
        return new ReviewResponseDto(review);
    }

    @Override
    public PageData<ReviewResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Review> tableResponse = reviewService.findAll(dataTableRequest);
        List<ReviewResponseDto> reviews = tableResponse.getItems().stream().
                map(ReviewResponseDto::new).
                collect(Collectors.toList());

        PageData<ReviewResponseDto> pageData = (PageData<ReviewResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(reviews);
        return pageData;
    }

    @Override
    public Collection<ReviewResponseDto> findAllBySubscriber(Long id) {
        Collection<Review> reviews = reviewService.findBySubscriberId(id);
        return reviews.stream().map(ReviewResponseDto::new).toList();
    }
}

