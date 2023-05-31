package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.entity.movie.Movie;
import ua.com.alevel.persistence.entity.review.Review;
import ua.com.alevel.persistence.entity.user.Subscriber;
import ua.com.alevel.persistence.repository.user.SubscriberRepository;
import ua.com.alevel.persistence.type.Gender;
import ua.com.alevel.persistence.type.RoleType;
import ua.com.alevel.service.impl.SubscriberCrudServiceImpl;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscriberCrudServiceTest {

    private static Subscriber subscriber;
    private static final Long ID = 1L;
    private static final String USERNAME = "TestUsername";
    private static final String EMAIL = "email@test.com";
    private static final String PASSWORD = "12345";
    private static final String PHONE_NUMBER = "123456789";
    private static final RoleType ROLE_TYPE = RoleType.ROLE_SUBSCRIBER;
    private static final Integer AGE = 18;
    private static final Gender GENDER = Gender.MALE;
    private static final String COUNTRY = "Ukraine";
    private static Set<Movie> movies;
    private static Set<Review> reviews;

    @Mock
    private SubscriberRepository subscriberRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private CrudRepositoryHelper crudRepositoryHelper;

    @InjectMocks
    private SubscriberCrudServiceImpl subscriberService;

    @BeforeEach
    public void setUpMock() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    public static void setUp() {
        subscriber = new Subscriber();
        subscriber.setId(ID);
        subscriber.setUsername(USERNAME);
        subscriber.setEmail(EMAIL);
        subscriber.setPassword(PASSWORD);
        subscriber.setPhoneNumber(PHONE_NUMBER);
        subscriber.setRoleType(ROLE_TYPE);
        subscriber.setAge(AGE);
        subscriber.setGender(GENDER);
        subscriber.setCountry(COUNTRY);
        movies = new HashSet<>();
        reviews = new HashSet<>();
    }
}
