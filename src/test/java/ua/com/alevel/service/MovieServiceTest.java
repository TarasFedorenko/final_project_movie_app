package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.repository.movie.MovieRepository;
import ua.com.alevel.persistence.repository.review.ReviewRepository;
import ua.com.alevel.persistence.repository.user.SubscriberRepository;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.service.impl.MovieServiceImpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.doNothing;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private CrudRepositoryHelper<Movie, MovieRepository> crudRepositoryHelper;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private SubscriberRepository subscriberRepository;

    private MovieServiceImpl movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepository, crudRepositoryHelper, reviewRepository, subscriberRepository);
    }

    @Test
    public void create_shouldCallCrudRepositoryHelperCreateMethod() {
        Movie movie = new Movie();
        doNothing().when(crudRepositoryHelper).create(movieRepository, movie);

        movieService.create(movie);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).create(movieRepository, movie);
    }

    @Test
    public void update_shouldCallCrudRepositoryHelperUpdateMethod() {
        Movie movie = new Movie();
        doNothing().when(crudRepositoryHelper).update(movieRepository, movie);

        movieService.update(movie);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).update(movieRepository, movie);
    }

    @Test
    public void delete_shouldCallCrudRepositoryHelperDeleteMethod() {
        Long id = 1L;
        doNothing().when(crudRepositoryHelper).delete(movieRepository, id);

        movieService.delete(id);

        Mockito.verify(crudRepositoryHelper, Mockito.times(1)).delete(movieRepository, id);
    }

    @Test
    public void findById_shouldReturnOptionalMovie() {
        Long id = 1L;
        Movie movie = new Movie();
        Mockito.when(crudRepositoryHelper.findById(movieRepository, id)).thenReturn(Optional.of(movie));

        Optional<Movie> result = movieService.findById(id);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(movie, result.get());
    }

    @Test
    public void findAll_shouldReturnDataTableResponse() {
        DataTableRequest request = new DataTableRequest();
        DataTableResponse<Movie> expectedResponse = new DataTableResponse<>();
        Mockito.when(crudRepositoryHelper.findAll(movieRepository, request)).thenReturn(expectedResponse);

        DataTableResponse<Movie> result = movieService.findAll(request);

        Assertions.assertEquals(expectedResponse, result);
    }

    @Test
    public void count_shouldReturnMovieCount() {
        int expectedCount = 5;
        Mockito.when(movieRepository.countMovies()).thenReturn(expectedCount);

        int result = movieService.count();

        Assertions.assertEquals(expectedCount, result);
    }

    @Test
    public void findBySubscriberId_shouldReturnMoviesForSubscriber() {
        Long subscriberId = 1L;
        Subscriber subscriber = new Subscriber();
        Set<Movie> movies = new HashSet<>();
        subscriber.setMovies(movies);
        subscriber.setId(subscriberId);
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        movies.add(movie1);
        movies.add(movie2);
        Mockito.when(subscriberRepository.findById(subscriberId)).thenReturn(Optional.of(subscriber));

        Collection<Movie> result = movieService.findBySubscriberId(subscriberId);

        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(movie1));
        Assertions.assertTrue(result.contains(movie2));
    }

    @Test
    public void findMovieByReviewId_shouldReturnMovieForReviewId() {
        Long reviewId = 1L;
        Movie movie = new Movie();
        Review review = new Review();
        review.setId(reviewId);
        review.setMovie(movie);
        Mockito.when(reviewRepository.getById(reviewId)).thenReturn(review);

        Movie result = movieService.findMovieByReviewId(reviewId);

        Assertions.assertEquals(movie, result);
    }
}
