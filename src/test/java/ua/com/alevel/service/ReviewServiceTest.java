package ua.com.alevel.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.persistence.repository.review.ReviewRepository;
import ua.com.alevel.persistence.repository.user.SubscriberRepository;
import ua.com.alevel.service.impl.ReviewServiceImpl;
import ua.com.alevel.persistence.datatable.DataTableRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private CrudRepositoryHelper<Review, ReviewRepository> crudRepositoryHelper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    public void findAll_ShouldReturnAllReviews() {
        // Arrange
        List<Review> expectedReviews = new ArrayList<>();
        when(reviewRepository.findAll()).thenReturn(expectedReviews);

        // Act
        Collection<Review> actualReviews = reviewService.findAll();

        // Assert
        assertEquals(expectedReviews, actualReviews);
    }
}
