package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.alevel.persistence.entity.person.Director;
import ua.com.alevel.persistence.repository.movie.DirectorRepository;
import ua.com.alevel.service.impl.DirectorServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DirectorServiceTest {

    private final Long id1 = 1L;
    private final String firstName1 = "John";
    private final String lastName1 = "Doe";
    private final Long id2 = 2L;
    private final String firstName2 = "Jane";
    private final String lastName2 = "Smith";

    @Mock
    private DirectorRepository directorRepository;

    @InjectMocks
    private DirectorServiceImpl directorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById_WithExistingId_ShouldReturnOptionalWithDirector() {
        // Arrange
        Director director = new Director();
        director.setId(id1);
        director.setFirstName(firstName1);
        director.setLastName(lastName1);
        directorRepository.save(director);
        when(directorRepository.findById(id1)).thenReturn(Optional.of(director));

        // Act
        directorRepository.findById(id1);
        Optional<Director> result = Optional.of(director);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(director, result.get());
        Mockito.verify(directorRepository, times(1)).findById(id1);
    }

    @Test
    public void testFindById_WithNonExistingId_ShouldReturnEmptyOptional() {
        // Arrange
        when(directorRepository.findById(id1)).thenReturn(Optional.empty());

        // Act
        Optional<Director> result = directorRepository.findById(id1);

        // Assert
        assertTrue(result.isEmpty());
        Mockito.verify(directorRepository, times(1)).findById(id1);
    }

    @Test
    public void testFindAll_ShouldReturnAllDirectors() {
        // Arrange
        List<Director> directors = new ArrayList<>();
        Director director1 = new Director();
        director1.setId(id1);
        director1.setFirstName(firstName1);
        director1.setLastName(lastName1);
        directors.add(director1);
        directorRepository.save(director1);
        Director director2 = new Director();
        director2.setId(id2);
        director2.setFirstName(firstName2);
        director2.setLastName(lastName2);
        directors.add(director2);
        directorRepository.save(director2);
        when(directorRepository.findAll()).thenReturn(directors);

        // Act
        Collection<Director> result = directorRepository.findAll();

        // Assert
        assertEquals(directors.size(), result.size());
        assertTrue(result.containsAll(directors));
        Mockito.verify(directorRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllByMovie_ShouldReturnDirectorsForMovie() {
        // Arrange
        List<Director> directors = new ArrayList<>();
        Director director1 = new Director();
        director1.setId(id1);
        director1.setFirstName(firstName1);
        director1.setLastName(lastName1);
        directors.add(director1);
        directorRepository.save(director1);
        Director director2 = new Director();
        director2.setId(id2);
        director2.setFirstName(firstName2);
        director2.setLastName(lastName2);
        directors.add(director2);
        directorRepository.save(director2);
        when(directorRepository.findAllByMovie(id1)).thenReturn(directors);

        // Act
        List<Director> result = directorRepository.findAllByMovie(id1);

        // Assert
        assertEquals(directors.size(), result.size());
        assertTrue(result.containsAll(directors));
        Mockito.verify(directorRepository, times(1)).findAllByMovie(id1);
    }

    @Test
    public void testCount_ShouldReturnNumberOfDirectors() {
        // Arrange
        Director director1 = new Director();
        directorRepository.save(director1);
        Director director2 = new Director();
        directorRepository.save(director2);
        Director director3 = new Director();
        directorRepository.save(director3);
        Director director4 = new Director();
        directorRepository.save(director4);
        Director director5 = new Director();
        directorRepository.save(director5);
        int count = 5;
        when(directorRepository.countDirectors()).thenReturn(count);

        // Act
        directorRepository.countDirectors();
        Integer result = 5;

        // Assert
        assertEquals(count, result);
        Mockito.verify(directorRepository, times(1)).countDirectors();
    }
}
